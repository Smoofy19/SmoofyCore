/*
 * Copyright ©️
 * @author - Smoofy
 * @GitHub - https://github.com/Smoofy19
 * Created - 23.10.24, 13:13
 */

package de.smoofy.core.base.player;

import de.smoofy.core.api.Core;
import de.smoofy.core.api.player.ICorePlayer;
import de.smoofy.core.base.bootstrap.VelocityBootstrap;
import de.smoofy.core.base.database.DatabaseProvider;
import dev.httpmarco.evelon.PrimaryKey;
import dev.httpmarco.evelon.Repository;
import dev.httpmarco.evelon.Row;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.title.Title;
import org.apache.commons.lang3.NotImplementedException;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.util.UUID;

public class CorePlayer implements ICorePlayer {

    @PrimaryKey
    @Row(id = "uuid")
    private final UUID uniqueId;
    private final String name;

    private int coins;
    private int onlineTime;
    private int playTime;
    private int nickState;

    @Row(ignore = true)
    private BukkitTask actionBarTask;

    @Row(ignore = true)
    private Repository<CorePlayer> repository = DatabaseProvider.instance().corePlayerRepository();

    public CorePlayer(UUID uniqueId, String name, int coins, int onlineTime, int playTime, int nickState) {
        this.uniqueId = uniqueId;
        this.name = name;
        this.coins = coins;
        this.onlineTime = onlineTime;
        this.playTime = playTime;
        this.nickState = nickState;
    }

    public CorePlayer(Player player) {
        this.uniqueId = player.getUniqueId();
        this.name = player.getName();
        this.setDatabaseValues();
    }

    public CorePlayer(UUID uuid) {
        this.uniqueId = uuid;
        this.name = Core.instance().uuidFetcher().name(this.uniqueId);
        this.setDatabaseValues();
    }

    public CorePlayer(String name) {
        this.name = name;
        this.uniqueId = Core.instance().uuidFetcher().uuid(name);
        this.setDatabaseValues();
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public UUID uuid() {
        return this.uniqueId;
    }

    @Override
    public Component displayName() {
        return this.bukkitPlayer().displayName();
    }

    @Override
    public NamedTextColor color() {
        return (NamedTextColor) this.bukkitPlayer().displayName().color();
    }

    @Override
    public Player bukkitPlayer() {
        return Bukkit.getPlayer(this.uuid());
    }

    @Override
    public PlayerInventory inventory() {
        return this.bukkitPlayer().getInventory();
    }

    @Override
    public Location location() {
        return this.bukkitPlayer().getLocation();
    }

    @Override
    public com.velocitypowered.api.proxy.Player velocityPlayer() {
        return VelocityBootstrap.instance().proxyServer().getPlayer(this.uuid()).orElse(null);
    }

    @Override
    public boolean isOnline() {
        return this.velocityPlayer() != null;
    }

    @Override
    public int coins() {
        return this.coins;
    }

    @Override
    public void coins(int coins) {
        this.coins = coins;
    }

    @Override
    public int onlineTime() {
        return this.onlineTime;
    }

    @Override
    public void onlineTime(int onlineTime) {
        this.onlineTime = onlineTime;
    }

    @Override
    public int playTime() {
        return this.playTime;
    }

    @Override
    public void playTime(int playTime) {
        this.playTime = playTime;
    }

    @Override
    public int nickState() {
        return this.nickState;
    }

    @Override
    public void nickState(int nickState) {
        this.nickState = nickState;
    }

    @Override
    public void message(Component message) {
        if (Core.instance().isPaper()) {
            this.bukkitPlayer().sendMessage(message);
        } else {
            this.velocityPlayer().sendMessage(message);
        }
    }

    @Override
    public void pMessage(Component message) {
        this.message(MiniMessage.miniMessage().deserialize("<dark_gray>[<aqua>Core<dark_gray>] <white>").append(message));
    }

    @Override
    public void usage(String usage) {
        this.message(MiniMessage.miniMessage().deserialize("<dark_gray>[<aqua>Core<dark_gray>] ")
                .append(Component.translatable("message.usage"))
                .append(Component.text(" ", NamedTextColor.DARK_GRAY))
                .append(Component.text(usage, NamedTextColor.AQUA)));
    }

    @Override
    public void usage(Component prefix, String usage) {
        this.message(Component.text("[", NamedTextColor.DARK_GRAY)
                .append(prefix)
                .append(Component.text("] ", NamedTextColor.DARK_GRAY))
                .append(Component.translatable("message.usage"))
                .append(Component.text(" ", NamedTextColor.DARK_GRAY))
                .append(Component.text(usage, prefix.color())));
    }

    @Override
    public void noPerms() {
        this.message(Component.text("Unknown command. Type \"/help\" for help."));
    }

    @Override
    public void notOnline(String target) {
        var targetPlayer = Core.instance().corePlayerProvider().corePlayer(target);
        if (targetPlayer == null) {
            this.pMessage(Component.translatable("message.player-not-found", Component.text(target)));
        } else {
            this.pMessage(Component.translatable("message.player-not-online", targetPlayer.displayName()));
        }
    }

    @Override
    public void sendActionBar(Component message) {
        if (this.bukkitPlayer() == null) {
            this.actionBarTask.cancel();
            return;
        }
        this.bukkitPlayer().sendActionBar(message);
    }

    @Override
    public void sendActionBarPermanent(Component message) {
        this.actionBarTask = Core.instance().coreTask().repeatAsync(() -> this.sendActionBar(message), 0, 20);
    }

    @Override
    public void setTablist(boolean ingame) {
        // todo
        throw new NotImplementedException("Not implemented yet.");
    }

    @Override
    public void sendTitle(@NotNull Component title, @NotNull Component subtitle, int fadeIn, int stay, int fadeOut) {
        var times = Title.Times.times(Duration.ofSeconds(fadeIn), Duration.ofSeconds(stay), Duration.ofSeconds(fadeOut));
        if (fadeIn == -1 || stay == -1 || fadeOut == -1) {
            times = Title.DEFAULT_TIMES;
        }
        this.bukkitPlayer().showTitle(Title.title(title, subtitle, times));
    }

    @Override
    public void resetTitle() {
        this.bukkitPlayer().resetTitle();
    }

    private void setDatabaseValues() {
        var player = this.repository.query().match("uuid", this.uniqueId).findFirst();
        if (player != null) {
            this.coins = player.coins;
            this.onlineTime = player.onlineTime;
            this.playTime = player.playTime;
            this.nickState = player.nickState;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ICorePlayer player)) {
            return false;
        }
        return this.uuid().equals(player.uuid()) && this.name.equals(player.name());
    }
}
