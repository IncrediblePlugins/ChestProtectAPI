package me.angeschossen.chestprotect.api.player.flag;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * Used to customize a players experience.
 */
public enum PlayerFlag {

    /**
     * Dont disable lock mode until "/cp lock" or "/cp exit" is executed again.
     */
    PERSISTENT_LOCK(0),
    /**
     * Dont disable trust mode until "/cp lock" or "/cp exit" is executed again.
     */
    PERSISTENT_TRUST(1),
    /**
     * Automatically lock blocks upon placement.
     */
    AUTO_LOCK(2),
    /**
     * Get information about a protection when interacting with it.
     */
    NOTIFICATIONS(3);

    private static final Map<Integer, PlayerFlag> map = new HashMap<>();

    static {
        for (PlayerFlag s : values()) {
            map.put(s.id, s);
        }
    }

    public final int id;

    PlayerFlag(int id) {
        this.id = id;
    }

    /**
     * Get permission required to toggle the flag.
     *
     * @return permission required to toggle the flag
     */
    @NotNull
    public String getTogglePermission() {
        return "chestprotect.setting.player." + toString();
    }

    /**
     * Get a flag by its numerical id.
     *
     * @param id the numerical id
     * @return null, if no flags with that id
     */
    public static PlayerFlag getById(int id) {
        return map.get(id);
    }
}
