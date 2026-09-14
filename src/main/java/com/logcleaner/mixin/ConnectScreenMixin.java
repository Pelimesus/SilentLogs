package com.logcleaner.mixin;

import net.minecraft.client.gui.screen.multiplayer.ConnectScreen;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ConnectScreen.class)
public class ConnectScreenMixin {

	@Redirect(
			method = "*",
			at = @At(
					value = "INVOKE",
					target = "Lorg/slf4j/Logger;info(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V"
			)
	)
	private static void redirectConnectLog(Logger logger, String message, Object arg1, Object arg2) {
		if (message != null && message.contains("Connecting to")) {
			// Подменяем имя хоста на funtime.su
			logger.info(message, "funtime.su", arg2);
		} else {
			logger.info(message, arg1, arg2);
		}
	}
}