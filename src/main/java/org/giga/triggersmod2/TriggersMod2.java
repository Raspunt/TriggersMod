package org.giga.triggersmod2;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.server.ServerStartCallback;
import net.fabricmc.fabric.api.event.server.ServerTickCallback;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import org.giga.triggersmod2.triggerZones.TriggerZoneRenderer;
import org.giga.triggersmod2.triggerZones.TriggerZoneStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class TriggersMod2 implements ModInitializer {

    public static final String MOD_ID = "triggersmod2";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    private static TriggerZoneStorage triggerZoneStorage;
    private final Map<ServerWorld, TriggerZoneRenderer> renderers = new HashMap<>();

    @Override
    public void onInitialize() {
        LOGGER.info("TriggersMod2 has been initialized successfully!");
        ModItems.initialize();

        triggerZoneStorage = new TriggerZoneStorage();

        ServerStartCallback.EVENT.register(this::onServerStart);
        ServerTickCallback.EVENT.register(this::serverTickAction);
    }

    private void onServerStart(MinecraftServer server) {
        for (ServerWorld world : server.getWorlds()) {
            TriggerZoneRenderer renderer = createTriggerZoneRenderer(world);
            renderers.put(world, renderer);
        }
    }

    private void serverTickAction(MinecraftServer server) {
        for (ServerWorld world : server.getWorlds()) {
            TriggerZoneRenderer renderer = renderers.get(world);
            if (renderer != null) {
                renderer.renderTriggerZone();
            }
        }
    }

    public static TriggerZoneRenderer createTriggerZoneRenderer(ServerWorld world) {
        return new TriggerZoneRenderer(world, triggerZoneStorage);
    }
}
