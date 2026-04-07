package com.timetoggler.mixin;

import com.timetoggler.TimeTogglerMod;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientWorld.class)
public class ClientWorldMixin {

    /**
     * Überschreibt die zurückgegebene Tageszeit clientseitig.
     * Nur für Rendering (Himmel, Beleuchtung, Sterne) – nicht für Spiellogik.
     */
    @Inject(method = "getTimeOfDay", at = @At("RETURN"), cancellable = true)
    private void overrideTimeOfDay(CallbackInfoReturnable<Long> cir) {
        if (TimeTogglerMod.isNightMode) {
            cir.setReturnValue(18000L); // Mitternacht
        }
    }
}
