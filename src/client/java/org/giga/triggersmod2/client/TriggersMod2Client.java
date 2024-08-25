package org.giga.triggersmod2.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.util.math.MatrixStack;
import org.giga.triggersmod2.client.commands.CommandMaker;

import java.util.logging.Logger;

import static org.giga.triggersmod2.TriggersMod2.LOGGER;

public class TriggersMod2Client implements ClientModInitializer {

    private CommandMaker commandMaker = new CommandMaker();
    public static final Logger LOGGER = Logger.getLogger(TriggersMod2Client.class.getName());
    public static final String MOD_ID = "triggersmod2";


    @Override
    public void onInitializeClient() {
        commandMaker.register_commands();
    }


}
