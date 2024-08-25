package org.giga.triggersmod2.triggerZones;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TriggerZoneStorage {

    private final Gson gson;
    private List<TriggerZone> triggerZones;

    private BlockPos firstBlock;
    private BlockPos secondBlock;
    private final String triggerZoneStorageFilename = "trigger_zones.json";

    public BlockPos getFirstBlock() {
        return firstBlock;
    }

    public BlockPos getSecondBlock() {
        return secondBlock;
    }

    public TriggerZoneStorage(){
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.triggerZones = new ArrayList<>();
        this.loadTriggerZonesFromFile();
    }

    public void setFirstBlock(BlockPos blockPos){
        this.firstBlock = blockPos;
    }
    public void setSecondBlock(BlockPos blockPos){
        this.secondBlock = blockPos;
    }

    public Box mergeBlockPos2Box(){
        if (firstBlock == null || secondBlock == null) {
            throw new IllegalStateException("Both blocks must be set before creating a trigger zone.");
        }

        double x1 = Math.min(firstBlock.getX(), secondBlock.getX());
        double y1 = Math.min(firstBlock.getY(), secondBlock.getY());
        double z1 = Math.min(firstBlock.getZ(), secondBlock.getZ());

        double x2 = Math.max(firstBlock.getX(), secondBlock.getX());
        double y2 = Math.max(firstBlock.getY(), secondBlock.getY());
        double z2 = Math.max(firstBlock.getZ(), secondBlock.getZ());

        return new Box(x1, y1, z1, x2, y2, z2);
    }

    public void createTriggerZone(Box box, PlayerEntity player) {

        long id = this.triggerZones.size();
        String triggerZoneName = "trigger_zone_" + id;

        TriggerZone triggerZone = new TriggerZone(id, box, triggerZoneName, player.getName().getString(),"");
        triggerZones.add(triggerZone);

        this.saveTriggerZonesToFile();
    }

    public void clearBlocks(){
        this.firstBlock = null;
        this.secondBlock = null;
    }

    public List<TriggerZone> getTriggerZones() {
        this.loadTriggerZonesFromFile();
        return this.triggerZones;
    }

    private void loadTriggerZonesFromFile() {
        try (FileReader reader = new FileReader(triggerZoneStorageFilename)) {
            this.triggerZones = gson.fromJson(reader, new TypeToken<List<TriggerZone>>(){}.getType());
            if (this.triggerZones == null) {
                this.triggerZones = new ArrayList<>();
            }
        } catch (IOException e) {
            e.printStackTrace();
            this.triggerZones = new ArrayList<>();
        }
    }

    private void saveTriggerZonesToFile() {
        String json = gson.toJson(triggerZones);
        try (FileWriter writer = new FileWriter(triggerZoneStorageFilename)) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
