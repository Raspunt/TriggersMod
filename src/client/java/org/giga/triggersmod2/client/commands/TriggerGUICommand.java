package org.giga.triggersmod2.client.commands;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import org.giga.triggersmod2.client.Screens.TriggerGuiScreen;

import java.util.logging.Logger;

import static org.giga.triggersmod2.client.TriggersMod2Client.LOGGER;

public class TriggerGUICommand implements CommandInter {

    private String commandName;
    private  MinecraftClient client = MinecraftClient.getInstance();

    @Override
    public void setCommandName(String name) {
        this.commandName = name;
    }

    @Override
    public String getCommandName() {
        return this.commandName;
    }

    @Override
    public void action() {
        try {
            LOGGER.info("Opening TriggerGuiScreen...");
            client.setScreen(new TriggerGuiScreen(Text.literal("Trigger GUI")));
            LOGGER.info("TriggerGuiScreen opened successfully." );
        } catch (Exception e) {
            LOGGER.severe("Error while opening TriggerGuiScreen: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void register() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(ClientCommandManager.literal(getCommandName()).executes(context -> {
                action();
                return 1;
            }));
        });
    }
}
