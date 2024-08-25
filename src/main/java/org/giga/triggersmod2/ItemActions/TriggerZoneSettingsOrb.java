package org.giga.triggersmod2.ItemActions;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;


public class TriggerZoneSettingsOrb extends Item {


    public TriggerZoneSettingsOrb(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {



        return super.use(world, user, hand);
    }
}
