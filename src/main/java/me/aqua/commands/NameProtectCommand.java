package me.aqua.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import me.aqua.Init;

public class NameProtectCommand {

    private static final SuggestionProvider<FabricClientCommandSource> MODE_SUGGESTIONS = (context, builder) -> {
        for (String mode : new String[]{"off", "self", "everyone"}) {
            builder.suggest(mode);
        }
        return builder.buildFuture();
    };

    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(
                ClientCommandManager.literal("nameprotect")
                        .then(
                                ClientCommandManager.literal("change")
                                        .then(
                                                ClientCommandManager.argument("newname", StringArgumentType.greedyString())
                                                        .executes(ctx ->
                                                                Init.changeName(
                                                                        StringArgumentType.getString(ctx, "newname")
                                                                )
                                                        )
                                        )
                        )
                        .then(
                                ClientCommandManager.literal("mode")
                                        .then(
                                                ClientCommandManager.argument("mode", StringArgumentType.word())
                                                        .suggests(MODE_SUGGESTIONS)
                                                        .executes(ctx ->
                                                                Init.setMode(
                                                                        StringArgumentType.getString(ctx, "mode")
                                                                )
                                                        )
                                        )
                        )
        );
    }
}
