package pl.karol.plugin.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import pl.karol.plugin.util.TextUtil;

public class PlayerPreCommandListener implements Listener {
    @EventHandler
    public void manipulate(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();
        String[] splittedMessage = message.split(" ");
        String[] commands =  new String[]{"/help", "/?", "/pl", "/plugins", "/ver", "/version", "/about", "/icanhasbukkit", "/bukkit:pl", "/bukkit:plugins", "/bukkit:ver", "/bukkit:version", "/bukkit:about", "/bukkit:?", "/bukkit:help", "/me", "/minecraft:me"};
        if (this.containsIgnoreCase(commands, splittedMessage[0])) {
            event.setCancelled(true);
            player.sendMessage(TextUtil.translate("" +
                    "\n &8&m(---(-----)---)" +
                    "\n &fSerwer zabezpieczony dzięki: " +
                    "\n &bkarol@bagno.ltd" +
                    "\n &ebagno.ltd"));
        }
    }

    public boolean containsIgnoreCase(String[] board, String string) {
        for (String otherstring : board) {
            if (!otherstring.equalsIgnoreCase(string)) continue;
            return true;
        }
        return false;
    }
}

