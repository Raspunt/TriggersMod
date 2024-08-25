package org.giga.triggersmod2.triggerZones;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.giga.triggersmod2.TriggersMod2.LOGGER;

public class TriggerZoneRenderer {

    private final World world;
    private final List<TriggerZone> triggerZones;
    private final Map<PlayerEntity, TriggerZone> playersInTrigger;

    public TriggerZoneRenderer(World world, TriggerZoneStorage triggerZoneStorage) {
        this.world = world;
        this.triggerZones = triggerZoneStorage.getTriggerZones();
        this.playersInTrigger = new HashMap<>();
    }

    public void renderTriggerZone() {
        if (triggerZones.isEmpty()) {
            LOGGER.info("No trigger zones to render.");
            return;
        }

        Map<PlayerEntity, TriggerZone> playersToRemove = new HashMap<>(playersInTrigger);

        for (PlayerEntity player : this.world.getPlayers()) {
            Vec3d playerPos = player.getPos();

            for (TriggerZone zone : this.triggerZones) {
                boolean isInZone = zone.inRange(playerPos);

                if (isInZone) {
                    if (!playersInTrigger.containsKey(player) || playersInTrigger.get(player) != zone) {
                        playersInTrigger.put(player, zone);
                        player.sendMessage(Text.of("Вы находитесь внутри зоны " + zone.getTriggerZoneName()), true);
                        LOGGER.info("Player " + player.getName().getString() + " is inside zone " + zone.getTriggerZoneName());
                    }
                } else if (playersInTrigger.containsKey(player) && playersInTrigger.get(player) == zone) {
                    playersInTrigger.remove(player);
                    player.sendMessage(Text.of("Вы вышли находитесь внутри зоны " + zone.getTriggerZoneName()), true);
                    LOGGER.info("Player " + player.getName().getString() + " has left zone " + zone.getTriggerZoneName());
                }
            }
        }

        for (PlayerEntity player : playersToRemove.keySet()) {
            if (!playersInTrigger.containsKey(player)) {
                LOGGER.info("Player " + player.getName().getString() + " has left zone " + playersToRemove.get(player).getTriggerZoneName());
            }
        }
    }
}

