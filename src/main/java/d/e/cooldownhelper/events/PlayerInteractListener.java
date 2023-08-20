package d.e.cooldownhelper.events;

import d.e.cooldownhelper.CooldownHelper;
import org.bukkit.Material;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInteractListener implements Listener {

    private final CooldownHelper ch;
    public PlayerInteractListener(CooldownHelper cooldownHelper) {
        this.ch = cooldownHelper;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack mainHandItem = player.getInventory().getItemInMainHand();
        ItemStack offHandItem = player.getInventory().getItemInOffHand();
        int extendedCooldownTicks = ch.getConfig().getInt("pearl-cooldown");

        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if ((mainHandItem.getType() == Material.ENDER_PEARL || offHandItem.getType() == Material.ENDER_PEARL)
                    && player.getCooldown(Material.ENDER_PEARL) == 0) {
                player.setCooldown(Material.ENDER_PEARL, extendedCooldownTicks);
                if (mainHandItem.getType() == Material.ENDER_PEARL) {
                    int heldAmount = mainHandItem.getAmount();
                    if (heldAmount >= 1) {
                        mainHandItem.setAmount(heldAmount - 1);
                    } else {
                        player.getInventory().removeItem(mainHandItem);
                    }
                } else {
                    int heldAmount = offHandItem.getAmount();
                    if (heldAmount >= 1) {
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

}