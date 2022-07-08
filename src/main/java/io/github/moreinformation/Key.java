package io.github.moreinformation;

import com.mojang.blaze3d.platform.InputConstants;
import io.github.moreinformation.events.KeyEvent;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Mod.EventBusSubscriber(modid = Main.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public enum Key {
    FREE_CAM("free_cam", InputConstants.KEY_V, KeyMapping.CATEGORY_GAMEPLAY);

    private final KeyMapping key;
    @Setter
    private boolean pressed = false;
    private boolean active = false;

    Key(String name, int key, String category) {
        this.key = new KeyMapping("key." + Main.MOD_ID  + "." + name, key, category);
        ClientRegistry.registerKeyBinding(this.key);
    }


    public static List<Key> getKeys(boolean pressed) {
        return Arrays.stream(values())
                .filter(key1 -> key1.isPressed() != pressed)
                .filter(key1 -> key1.getKey().isDown() == pressed)
                .collect(Collectors.toList());
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onKeyPress(KeyEvent e) {
        Key key = e.getKey();
        if (key.isPressed() != (e.getAction() == GLFW.GLFW_PRESS))
            return;
        key.active = !key.isActive();
    }
}
