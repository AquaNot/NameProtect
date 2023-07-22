package me.aqua.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import me.aqua.Init;

public class NameProtectCommand {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)ClientCommandManager.literal("nameprotect").then(ClientCommandManager.literal("toggle").executes(ctx -> Init.toggleEnabled()))).then(ClientCommandManager.literal("change").then(ClientCommandManager.argument("newname", (ArgumentType)StringArgumentType.greedyString()).executes(ctx -> Init.changeName(StringArgumentType.getString((CommandContext)ctx, "newname"))))));
    }
}
