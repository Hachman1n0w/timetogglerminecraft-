package com.timetoggler;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeTogglerMod implements ClientModInitializer {

    public static final String MOD_ID = "timetoggler";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    // Ob wir gerade "Nacht" simulieren (nur visuell)
    public static boolean isNightMode = false;

    // Gespeicherter Originalwert des Tages (zum Wiederherstellen)
    public static long savedTime = -1;

    private static KeyBinding toggleTimeKey;

    @Override
    public void onInitializeClient() {
        // Tastenbelegung registrieren – Standard: N
        toggleTimeKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.timetoggler.toggle",       // Übersetzungsschlüssel
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_N,                // Standard: N
                "category.timetoggler.general"  // Kategorie in den Einstellungen
        ));

        // Jeden Client-Tick prüfen ob die Taste gedrückt wurde
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (toggleTimeKey.wasPressed()) {
                if (client.world == null) continue;

                isNightMode = !isNightMode;

                if (isNightMode) {
                    // Originalzeit merken und auf Mitternacht setzen
                    savedTime = client.world.getTimeOfDay();
                    client.world.setTimeOfDay(18000); // 18000 = Mitternacht
                    client.player.sendMessage(
                            Text.literal("§8[TimeToggler] §7Nachtmodus aktiviert 🌙"),
                            true // Action-Bar-Nachricht
                    );
                } else {
                    // Originalzeit wiederherstellen
                    if (savedTime >= 0) {
                        client.world.setTimeOfDay(savedTime);
                    } else {
                        client.world.setTimeOfDay(6000); // Mittag als Fallback
                    }
                    client.player.sendMessage(
                            Text.literal("§e[TimeToggler] §fTagmodus aktiviert ☀"),
                            true
                    );
                }
            }
        });

        LOGGER.info("TimeToggler Mod geladen! Drücke N (oder deinen Hotkey) zum Wechseln.");
    }
}
