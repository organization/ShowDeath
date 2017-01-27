package io.murye.showdeath;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.scheduler.AsyncTask;
import milk.tagmanager.text.Tag;

/**
 * Created by murye on 1/27/17.
 */
public class DeathTag extends Tag{
    protected Long since = new Long(0);
    protected Player player;

    public DeathTag(Player player) {
        super(player.getPosition(), "0분 전에 " + player.getName() + "이(가) 사망", new Long(60 * 10));
        this.player = player;
        Server.getInstance().getScheduler().scheduleRepeatingTask(new AsyncTask() {
            @Override
            public void onRun() {
                if(Server.getInstance().getOnlinePlayers().isEmpty()) {
                    return;
                }

                for(Tag tag : DeathTag.list.values()) tag.onUpdate();
            }
        }, 20 * 60, true);
    }

    public void onUpdate() {
        this.setText(++this.since + "분 전에 " + this.player.getName() + "이(가) 사망");
    }
}
