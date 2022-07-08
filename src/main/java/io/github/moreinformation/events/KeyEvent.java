package io.github.moreinformation.events;

import io.github.moreinformation.Key;
import io.github.moreinformation.Main;
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
        boolean pressed = e.getAction() == GLFW.GLFW_PRESS;
        Key.getKeys(pressed).forEach(key -> {
            key.setPressed(pressed);
            MinecraftForge.EVENT_BUS.post(new KeyEvent(key, e.getAction()));
        });
    }
}
