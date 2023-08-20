package d.e.cooldownhelper.events;

import d.e.cooldownhelper.CooldownHelper;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;
import org.bukkit.Material;

public class CobwebCooldownListener implements Listener {

    private final JavaPlugin plugin;

    public CobwebCooldownListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCobwebPlace(BlockPlaceEvent event) {
        FileConfiguration config = plugin.getConfig();
        Player player = event.getPlayer();
        if (event.getBlockPlaced().getType() == Material.COBWEB) {
            int extendedCooldownTicks = config.getInt("cobweb-cooldown");

            // Apply extended cooldown to cobweb placement
            player.setCooldown(Material.COBWEB, extendedCooldownTicks);
        }
    }
}
