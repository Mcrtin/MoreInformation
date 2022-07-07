package io.github.moreInformation;

import com.mojang.blaze3d.platform.InputConstants;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public enum Key {
    FREE_CAM("free_cam", InputConstants.KEY_V, KeyMapping.CATEGORY_GAMEPLAY);

    private final KeyMapping key;
    @Setter
    private boolean isActive = false;

    Key(String name, int key, String category) {
        this.key = new KeyMapping("key." + Main.MOD_ID  + "." + name, key, category);
        ClientRegistry.registerKeyBinding(this.key);
    }

    static void init(){}

    public static List<Key> getKeys(int key, int modifiers) {
        Set<KeyModifier> modifierSet = getModifiersByValue(modifiers);
        return Arrays.stream(values())
                .filter(key1 -> key1.getKey().getKey().getValue() == key)
                .filter(key1 -> modifierSet.contains(key1.getKey().getKeyModifier()))
                .collect(Collectors.toList());
    }

    private static Set<KeyModifier> getModifiersByValue(int modifiers) {
        Set<KeyModifier> modifierSet = new HashSet<>();
        if ((modifiers & GLFW.GLFW_MOD_SHIFT) == GLFW.GLFW_MOD_SHIFT)
            modifierSet.add(KeyModifier.SHIFT);
        if ((modifiers & GLFW.GLFW_MOD_CONTROL) == GLFW.GLFW_MOD_CONTROL)
            modifierSet.add(KeyModifier.CONTROL);
        if ((modifiers & GLFW.GLFW_MOD_ALT) == GLFW.GLFW_MOD_ALT)
            modifierSet.add(KeyModifier.ALT);
        return modifierSet;
    }
}
