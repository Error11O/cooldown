package d.e.ben;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.event.block.Action;
import org.bukkit.entity.EnderPearl;

public final class Ben extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new GoldenAppleCooldownListener(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack mainHandItem = player.getInventory().getItemInMainHand();
        ItemStack offHandItem = player.getInventory().getItemInOffHand();
        int extendedCooldownTicks = 200; // 10 secs

        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if ((mainHandItem.getType() == Material.ENDER_PEARL || offHandItem.getType() == Material.ENDER_PEARL)
                    && player.getCooldown(Material.ENDER_PEARL) == 0) {
                player.setCooldown(Material.ENDER_PEARL, extendedCooldownTicks);
                if (mainHandItem.getType() == Material.ENDER_PEARL) {
                    int heldAmount = mainHandItem.getAmount();
                    if (heldAmount > 1) {
                        mainHandItem.setAmount(heldAmount - 1);
                    } else {
                        player.getInventory().removeItem(mainHandItem);
                    }
                } else {
                    int heldAmount = offHandItem.getAmount();
                    if (heldAmount > 1) {
                        offHandItem.setAmount(heldAmount - 1);
                    } else {
                        player.getInventory().removeItem(offHandItem);
                    }
                }
                EnderPearl enderPearl = player.launchProjectile(EnderPearl.class);
                enderPearl.setShooter(player);
            }
        }
    }
    @EventHandler
    public void onPlayerEatGoldenApple(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        if (event.getItem().getType() == Material.GOLDEN_APPLE) {
            int extendedCooldownTicks = 200; // 10 sec
            player.setCooldown(Material.GOLDEN_APPLE, extendedCooldownTicks);

            event.setCancelled(true);
        }
    }
}
