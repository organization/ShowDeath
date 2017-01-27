package io.murye.showdeath;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerDeathEvent;
import cn.nukkit.plugin.PluginBase;

/**
 * Created by murye on 1/22/17.
 */
public class ShowDeath extends PluginBase implements Listener {
    @Override
    public void onEnable() {
        Server.getInstance().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void handlePlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        new DeathTag(player);
    }
}
