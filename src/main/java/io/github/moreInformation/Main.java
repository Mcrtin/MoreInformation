package io.github.moreInformation;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod(Main.MOD_ID)
@Mod.EventBusSubscriber(modid = Main.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class Main {
    public static final String MOD_ID = "moreInformation";
    public static final Minecraft MC = Minecraft.getInstance();

    @SubscribeEvent
    public static void init(FMLClientSetupEvent e) {
        System.out.println("init-------------------------------------------------------------------------------------------------------------------------------------------------------------");
        Key.init();
    }
}
