/*
 * Copyright ©️
 * @author - Smoofy
 * @GitHub - https://github.com/Smoofy19
 * Created - 23.10.24, 13:13
 */

package de.smoofy.core.base.paper.modules.hologram;

import de.smoofy.core.api.Core;
import de.smoofy.core.api.module.hologram.IHologram;
import de.smoofy.core.api.player.ICorePlayer;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;

import java.util.List;

public class HologramImpl implements IHologram {

    private final String key;
    private final List<Component> text;
    private final Location location;

    public HologramImpl(String key, List<Component> text, Location location) {
        this.key = key;
        this.text = text;
        this.location = location;
        Core.instance().hologramProvider().cache(this);
    }

    @Override
    public void spawn(ICorePlayer player) {

    }

    @Override
    public void destroy(ICorePlayer player) {

    }

    @Override
    public String key() {
        return this.key;
    }

    @Override
    public List<Component> text() {
        return this.text;
    }

    @Override
    public Location location() {
        return this.location;
    }
}
