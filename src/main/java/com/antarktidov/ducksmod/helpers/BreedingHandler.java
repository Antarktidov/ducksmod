package com.antarktidov.ducksmod.helpers;

import com.antarktidov.ducksmod.DucksMod;
import com.antarktidov.ducksmod.entity.custom.DuckEntity;
import com.antarktidov.ducksmod.entity.custom.DuckVariant;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;

@Mod.EventBusSubscriber(modid = DucksMod.MOD_ID)
public class BreedingHandler {

    @SubscribeEvent
    public static void onBabySpawn(BabyEntitySpawnEvent event) {
        Animal parentA = (Animal) event.getParentA();
        Animal parentB = (Animal) event.getParentB();

        if (!(parentA instanceof DuckEntity) || !(parentB instanceof DuckEntity)) {
            return;
        }

        if( (((DuckEntity)parentA).getVariant() == DuckVariant.MALE_MALLARD && ((DuckEntity)parentB).getVariant() == DuckVariant.MALE_MALLARD)
        || (((DuckEntity)parentA).getVariant() == DuckVariant.FEMALE_MALLARD && ((DuckEntity)parentB).getVariant() == DuckVariant.FEMALE_MALLARD) ){
            event.setCanceled(true);
        }
    }
}
