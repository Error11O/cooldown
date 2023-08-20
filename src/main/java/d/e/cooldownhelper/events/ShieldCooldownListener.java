package d.e.cooldownhelper.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerAnimationType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;
import org.bukkit.Material;

public class ShieldCooldownListener implements Listener {

    private final JavaPlugin plugin;

    public ShieldCooldownListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerSwingSword(PlayerAnimationEvent event) {
        Player player = event.getPlayer();

        // Check if the animation is a swinging arm animation
        if (event.getAnimationType() == PlayerAnimationType.ARM_SWING) {
            int shieldCooldownTicks = 20; // 1 second in ticks

            // Apply shield cooldown
            player.setCooldown(Material.SHIELD, shieldCooldownTicks);
        }
    }
}

