package io.github.moreInformation.events;

import io.github.moreInformation.Key;
import io.github.moreInformation.Main;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@AllArgsConstructor
@Getter
@Mod.EventBusSubscriber(modid = Main.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class KeyEvent extends Event {
    private final Key key;

    /**
     * Integer representing the key's action.
     *
     * @see org.lwjgl.glfw.GLFW#GLFW_PRESS
     * @see org.lwjgl.glfw.GLFW#GLFW_RELEASE
     * @see org.lwjgl.glfw.GLFW#GLFW_REPEAT
     */
    private final int action;

    @SubscribeEvent
    public static void onKeyPress(InputEvent.KeyInputEvent e) {
        Key.getKeys(e.getKey(), e.getModifiers())
                .forEach(key -> {
                    key.setActive(e.getAction() == GLFW.GLFW_PRESS);
                    MinecraftForge.EVENT_BUS.post(new KeyEvent(key, e.getAction()));
                });
    }

    @SubscribeEvent
    public static void onKeyPress(KeyEvent e) {
        if (e.getKey() != Key.FREE_CAM)
            return;
        System.out.println("pressed key");
    }
}
