package net.wijk.chest;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.WorldSavePath;

import static net.minecraft.server.command.CommandManager.argument;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WijkChestGit {

    @SuppressWarnings("unused")
    public static void register(CommandDispatcher<ServerCommandSource> serverCommandSourceCommandDispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        serverCommandSourceCommandDispatcher.register(CommandManager.literal("git").then(CommandManager.literal("status").executes(WijkChestGit::seeStatus)));

        serverCommandSourceCommandDispatcher.register(CommandManager.literal("git").then(CommandManager.literal("commit").then(argument("message", StringArgumentType.greedyString()).executes(WijkChestGit::commit))));
    }

    private static int commit(CommandContext<ServerCommandSource> context){
        final String message = StringArgumentType.getString(context, "message");

        ServerCommandSource source = context.getSource();
        File worldDir = source.getServer().getSavePath(WorldSavePath.ROOT).toFile();
        try {
            String output = runGitCommand(worldDir, "commit", "-m", message);
            for (String line : output.split("\n")) {
                source.sendFeedback(() -> Text.literal(line), false);
            }
        } catch (IOException e) {
            source.sendError(Text.literal("Error running git: " + e.getMessage()));
        }

        context.getSource().sendFeedback(() -> Text.literal(message), false);
        return 1;
    }

    private static int seeStatus(CommandContext<ServerCommandSource> context) {
        ServerCommandSource source = context.getSource();

        MinecraftServer server = Objects.requireNonNull(source.getPlayer()).getServer();
        assert server != null;
        Path worldPath = server.getSavePath(WorldSavePath.ROOT);
        source.sendFeedback(() -> Text.literal(worldPath.toString()), false);

        File worldDir = worldPath.toFile();
        try {
            String output = runGitCommand(worldDir, "status");
            for (String line : output.split("\n")) {
                source.sendFeedback(() -> Text.literal(line), false);
            }
        } catch (IOException e) {
            source.sendError(Text.literal("Error running git: " + e.getMessage()));
        }

        return 1;
    }

    private static String runGitCommand(File worldDir, String... args) throws IOException {
        List<String> command = new ArrayList<>();
        command.add("git");
        command.addAll(List.of(args));

        ProcessBuilder builder = new ProcessBuilder(command);
        builder.directory(worldDir);
        builder.redirectErrorStream(true);

        Process process = builder.start();
        StringBuilder output = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
        }

        return output.toString();
    }
}

        /*
        BlockPos playerPos = player.getBlockPos();
        String positionString = "(" + playerPos.getX() + ", " + playerPos.getY() + ", " + playerPos.getZ() + ")";

        context.getSource().sendFeedback(() -> Text.literal("Set Home at " + positionString), false);
        */