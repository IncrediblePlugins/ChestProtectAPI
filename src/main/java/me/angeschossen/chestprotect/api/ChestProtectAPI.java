package me.angeschossen.chestprotect.api;

import com.github.angeschossen.pluginframework.api.blockutil.BlockPosition;
import me.angeschossen.chestprotect.api.handler.APIHandler;
import me.angeschossen.chestprotect.api.player.ProtectOfflinePlayer;
import me.angeschossen.chestprotect.api.player.ProtectPlayer;
import me.angeschossen.chestprotect.api.protection.ProtectionManager;
import me.angeschossen.chestprotect.api.protection.block.BlockProtection;
import me.angeschossen.chestprotect.api.protection.entity.EntityProtection;
import me.angeschossen.chestprotect.api.protection.flag.FlagRegistry;
import me.angeschossen.chestprotect.api.protection.world.ProtectionWorld;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface ChestProtectAPI {

    /**
     * Get implementation of the API.
     *
     * @return API implementation
     */
    @NotNull
    static ChestProtectAPI getInstance() {
        return Objects.requireNonNull(APIHandler.getInstance(), "ChestProtect isn't loaded yet. Please access the API, after ChestProtect is loaded. It doesn't need to be fully enabled.").getAPI();
    }

    /**
     * Get world related data.
     *
     * @return all worlds that have protections enabled and are loaded
     */
    @NotNull
    Collection<? extends ProtectionWorld> getProtectionWorlds();

    /**
     * Get information about an offline player. For online players, use {@link #getProtectPlayer(Player)} instead.
     *
     * @param uuid the player's uuid
     * @return never null
     */
    @NotNull CompletableFuture<? extends ProtectOfflinePlayer> getProtectOfflinePlayer(@NotNull UUID uuid);

    /**
     * Get protection manager.
     *
     * @return Initialized protection manager
     */
    @NotNull ProtectionManager getProtectionManager();

    /**
     * Get online player. For offline players use {@link #getProtectOfflinePlayer(UUID)} instead.
     *
     * @param player The player
     * @return null, if not online and already unloaded.
     */
    @Nullable ProtectPlayer getProtectPlayer(@NotNull Player player);

    /**
     * Get world that contains all loaded protections.
     *
     * @param world World
     * @return null, if protections aren't enabled in that world or the world isn't loaded anymore.
     */
    @Nullable ProtectionWorld getProtectionWorld(@NotNull World world);

    /**
     * Get entity protection.
     *
     * @param entity The entity
     * @return null, if not protected
     */
    @Nullable EntityProtection getEntityProtection(@NotNull Entity entity);

    /**
     * Get block protection.
     *
     * @param block the block
     * @return null, if not protected or chunk unloaded
     */
    @Nullable BlockProtection getBlockProtectionByBlock(@NotNull Block block);

    /**
     * Get block protection.
     *
     * @param position position of the block
     * @return null, if not protected or chunk unloaded
     */
    @Nullable BlockProtection getBlockProtectionByPos(@NotNull BlockPosition position);
}
