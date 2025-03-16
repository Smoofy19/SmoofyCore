/*
 * Copyright ©️
 * @author - Smoofy
 * @GitHub - https://github.com/Smoofy19
 * Created - 23.10.24, 13:13
 */

package de.smoofy.core.base.player;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import de.smoofy.core.api.Core;
import de.smoofy.core.api.player.ICorePlayer;
import de.smoofy.core.api.player.ICorePlayerProvider;
import de.smoofy.core.base.database.DatabaseProvider;
import dev.httpmarco.evelon.Repository;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CorePlayerProviderImpl implements ICorePlayerProvider {

    private final Map<Object, ICorePlayer> corePlayerCache = Maps.newHashMap();
    private final List<ICorePlayer> corePlayers = Lists.newArrayList();

    private final Repository<CorePlayer> corePlayerRepository = DatabaseProvider.instance().corePlayerRepository();

    @Override
    public ICorePlayer corePlayer(Player player) {
        return this.corePlayer(player.getUniqueId());
    }

    @Override
    public ICorePlayer corePlayer(UUID uuid) {
        if (this.corePlayerCache.containsKey(uuid)) {
            return this.corePlayerCache.get(uuid);
        }
        var corePlayer = new CorePlayer(uuid);
        this.cacheCorePlayer(corePlayer);
        return corePlayer;
    }

    @Override
    public ICorePlayer corePlayer(String name) {
        if (this.corePlayerCache.containsKey(name)) {
            return this.corePlayerCache.get(name);
        }
        var corePlayer = new CorePlayer(name);
        this.cacheCorePlayer(corePlayer);
        return corePlayer;
    }

    @Override
    public List<ICorePlayer> corePlayers() {
        return this.corePlayers;
    }

    @Override
    public void init(UUID uuid) {
        if (this.exists(uuid)) {
            return;
        }
        this.corePlayerRepository.query().create(new CorePlayer(uuid, Core.instance().uuidFetcher().name(uuid),
                100, 0, 0, 0));
    }

    @Override
    public void update(ICorePlayer player) {
        if (!this.exists(player.uuid())) {
            return;
        }
        this.corePlayerRepository.query().update((CorePlayer) player);
    }

    @Override
    public boolean exists(UUID uuid) {
        return this.corePlayerRepository.query().match("uuid", uuid).exists();
    }

    private void cacheCorePlayer(ICorePlayer player) {
        this.corePlayers.add(player);
        this.corePlayerCache.put(player.uuid(), player);
        this.corePlayerCache.put(player.name(), player);
    }
}
