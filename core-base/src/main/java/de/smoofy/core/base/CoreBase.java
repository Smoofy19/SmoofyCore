/*
 * Copyright ©️
 * @author - Smoofy
 * @GitHub - https://github.com/Smoofy19
 * Created - 23.10.24, 13:13
 */

package de.smoofy.core.base;

import de.smoofy.core.api.Core;
import de.smoofy.core.api.config.IConfig;
import de.smoofy.core.api.fetcher.IUUIDFetcher;
import de.smoofy.core.api.game.IGameConfiguration;
import de.smoofy.core.api.game.countdown.ICountdownProvider;
import de.smoofy.core.api.game.phase.IPhaseHandler;
import de.smoofy.core.api.localization.ILocalize;
import de.smoofy.core.api.logger.ILogger;
import de.smoofy.core.api.message.IMessageBuilder;
import de.smoofy.core.api.module.hologram.IHologramProvider;
import de.smoofy.core.api.module.tasks.ICoreTask;
import de.smoofy.core.api.player.ICorePlayerProvider;
import de.smoofy.core.api.time.ITimeHandler;
import de.smoofy.core.base.config.ConfigImpl;
import de.smoofy.core.base.database.DatabaseProvider;
import de.smoofy.core.base.fetcher.UUIDFetcherImpl;
import de.smoofy.core.base.localize.LocalizeImpl;
import de.smoofy.core.base.logger.LoggerImpl;
import de.smoofy.core.base.message.MessageBuilderImpl;
import de.smoofy.core.base.paper.game.GameConfigurationImpl;
import de.smoofy.core.base.paper.game.countdown.CountdownProviderImpl;
import de.smoofy.core.base.paper.game.phase.PhaseHandlerImpl;
import de.smoofy.core.base.paper.modules.hologram.HologramProviderImpl;
import de.smoofy.core.base.paper.modules.task.CoreTaskImpl;
import de.smoofy.core.base.player.CorePlayerProviderImpl;
import de.smoofy.core.base.time.TimeHandlerImpl;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.Locale;

public class CoreBase extends Core {

    private final boolean paper;

    // Global
    private final IUUIDFetcher uuidFetcher;
    private final ICorePlayerProvider corePlayerProvider;
    private final ITimeHandler timeHandler;

    // Paper
    private ICountdownProvider countdownProvider;
    private IPhaseHandler phaseHandler;
    private IHologramProvider hologramProvider;
    private ICoreTask coreTask;

    public CoreBase(boolean paper) {
        Core.instance(this);
        Core.instance().localize().init(this.getClass(), "Messages", Locale.GERMANY);

        this.paper = paper;

        new DatabaseProvider();

        this.uuidFetcher = new UUIDFetcherImpl();
        this.corePlayerProvider = new CorePlayerProviderImpl();
        this.timeHandler = new TimeHandlerImpl();
        if (paper) {
            this.countdownProvider = new CountdownProviderImpl();
            this.phaseHandler = new PhaseHandlerImpl();
            this.hologramProvider = new HologramProviderImpl();
            this.coreTask = new CoreTaskImpl();
        }
    }

    @Override
    public boolean isPaper() {
        return this.paper;
    }

    @Override
    public IConfig config(File directory, String fileName) {
        return new ConfigImpl(directory, fileName);
    }

    @Override
    public IUUIDFetcher uuidFetcher() {
        return this.uuidFetcher;
    }

    @Override
    public ICountdownProvider countdownProvider() {
        return this.countdownProvider;
    }

    @Override
    public IPhaseHandler phaseHandler() {
        return this.phaseHandler;
    }

    @Override
    public IGameConfiguration gameConfiguration(int minPlayers, int maxPlayers, int teams, int teamSize) {
        return new GameConfigurationImpl(minPlayers, maxPlayers, teams, teamSize);
    }

    @Override
    public ILocalize localize() {
        return new LocalizeImpl();
    }

    @Override
    public ILogger logger(@Nullable JavaPlugin javaPlugin) {
        return new LoggerImpl(javaPlugin);
    }

    @Override
    public IMessageBuilder messageBuilder(String text) {
        return new MessageBuilderImpl(text);
    }

    @Override
    public IHologramProvider hologramProvider() {
        return this.hologramProvider;
    }

    @Override
    public ICoreTask coreTask() {
        return this.coreTask;
    }

    @Override
    public ICorePlayerProvider corePlayerProvider() {
        return this.corePlayerProvider;
    }

    @Override
    public ITimeHandler timeHandler() {
        return this.timeHandler;
    }
}
