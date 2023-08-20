package d.e.cooldownhelper.events;

import d.e.cooldownhelper.CooldownHelper;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class PlayerItemConsumeListener implements Listener {

    private final CooldownHelper ch;
    public PlayerItemConsumeListener(CooldownHelper cooldownHelper) {
        this.ch = cooldownHelper;
    }


    @EventHandler
    public void onPlayerEatGoldenApple(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        if (event.getItem().getType() == Material.GOLDEN_APPLE) {
            int extendedCooldownTicks = ch.getConfig().getInt("gapple-cooldown"); // 10 sec
            player.setCooldown(Material.GOLDEN_APPLE, extendedCooldownTicks);

            event.setCancelled(true);
        }
    }
}