package me.angeschossen.chestprotect.api.limits;

import com.github.angeschossen.pluginframework.api.limit.LimitModifier;
import com.github.angeschossen.pluginframework.api.limit.holder.LimitHolder;
import com.github.angeschossen.pluginframework.api.utils.Checks;
import com.github.angeschossen.pluginframework.api.utils.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public enum Limit implements com.github.angeschossen.pluginframework.api.limit.Limit {
    /**
     * Maximum amount of blocks a player can protect.
     */
    PLAYER_BLOCKS("chestprotect.blocks", LimitTarget.PLAYER),
    /**
     * Amount of blocks a player can protect for free.
     */
    PLAYER_BLOCKS_FREE("chestprotect.free.blocks", LimitTarget.PLAYER),
    /**
     * Amount of entities a player can protect.
     */
    PLAYER_ENTITIES("chestprotect.entities", LimitTarget.PLAYER),
    /**
     * Amount of entities a player can protect for free.
     */
    PLAYER_ENTITIES_FREE("chestprotect.free.blocks", LimitTarget.PLAYER),
    /**
     * Amount of groups a player can create.
     */
    PLAYER_GROUPS("chestprotect.groups"),
    /**
     * Amount of members a player can add to a protection or group.
     */
    PROTECTION_MEMBERS("chestprotect.members");

    private final @NotNull String permission;
    private static final Map<String, Limit> permissionToLimitMap = new HashMap<>();
    private final Set<com.github.angeschossen.pluginframework.api.limit.holder.LimitTarget> targets;
    private final Map<String, LimitModifier> modifiers = new HashMap<>();

    static {
        for (Limit value : Limit.values()) {
            Limit.permissionToLimitMap.put(StringUtils.toLowerCase(value.getPermission()), value);
        }
    }


    @Nullable
    public static Limit getByPermission(@NotNull String permission) {
        return Limit.permissionToLimitMap.get(StringUtils.toLowerCase(Checks.requireNonNull(permission, "permission")));
    }

    /**
     * Constructor
     *
     * @param permission permission node associated with the limit
     */
    Limit(@NotNull String permission, @NotNull LimitTarget... targets) {
        this.permission = Checks.requireNonNull(permission, "permission");
        this.targets = Set.of(Checks.requireNonNull(targets, "targets"));
    }

    public final boolean hasTarget(@NotNull com.github.angeschossen.pluginframework.api.limit.holder.LimitTarget target) {
        return targets.contains(target); // some limits should only be saved to lands or nations
    }

    @Override
    public int applyModifiers(@NotNull LimitHolder limitHolder, final int limit) {
        long value = limit;

        for (LimitModifier modifier : modifiers.values()) {
            value += modifier.getModifier(limitHolder);

            if (value >= Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
        }

        return (int) value;
    }

    @Override
    public void registerModifier(@NotNull LimitModifier... limitModifiers) {
        for (LimitModifier modifier : limitModifiers) {
            modifiers.put(StringUtils.toLowerCase(modifier.getId()), modifier);
        }
    }

    @Override
    public void unregisterModifier(@NotNull LimitModifier... limitModifiers) {
        for (LimitModifier modifier : limitModifiers) {
            modifiers.remove(StringUtils.toLowerCase(modifier.getId()));
        }
    }

    @Override
    public @NotNull Collection<@NotNull LimitModifier> getModifiers() {
        return modifiers.values();
    }

    @Override
    public @NotNull String getId() {
        return toString();
    }

    /**
     * Get the permission node associated with the limit.
     *
     * @return never null
     */
    public @NotNull String getPermission() {
        return permission;
    }
}
