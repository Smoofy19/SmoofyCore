/*
 * Copyright ©️
 * @author - Smoofy
 * @GitHub - https://github.com/Smoofy19
 * Created - 23.10.24, 13:13
 */

package de.smoofy.core.base.bootstrap;

import com.github.retrooper.packetevents.PacketEvents;
import de.smoofy.core.base.CoreBase;
import de.smoofy.core.base.paper.listener.AsyncPlayerPreLoginListener;
import de.smoofy.core.base.paper.listener.ItemBuilderListener;
import de.smoofy.core.base.paper.listener.PlayerJoinListener;
import de.smoofy.core.base.paper.listener.PlayerQuitListener;
import io.github.retrooper.packetevents.factory.spigot.SpigotPacketEventsBuilder;
import lombok.Getter;
import lombok.experimental.Accessors;
import me.tofaa.entitylib.APIConfig;
import me.tofaa.entitylib.EntityLib;
import me.tofaa.entitylib.spigot.SpigotEntityLibPlatform;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
@Accessors(fluent = true)
public class PaperBootstrap extends JavaPlugin {

    @Getter
    @Accessors(fluent = true)
    private static PaperBootstrap instance;

    @Override
    public void onLoad() {
        PacketEvents.setAPI(SpigotPacketEventsBuilder.build(this));
        PacketEvents.getAPI().load();
    }

    @Override
    public void onEnable() {
        instance = this;

        PacketEvents.getAPI().init();
        EntityLib.init(new SpigotEntityLibPlatform(this), new APIConfig(PacketEvents.getAPI()));

        new CoreBase(true);

        this.registerListener();
    }

    @Override
    public void onDisable() {
        instance = null;

        PacketEvents.getAPI().terminate();
    }

    private void registerListener() {
        new AsyncPlayerPreLoginListener();
        new ItemBuilderListener();
        new PlayerJoinListener();
        new PlayerQuitListener();
    }
}
