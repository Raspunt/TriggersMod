package org.giga.triggersmod2.ItemActions;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.giga.triggersmod2.triggerZones.TriggerZoneStorage;

public class TriggerZoneOrb extends Item {
    TriggerZoneStorage triggerZoneStorage;
    public TriggerZoneOrb(Settings settings) {
        super(settings);
        this.triggerZoneStorage = new TriggerZoneStorage();
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        PlayerEntity user = context.getPlayer();
        BlockPos pos = context.getBlockPos();

        if (!world.isClient && user != null) {
            if (this.triggerZoneStorage.getFirstBlock() == null) {

                this.triggerZoneStorage.setFirstBlock(pos);
                user.sendMessage(Text.of("Первый блок установлен."), false);
            } else if (this.triggerZoneStorage.getSecondBlock() == null) {

                this.triggerZoneStorage.setSecondBlock(pos);
                user.sendMessage(Text.of("Второй блок установлен. Создание триггерной зоны..."), false);

                Box box = this.triggerZoneStorage.mergeBlockPos2Box();

                this.triggerZoneStorage.createTriggerZone(box,user);

                this.triggerZoneStorage.clearBlocks();
            }

            String message = String.format("блок x=%d, y=%d, z=%d", pos.getX(), pos.getY(), pos.getZ());
            user.sendMessage(Text.of(message), false);
        }

        return ActionResult.PASS;
    }



}
