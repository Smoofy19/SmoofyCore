/*
 * Copyright ©️
 * @author - Smoofy
 * @GitHub - https://github.com/Smoofy19
 * Created - 23.10.24, 13:13
 */

package de.smoofy.core.examples.modules.hologram;

import de.smoofy.core.api.Core;
import de.smoofy.core.api.player.ICorePlayer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

import java.util.Arrays;

public class HologramExample {

    public void testSpawn(ICorePlayer player) {
        var hologram = Core.instance().hologramProvider().create("test", Arrays.asList(
                Component.text("Hallo", NamedTextColor.RED),
                Component.text("Welt", NamedTextColor.BLUE)
        ), player.bukkitPlayer().get().getLocation());
        hologram.spawn(player);
    }

    public void testDestroy(ICorePlayer player) {
        Core.instance().hologramProvider().hologram("test").destroy(player);
    }

}
