package de.smoofy.core.base;

/*
 * Copyright ©️
 * @author - Smoofy
 * @GitHub - https://github.com/Smoofy19
 * Created - 27.09.24, 23:46
 */

import de.smoofy.core.api.Core;
import de.smoofy.core.api.fetcher.IUUIDFetcher;
import de.smoofy.core.api.localization.ILocalize;
import de.smoofy.core.api.logger.ILogger;
import de.smoofy.core.api.message.IMessageBuilder;
import de.smoofy.core.api.time.ITimeHandler;
import de.smoofy.core.base.fetcher.UUIDFetcherImpl;
import de.smoofy.core.base.localize.LocalizeImpl;
import de.smoofy.core.base.logger.LoggerImpl;
import de.smoofy.core.base.message.MessageBuilderImpl;
import de.smoofy.core.base.time.TimeHandlerImpl;

public class CoreBase extends Core {

    private final boolean paper;

    // Global
    private final IUUIDFetcher uuidFetcher;
    private final ILocalize localize;
    private final ILogger logger;
    private final ITimeHandler timeHandler;

    // Paper

    public CoreBase(boolean paper) {
        Core.instance(this);

        this.paper = paper;

        this.uuidFetcher = new UUIDFetcherImpl();
        this.localize = new LocalizeImpl();
        this.logger = new LoggerImpl();
        this.timeHandler = new TimeHandlerImpl();
    }

    @Override
    public boolean isPaper() {
        return this.paper;
    }

    @Override
    public IUUIDFetcher uuidFetcher() {
        return this.uuidFetcher;
    }

    @Override
    public ILocalize localize() {
        return this.localize;
    }

    @Override
    public ILogger logger() {
        return this.logger;
    }

    @Override
    public IMessageBuilder messageBuilder(String text) {
        return new MessageBuilderImpl(text);
    }

    @Override
    public ITimeHandler timeHandler() {
        return this.timeHandler;
    }
}