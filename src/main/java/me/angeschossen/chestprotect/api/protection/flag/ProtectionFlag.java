package me.angeschossen.chestprotect.api.protection.flag;

import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * Flags used to restrict more general and world related events.
 */
public enum ProtectionFlag {
    REDSTONE(0, true),
    PUBLIC_TAKE(1, false),
    PUBLIC_INSERT(2, false),
    HOLOGRAM(3, true);

    private static final Map<Integer, ProtectionFlag> map = new HashMap<>();

    static {
        for (ProtectionFlag s : values()) {
            map.put(s.id, s);
        }
    }

    private final int id;
    public final boolean defState;

    ProtectionFlag(int id, boolean defState) {
        this.id = id;
        this.defState = defState;
    }

    /**
     * Get a flag by its numerical id.
     *
     * @param id the numerical id
     * @return null, if no flag belongs to this id
     */
    @Nullable
    public static ProtectionFlag getById(int id) throws IllegalArgumentException {
        return map.get(id);
    }

    /**
     * Get the numerical id.
     *
     * @return numerical id.
     */
    public int getId() {
        return id;
    }
}
