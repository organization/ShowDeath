package io.murye.showdeath;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.scheduler.AsyncTask;
import milk.tagmanager.text.Tag;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by murye on 1/27/17.
 */
public class DeathTag {
    public static Set<DeathTag> tags = new HashSet<>();
    protected Long since = new Long(0);
    protected Player player;
    protected Tag tag;

    public DeathTag(Player player) {
        this.tag = new Tag(player.getPosition(), "0분 전에 " + player.getName() + "이(가) 사망", new Long(60 * 10));
        this.player = player;
        DeathTag.tags.add(this);
        Server.getInstance().getScheduler().scheduleRepeatingTask(new AsyncTask() {
            @Override
            public void onRun() {
                for(DeathTag tag : DeathTag.tags) tag.onUpdate();
            }
        }, 20 * 60, true);
    }

    public void onUpdate() {
        this.tag.setText(++this.since + "분 전에 " + this.player.getName() + "이(가) 사망");
        if(this.since >= 30)
            DeathTag.tags.remove(this.tag);
    }
}
