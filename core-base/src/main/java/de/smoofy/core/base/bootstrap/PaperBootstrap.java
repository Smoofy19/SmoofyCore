/*
 * Copyright ©️
 * @author - Smoofy
 * @GitHub - https://github.com/Smoofy19
 * Created - 23.10.24, 13:13
 */

package de.smoofy.core.base.bootstrap;

import de.smoofy.core.base.CoreBase;
import de.smoofy.core.base.paper.listener.AsyncPlayerPreLoginListener;
import de.smoofy.core.base.paper.listener.ItemBuilderListener;
import de.smoofy.core.base.paper.listener.PlayerJoinListener;
import de.smoofy.core.base.paper.listener.PlayerQuitListener;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
@Accessors(fluent = true)
public class PaperBootstrap extends JavaPlugin {

    @Getter
    @Accessors(fluent = true)
    private static PaperBootstrap instance;

    @Override
    public void onEnable() {
        instance = this;

        new CoreBase(true);

        this.registerListener();
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    private void registerListener() {
        new AsyncPlayerPreLoginListener();
        new ItemBuilderListener();
        new PlayerJoinListener();
        new PlayerQuitListener();
    }
}
