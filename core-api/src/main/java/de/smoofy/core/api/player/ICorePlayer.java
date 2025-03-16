/*
 * Copyright ©️
 * @author - Smoofy
 * @GitHub - https://github.com/Smoofy19
 * Created - 23.10.24, 13:13
 */

package de.smoofy.core.api.player;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.UUID;

public interface ICorePlayer {

    /**
     * Get the name of the player.
     *
     * @return the player's name
     */
    String name();

    /**
     * Get the UUID of the player.
     *
     * @return the player's UUID
     */
    UUID uuid();

    /**
     * Get the Luckperms User of the player.
     *
     * @return the luckperms User
     */
    Optional<User> user();

    /**
     * Get the Luckperms Group of the player.
     *
     * @return the luckperms Group
     */
    Optional<Group> group();

    /**
     * Get the display name of the player.
     *
     * @return the player's display name
     */
    Component displayName();

    /**
     * Get the color of the player.
     *
     * @return the player's color
     */
    NamedTextColor color();

    /**
     * Get the bukkit player.
     *
     * @return {@link Player}
     */
    Optional<Player> bukkitPlayer();

    /**
     * Get the inventory of the player.
     *
     * @return the player's inventory
     */
    PlayerInventory inventory();

    /**
     * Get the location of the player.
     *
     * @return the player's location
     */
    Location location();

    /**
     * Get the velocity player.
     *
     * @return {@link com.velocitypowered.api.proxy.Player}
     */
    Optional<com.velocitypowered.api.proxy.Player> velocityPlayer();

    /**
     * Check if the player is online.
     *
     * @return true if the player is online, otherwise false
     */
    boolean isOnline();

    /**
     * Get the coins of the player.
     *
     * @return the player's coins
     */
    int coins();

    /**
     * Set the coins of the player.
     *
     * @param coins the coins
     */
    void coins(int coins);

    /**
     * Get the online time of the player.
     *
     * @return the player's online time
     */
    int onlineTime();

    /**
     * Set the online time of the player.
     *
     * @param onlineTime the online time
     */
    void onlineTime(int onlineTime);

    /**
     * Get the play time of the player.
     *
     * @return the player's play time
     */
    int playTime();

    /**
     * Set the play time of the player.
     *
     * @param playTime the play time
     */
    void playTime(int playTime);

    /**
     * Get the nick state of the player.
     *
     * @return the player's nick state
     */
    int nickState();

    /**
     * Set the nick state of the player.
     *
     * @param nickState the nick state
     */
    void nickState(int nickState);

    /**
     * Send a message to the player.
     *
     * @param message the message
     */
    void message(Component message);

    /**
     * Send a message with 'Core' prefix to the player.
     *
     * @param message the message
     */
    void pMessage(Component message);

    /**
     * Send the usage message to the player.
     *
     * @param usage the usage
     */
    void usage(String usage);

    /**
     * Send the usage message with prefix to the player.
     *
     * @param prefix the prefix
     * @param usage the usage
     */
    void usage(Component prefix, String usage);

    /**
     * Send the no permission message to the player.
     */
    void noPerms();

    /**
     * Send the not online message to the player.
     *
     * @param target the target
     */
    void notOnline(String target);

    /**
     * Send an action bar message to the player.
     *
     * @param message the message
     */
    void sendActionBar(Component message);

    /**
     * Send a permanent action bar message to the player.
     *
     * @param message the message
     */
    void sendActionBarPermanent(Component message);

    /**
     * Set the tablist of the player.
     *
     * @param minigame true if minigame tablist, otherwise false
     */
    void setTablist(boolean minigame);

    /**
     * Send a title to the player.
     *
     * @param title the title
     * @param subtitle the subtitle
     * @param fadeIn the fade in time in seconds
     * @param stay the stay time in seconds
     * @param fadeOut the fade out time in seconds
     */
    void sendTitle(@NotNull Component title, @NotNull Component subtitle, int fadeIn, int stay, int fadeOut);

    /**
     * Reset the title of the player.
     */
    void resetTitle();

}
