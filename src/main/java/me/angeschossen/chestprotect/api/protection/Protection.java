package me.angeschossen.chestprotect.api.protection;

import com.github.angeschossen.pluginframework.api.trusted.RoleHolder;
import com.github.angeschossen.pluginframework.api.trusted.group.Group;
import me.angeschossen.chestprotect.api.player.ProtectPlayer;
import me.angeschossen.chestprotect.api.protection.enums.Type;
import me.angeschossen.chestprotect.api.protection.flag.ProtectionFlag;
import me.angeschossen.chestprotect.api.protection.hook.ProtectionHookHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.UUID;

public interface Protection extends Nameable, RoleHolder, ProtectionHookHolder {


    /**
     * Get the type of protection.
     *
     * @return block or entity
     */
    @NotNull ProtectionType getProtectionType();

    /**
     * Get the block x coordinate.
     *
     * @return block x
     */
    int getX();

    /**
     * Get the block y coordinate.
     *
     * @return block y
     */
    int getY();

    /**
     * Get the block z coordinate.
     *
     * @return block z
     */
    int getZ();

    /**
     * Get the type of protection.
     *
     * @return protection type
     */
    @NotNull
    Type type();

    /**
     * Get group that this protection belongs to.
     *
     * @return null, if no group set
     */
    @Nullable Group getGroup();

    /**
     * Delete the protection
     *
     * @param deleter The player that deletes the protection, will receive an unlock message.
     */
    void delete(@Nullable ProtectPlayer deleter, boolean msg) throws IllegalStateException;

    void delete(@Nullable ProtectPlayer deleter) throws IllegalStateException;

    /**
     * Get players that are trusted directly to this protection and aren't
     * trusted via a group {@link Group}.
     *
     * @return Players directly trusted at this protection
     */
    @NotNull
    Collection<UUID> getTrustedWithoutGroup();

    /**
     * Check if a protection has a flag set.
     *
     * @param flag the flag
     * @return false, if flag not set
     */
    boolean hasFlag(@NotNull ProtectionFlag flag);

    /**
     * Toggle a flag for this protection.
     *
     * @param flag the flag to toggle
     * @return new state of the flag
     */
    boolean toggleFlag(@NotNull ProtectionFlag flag);

    /**
     * Untrust a player that is trusted directly to this protection.
     *
     * @param playerUUID The player to untrust
     */
    boolean untrustPlayer(@NotNull UUID playerUUID);
}
