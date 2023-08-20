package d.e.cooldownhelper.events;

import d.e.cooldownhelper.CooldownHelper;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.entity.Player;
import org.bukkit.Material;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.inventory.ItemStack;

public class GoldenAppleCooldownListener implements Listener {
    private final CooldownHelper ch;

    public GoldenAppleCooldownListener(CooldownHelper cooldownHelper) {
        this.ch = cooldownHelper;
    }
    @EventHandler
    public void onPlayerEatGoldenApple(PlayerItemConsumeEvent event) {
        FileConfiguration config = ch.getConfig();
        Player player = event.getPlayer();
        if (event.getItem().getType() == Material.GOLDEN_APPLE) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 1));
            player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 2400, 0));
            ItemStack goldenApple = new ItemStack(Material.GOLDEN_APPLE);

            for (ItemStack item : player.getInventory().getContents()) {
                if (item != null && item.isSimilar(goldenApple)) {
                    int amount = item.getAmount();
                    if (amount >= 1) {
                        item.setAmount(amount - 1);
                    } else {
                        player.getInventory().removeItem(item);
                    }
                    break;
                }
            }
            // Apply correct saturation and hunger values
            player.setFoodLevel(Math.min(player.getFoodLevel() + 4, 20)); // gives 4 hunger
            player.setSaturation(Math.min(player.getSaturation() + 9.6F, player.getFoodLevel())); // gives 9.6 sat
            int extendedCooldownTicks = config.getInt("gapple-cooldown"); // 10 sec
            player.setCooldown(Material.GOLDEN_APPLE, extendedCooldownTicks);
        }
    }
}