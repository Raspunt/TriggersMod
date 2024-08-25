package org.giga.triggersmod2;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.giga.triggersmod2.ItemActions.TriggerZoneOrb;
import org.giga.triggersmod2.ItemActions.TriggerZoneSettingsOrb;

import static org.giga.triggersmod2.TriggersMod2.LOGGER;
import static org.giga.triggersmod2.TriggersMod2.MOD_ID;

public class ModItems {

    public static final Item TRIGGER_ZONE_ORB = registerItem("trigger_zone_orb", new TriggerZoneOrb(new FabricItemSettings()));
    public static final Item TRIGGER_ZONE_SETTINGS_ORB = registerItem("trigger_zone_settings_orb", new TriggerZoneSettingsOrb(new FabricItemSettings()));


    private static Item registerItem(String name, Item item) {
        LOGGER.info("Registering item with name: {}", name);
        return Registry.register(Registries.ITEM, new Identifier(MOD_ID, name), item);
    }

    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {
        entries.add(TRIGGER_ZONE_ORB);
        entries.add(TRIGGER_ZONE_SETTINGS_ORB);
    }

    public static void initialize() {
        LOGGER.info("Initializing ModItems...");
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);
        LOGGER.info("ModItems initialization complete.");
    }
}
