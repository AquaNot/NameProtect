package me.aqua;

import com.mojang.brigadier.CommandDispatcher;
import me.aqua.commands.NameProtectCommand;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.text.Text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Init {
    INSTANCE;

    public enum NameProtectMode {
        OFF, SELF, EVERYONE
    }

    private static NameProtectMode mode;
    private static String replacement;

    public void init() {
        ClientCommandRegistrationCallback.EVENT.register(Init::registerCommands);
    }

    public static void registerCommands(
            CommandDispatcher<FabricClientCommandSource> dispatcher,
            CommandRegistryAccess registryAccess
    ) {
        NameProtectCommand.register(dispatcher);
    }

    public static NameProtectMode getMode() {
        return mode;
    }

    public static int setMode(String name) {
        NameProtectMode newMode;
        switch (name.toLowerCase()) {
            case "off":
                newMode = NameProtectMode.OFF;
                break;
            case "self":
                newMode = NameProtectMode.SELF;
                break;
            case "everyone":
                newMode = NameProtectMode.EVERYONE;
                break;
            default:
                MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(
                        Text.literal("§4Invalid mode. Use 'off', 'self', or 'everyone'.")
                );
                return 0;
        }
        mode = newMode;
        MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(
                Text.literal("NameProtect mode set to §b" + mode)
        );
        return 0;
    }

    public static int changeName(String replacee) {
        if (!replacee.matches("^[a-zA-Z0-9_]+$")) {
            MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(
                    Text.literal("§4Username cannot contain spaces or symbols.")
            );
            return 0;
        }

        replacement = replacee;
        MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(
                Text.literal("Name set to §b" + replacement)
        );
        return 0;
    }

    public static String replaceName(String string) {
        if (string == null || mode == NameProtectMode.OFF) return string;

        String localName = MinecraftClient.getInstance().getSession().getUsername();

        switch (mode) {
            case SELF:
                return string.replaceAll("\\b" + Pattern.quote(localName) + "\\b", replacement);

            case EVERYONE:
                if (MinecraftClient.getInstance().world == null) return string;
                for (var player : MinecraftClient.getInstance().world.getPlayers()) {
                    String name = player.getName().getString();
                    if (!name.equals(replacement)) {
                        string = string.replaceAll("\\b" + Pattern.quote(name) + "\\b", replacement);
                    }
                }
                return string;

            case OFF:
            default:
                return string;
        }
    }

    static {
        mode = NameProtectMode.OFF;
        replacement = "Player";
    }
}
