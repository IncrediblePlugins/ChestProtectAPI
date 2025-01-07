package me.angeschossen.chestprotect.api.protection.hook;

import com.github.angeschossen.pluginframework.api.utils.Checks;
import me.angeschossen.chestprotect.api.handler.APIHandler;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.Timestamp;

/**
 * Allows 3rd party plugins to create and manage protections.
 * A protection that was created by a 3rd party plugin will be deleted, if the
 * 3rd party plugin hasn't been enabled for 30 days.
 */
public abstract class ProtectionHook {

    private final @NotNull Plugin plugin;
    private final @NotNull Data data;

    /**
     * Create an instance of your protection hook. You should only create the hook once.
     * Don't forget to call {@link #register()} to register your hook.
     *
     * @param plugin your plugin
     */
    public ProtectionHook(@NotNull Plugin plugin) {
        this(plugin, APIHandler.getInstance().getFactory().getProtectionHookData(plugin));
    }

    /**
     * Create an instance of your protection hook. You should only create the hook once.
     * Don't forget to call {@link #register()} to register your hook.
     *
     * @param plugin your plugin
     * @param data   data containing last seen and main class path
     */
    protected ProtectionHook(@NotNull Plugin plugin, @NotNull Data data) {
        this.plugin = Checks.requireNonNull(plugin, "your plugin");
        this.data = Checks.requireNonNull(data, "data");
    }

    /**
     * Get info about this hook.
     *
     * @return never null
     */
    public final @NotNull Data getData() {
        return data;
    }

    /**
     * Get the ID of this hook.
     *
     * @return id represents the main class path of the plugin
     */
    public final @NotNull String getId() {
        return data.getId();
    }

    /**
     * Get the plugin of this hook.
     *
     * @return 3rd party plugin
     */
    public final @NotNull Plugin getPlugin() {
        return plugin;
    }

    /**
     * Holds information about hook.
     */
    public static final class Data {
        private final @NotNull Timestamp timestamp;
        private final @NotNull String id;

        /**
         * Create instance.
         *
         * @param id        main class path of plugin
         * @param timestamp last seen timestamp
         */
        public Data(@NotNull String id, @NotNull Timestamp timestamp) {
            this.timestamp = Checks.requireNonNull(timestamp, "timestamp");
            this.id = id;
        }

        /**
         * Get the registered hook.
         *
         * @return null, if 3rd party plugin not enabled or hasn't hooked into ChestProtect via {@link #register()} yet.
         */
        public @Nullable ProtectionHook getRegisteredHook() {
            return APIHandler.getInstance().getFactory().getProtectionHookById(id);
        }

        /**
         * Get the id of the hook.
         *
         * @return represents main class path of the 3rd party plugin
         */
        public @NotNull String getId() {
            return id;
        }

        /**
         * Set last seen time. If the 3rd party plugin hasn't been enabled for 30 days,
         * all protections of the hook will be deleted.
         *
         * @param time time in milliseconds
         */
        public void setTime(long time) {
            this.timestamp.setTime(time);
        }

        /**
         * Get last seen timestamp.
         *
         * @return updated each time {@link #register()} is called.
         */
        @NotNull
        public Timestamp getTimestamp() {
            return timestamp;
        }
    }

    /**
     * Check if this item belongs to the 3rd party item.
     * This will exclude the block placement of auto lock.
     *
     * @param itemStack the block that is being placed
     * @return true, if this item belongs to the 3rd party plugin
     */
    public abstract boolean hasItem(@NotNull ItemStack itemStack);

    /**
     * Register this protection hook. Needs to be called each time the server enables.
     */
    public final void register() {
        APIHandler.getInstance().getFactory().registerProtectionHook(this);
    }
}
