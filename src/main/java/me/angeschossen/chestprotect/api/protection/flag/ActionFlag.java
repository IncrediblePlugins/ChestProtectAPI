package me.angeschossen.chestprotect.api.protection.flag;

import com.github.angeschossen.pluginframework.api.player.PlayerData;
import org.apache.commons.lang3.ArrayUtils;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public class ActionFlag extends com.github.angeschossen.pluginframework.api.flags.roles.ActionFlag {

    public ActionFlag(@NotNull Plugin plugin, @NotNull String name) {
        super(plugin, name);
    }

    /**
     * Get the permission to bypass restrictions enforced by the flag.
     *
     * @return bypass permission
     */
    @NotNull
    @Override
    public String getBypassPermission() {
        return "chestprotect.bypass." + toString().toLowerCase();
    }

    @NotNull
    @Override
    public String getTogglePermission() {
        return "chestprotect.setting." + toString().toLowerCase();
    }

    @Override
    protected @Nullable String getMessageKey() {
        return "noaccess.action";
    }
}
