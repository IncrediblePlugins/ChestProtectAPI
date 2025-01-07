package me.angeschossen.chestprotect.api.protection.flag;

import com.github.angeschossen.pluginframework.api.flags.roles.RoleFlag;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public interface FlagRegistry extends com.github.angeschossen.pluginframework.api.flags.FlagRegistry<ActionFlag, ManagementFlag> {

    @NotNull
    Collection<ManagementFlag> getManagementFlags();

    @NotNull
    Collection<ActionFlag> getActionFlags();

    <T extends RoleFlag> @NotNull T registerFlag(@NotNull T flag) throws IllegalArgumentException;
}
