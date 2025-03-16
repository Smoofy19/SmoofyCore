/*
 * Copyright ©️
 * @author - Smoofy
 * @GitHub - https://github.com/Smoofy19
 * Created - 23.10.24, 13:13
 */

package de.smoofy.core.examples.player;

import de.smoofy.core.api.Core;
import de.smoofy.core.api.player.ICorePlayer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class CorePlayerExample {

    public void test(ICorePlayer player, String test) {
        if (test.equalsIgnoreCase("message")) {
            player.message(Component.text("Test Message", NamedTextColor.GOLD));
        } else if (test.equalsIgnoreCase("pmessage")) {
            player.pMessage(Component.text("Test Prefix message", NamedTextColor.AQUA));
        } else if (test.equalsIgnoreCase("usage")) {
            player.usage("/test");
        } else if (test.equalsIgnoreCase("pusage")) {
            player.usage(Component.text("Test", NamedTextColor.DARK_RED), "/test");
        } else if (test.equalsIgnoreCase("noperms")) {
            player.noPerms();
        } else if (test.equalsIgnoreCase("notonline")) {
            player.notOnline("Smoofy19");
        } else if (test.equalsIgnoreCase("actionbar")) {
            player.sendActionBar(Component.text("Test Actionbar", NamedTextColor.BLUE));
        } else if (test.equalsIgnoreCase("pactionbar")) {
            player.sendActionBarPermanent(Component.text("Test Permanent Actionbar", NamedTextColor.RED));
        } else if (test.equalsIgnoreCase("title1")) {
            player.sendTitle(Component.text("Title 1"), Component.empty(), -1, -1, -1);
        } else if (test.equalsIgnoreCase("title2")) {
            player.sendTitle(Component.text("Title 2", NamedTextColor.GREEN),
                    Component.text("Subtitle 2", NamedTextColor.YELLOW), -1, -1, -1);
        } else if (test.equalsIgnoreCase("title3")) {
            player.sendTitle(Component.text("Title 3"), Component.empty(), 2, 4, 2);
        } else if (test.equalsIgnoreCase("title4")) {
            player.sendTitle(Component.text("Title 4"), Component.text("Subtitle 4"), 2, 4, 2);
        } else if (test.equalsIgnoreCase("resettitle")) {
            player.resetTitle();
        } else if (test.equalsIgnoreCase("addcoins")) {
            player.coins(player.coins() + 1);
        } else if (test.equalsIgnoreCase("coins")) {
            player.message(Component.text("Coins: " + player.coins(), NamedTextColor.GOLD));
        } else if (test.equalsIgnoreCase("players")) {
            for (ICorePlayer corePlayer : Core.instance().corePlayerProvider().corePlayers()) {
                player.message(Component.text(corePlayer.name()));
            }
        }
    }

}
