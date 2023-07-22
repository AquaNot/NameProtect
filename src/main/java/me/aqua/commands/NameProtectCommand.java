package me.aqua.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import me.aqua.Init;

public class NameProtectCommand {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register((ClientCommandManager.literal("nameprotect").then(ClientCommandManager.literal("toggle").executes(ctx -> Init.toggleEnabled()))).then(ClientCommandManager.literal("change").then(ClientCommandManager.argument("newname", StringArgumentType.greedyString()).executes(ctx -> Init.changeName(StringArgumentType.getString(ctx, "newname"))))));
    }
}
