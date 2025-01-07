package me.angeschossen.chestprotect.api.handler;

import me.angeschossen.chestprotect.api.ChestProtectAPI;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * This class is not intended for direct usage and might change at any time.
 */
public class APIHandler {

    private static APIHandler instance;
    private final ChestProtectAPI api;
    private final Factory factory;

    @NotNull
    public ChestProtectAPI getAPI() {
        return api;
    }

    private APIHandler(@NotNull ChestProtectAPI api, Factory factory) {
        this.api = api;
        this.factory = factory;
    }

    public static APIHandler getInstance() {
        return instance;
    }

    public Factory getFactory() {
        return factory;
    }

    public static void init(@NotNull ChestProtectAPI api, @NotNull Factory factory) {
        Objects.requireNonNull(api);
        if (instance != null) {
            throw new IllegalStateException("Already initialized.");
        }

        instance = new APIHandler(api, factory);
    }
}
