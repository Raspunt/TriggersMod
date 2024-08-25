package org.giga.triggersmod2.client.commands;

public interface CommandInter {
    String getCommandName();
    void setCommandName(String name);
    void action();
    void register();

}
