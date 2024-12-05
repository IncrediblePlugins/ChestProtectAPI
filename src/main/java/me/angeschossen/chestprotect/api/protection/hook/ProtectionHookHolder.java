package me.angeschossen.chestprotect.api.protection.hook;

import me.angeschossen.chestprotect.api.protection.world.ProtectionWorld;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Protections that implement this interface can be managed by 3rd party plugins.
 * See {@link ProtectionHook} for more information.
 */
public interface ProtectionHookHolder {

    ProtectionWorld getWorld();
    int getX();
    int getY();
    int getZ();
    long getId();

    /**
     * Remove the current protection hook.
     */
    void removeProtectionHook();

    /**
     * Set the protection hook.
     *
     * @param protectionHook the hook
     */
    void setProtectionHook(@NotNull ProtectionHook protectionHook);

    /**
     * Get info about the current protection hook.
     *
     * @return will also return something if the 3rd party plugin currently isn't enabled
     */
    @Nullable
    ProtectionHook.Data getProtectionHookData();

    /**
     * Get the registered protection hook.
     *
     * @return null, if no hook set, the 3rd party plugin isn't enabled or hasn't called {@link ProtectionHook#register()}
     */
    @Nullable
    ProtectionHook getRegisteredProtectionHook();
}
