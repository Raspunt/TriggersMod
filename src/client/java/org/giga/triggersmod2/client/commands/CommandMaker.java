package org.giga.triggersmod2.client.commands;

import java.util.ArrayList;

public class CommandMaker {

    private TriggerGUICommand triggerGUICommand = new TriggerGUICommand();

    private void init_commands() {
        triggerGUICommand.setCommandName("open_trigger_menu");
    }

    public void register_commands() {
        init_commands();

        ArrayList<Runnable> commands = new ArrayList<>();

        commands.add(() -> triggerGUICommand.register());

        for (Runnable command : commands) {
            command.run();
        }
    }
}
