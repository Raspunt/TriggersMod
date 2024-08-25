package org.giga.triggersmod2.triggerZones;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

public class TriggerZone {


    private final long id;
    private final Box triggerZone;
    private final String triggerZoneName;
    private final String createdByPlayer;
    private final String command;


    public TriggerZone(long id, Box box, String triggerZoneName, String createdByPlayer, String command) {
        this.id = id;
        this.triggerZone = box;
        this.triggerZoneName = triggerZoneName;
        this.createdByPlayer = createdByPlayer;
        this.command = command;
    }

    public boolean inRange(Vec3d playerPos) {
        double x = playerPos.x;
        double y = playerPos.y;
        double z = playerPos.z;

        return x >= triggerZone.minX && x <= triggerZone.maxX &&
                y >= triggerZone.minY && y <= triggerZone.maxY &&
                z >= triggerZone.minZ && z <= triggerZone.maxZ;
    }

    public void action(PlayerEntity player){
        player.sendMessage(Text.of(command), true);
    }





    public long getId() {
        return id;
    }

    public Box getTriggerZone() {
        return triggerZone;
    }

    public String getTriggerZoneName() {
        return triggerZoneName;
    }

    public String getCreatedByPlayer() {
        return createdByPlayer;
    }







}
