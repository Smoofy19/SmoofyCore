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
import io.github.retrooper.packetevents.util.SpigotConversionUtil;
import me.tofaa.entitylib.wrapper.hologram.Hologram;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;

import java.util.List;

public class HologramImpl implements IHologram {

    private final String key;
    private final List<Component> text;
    private final Location location;

    private Hologram.Legacy hologram;

    public HologramImpl(String key, List<Component> text, Location location) {
        this.key = key;
        this.text = text;
        this.location = location;
        Core.instance().hologramProvider().cache(this);
    }

    @Override
    public void spawn(ICorePlayer player) {
        this.hologram = Hologram.legacy(SpigotConversionUtil.fromBukkitLocation(this.location), this.text);
        this.hologram.setLineOffset(-0.25f);
        this.hologram.show();
        this.hologram.addViewer(player.uuid());
    }

    @Override
    public void destroy(ICorePlayer player) {
        this.hologram.hide();
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
