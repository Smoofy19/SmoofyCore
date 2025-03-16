/*
 * Copyright ©️
 * @author - Smoofy
 * @GitHub - https://github.com/Smoofy19
 * Created - 23.10.24, 13:13
 */

package de.smoofy.core.examples.commands;

import de.smoofy.core.api.Core;
import de.smoofy.core.examples.builder.InventoryBuilderExample;
import de.smoofy.core.examples.builder.ItemBuilderExample;
import de.smoofy.core.examples.config.ConfigExample;
import de.smoofy.core.examples.fetcher.UUIDFetcherExample;
import de.smoofy.core.examples.game.countdown.CountdownExample;
import de.smoofy.core.examples.game.phase.PhaseExample;
import de.smoofy.core.examples.localize.LocalizeExample;
import de.smoofy.core.examples.logger.LoggerExample;
import de.smoofy.core.examples.message.MessageBuilderExample;
import de.smoofy.core.examples.modules.hologram.HologramExample;
import de.smoofy.core.examples.player.CorePlayerExample;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TestCommand implements CommandExecutor, TabCompleter {

    private final InventoryBuilderExample inventoryBuilderExample = new InventoryBuilderExample();
    private final ItemBuilderExample itemBuilderExample = new ItemBuilderExample();
    private final ConfigExample configExample = new ConfigExample();
    private final UUIDFetcherExample uuidFetcherExample = new UUIDFetcherExample();
    private final CountdownExample countdownExample = new CountdownExample();
    private final PhaseExample phaseExample = new PhaseExample();
    private final LocalizeExample localizeExample = new LocalizeExample();
    private final LoggerExample loggerExample = new LoggerExample();
    private final MessageBuilderExample messageBuilderExample = new MessageBuilderExample();
    private final HologramExample hologramExample = new HologramExample();
    private final CorePlayerExample corePlayerExample = new CorePlayerExample();

    public TestCommand() {
        Bukkit.getPluginCommand("test").setExecutor(this);
        Bukkit.getPluginCommand("test").setTabCompleter(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player player)) {
            commandSender.sendMessage("§cDu musst ein Spieler sein");
            return false;
        }
        var corePlayer = Core.instance().corePlayerProvider().corePlayer(player);
        switch (args[0].toLowerCase()) {
            case "inventory" -> inventoryBuilderExample.test(player);
            case "item" -> itemBuilderExample.test(player);
            case "config-write" -> configExample.testSaveObject();
            case "config-read" -> configExample.testLoadObject(player);
            case "uuid" -> uuidFetcherExample.testUUID(player, args[1]);
            case "name" -> uuidFetcherExample.testName(player, args[1]);
            case "countdown" -> countdownExample.test(args[1]);
            case "phase" -> phaseExample.test();
            case "localize" -> localizeExample.test(player);
            case "log" -> loggerExample.test();
            case "message" -> messageBuilderExample.test(player);
            case "hologram-spawn" -> hologramExample.testSpawn(corePlayer);
            case "hologram-destroy" -> hologramExample.testDestroy(corePlayer);
            case "coreplayer" -> corePlayerExample.test(corePlayer, args[1]);
        }
        player.sendRichMessage("<green>Test ausgeführt");
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            return List.of("inventory", "item", "config-write", "config-read", "uuid", "name", "countdown", "phase",
                    "localize", "log", "message", "hologram-spawn", "hologram-destroy", "coreplayer");
        }
        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("uuid")) {
                return Bukkit.getOnlinePlayers().stream().map(Player::getName).toList();
            }
            if (args[0].equalsIgnoreCase("name")) {
                return Bukkit.getOnlinePlayers().stream().map(player -> player.getUniqueId().toString()).toList();
            }
            if (args[0].equalsIgnoreCase("countdown")) {
                return List.of("start", "stop", "infinity", "count", "time");
            }
            if (args[0].equalsIgnoreCase("coreplayer")) {
                return List.of("message", "pmessage", "usage", "pusage", "noperms", "notonline", "actionbar",
                        "pactionbar", "title1", "title2", "title3", "title4", "resettitle", "addcoins", "coins", "players");
            }
        }
        return List.of();
    }
}