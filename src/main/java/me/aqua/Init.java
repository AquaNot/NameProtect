package me.aqua;

import com.mojang.brigadier.CommandDispatcher;
import me.aqua.commands.NameProtectCommand;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.text.Text;

public enum Init {
    INSTANCE;

    private static boolean enabled;
    private static String replacement;

    public void init() {
        ClientCommandRegistrationCallback.EVENT.register(Init::registerCommands);
    }

    public static void registerCommands(CommandDispatcher<FabricClientCommandSource> dispatcher, CommandRegistryAccess registryAccess) {
        NameProtectCommand.register(dispatcher);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public static int toggleEnabled() {
        boolean bl = enabled = !enabled;
        if (enabled) {
            MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(Text.literal("Toggled NameProtect §aON"));
        }
        if (!enabled) {
            MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(Text.literal("Toggled NameProtect §cOFF"));
        }
        return 0;
    }

    public static int changeName(String replacee) {
        replacement = replacee;
        MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(Text.literal("Name set to §b" + replacement));
        return 0;
    }

    public static String replaceName(String string) {
        if (string != null && enabled) {
            return string.replace(MinecraftClient.getInstance().getSession().getUsername(), replacement);
        }
        return string;
    }

    static {
        enabled = false;
        replacement = "Player";
    }
}
