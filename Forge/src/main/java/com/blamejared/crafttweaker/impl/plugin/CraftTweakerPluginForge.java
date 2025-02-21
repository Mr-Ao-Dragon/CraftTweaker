package com.blamejared.crafttweaker.impl.plugin;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.CraftTweakerConstants;
import com.blamejared.crafttweaker.api.command.CommandUtilities;
import com.blamejared.crafttweaker.api.plugin.CraftTweakerPlugin;
import com.blamejared.crafttweaker.api.plugin.ICommandRegistrationHandler;
import com.blamejared.crafttweaker.api.plugin.ICraftTweakerPlugin;
import com.blamejared.crafttweaker.mixin.common.access.entity.AccessFakePlayerFactory;
import com.mojang.brigadier.Command;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.Optional;
import java.util.stream.Stream;

@CraftTweakerPlugin(CraftTweakerConstants.MOD_ID + ":builtin_forge")
public class CraftTweakerPluginForge implements ICraftTweakerPlugin {
    
    @Override
    public void registerCommands(ICommandRegistrationHandler handler) {
        
        handler.registerDump("fake_players", new TranslatableComponent("crafttweaker.command.description.dump.fake_players"), builder -> {
            builder.executes(context -> {
                
                Stream.concat(AccessFakePlayerFactory.crafttweaker$getFakePlayers().keySet()
                                .stream(), Stream.of(AccessFakePlayerFactory.crafttweaker$getMINECRAFT()))
                        .map(it -> it.getName() + " -> " + it.getId())
                        .forEach(CraftTweakerAPI.LOGGER::info);
                
                CommandUtilities.send(CommandUtilities.openingLogFile(new TranslatableComponent("crafttweaker.command.list.check.log", CommandUtilities.makeNoticeable(new TranslatableComponent("crafttweaker.command.misc.fake_players")), CommandUtilities.getFormattedLogFile()).withStyle(ChatFormatting.GREEN)), context.getSource()
                        .getPlayerOrException());
                
                return Command.SINGLE_SUCCESS;
            });
        });
        
        handler.registerDump("tool_tiers", new TranslatableComponent("crafttweaker.command.description.dump.tool_tiers"), builder -> {
            builder.executes(context -> {
                
                TierSortingRegistry.getSortedTiers().forEach(tier -> {
                    
                    Object toLog = TierSortingRegistry.getName(tier);
                    if(toLog == null) {
                        toLog = tier;
                    }
                    CraftTweakerAPI.LOGGER.info("{}", toLog);
                });
                
                CommandUtilities.send(CommandUtilities.openingLogFile(new TranslatableComponent("crafttweaker.command.list.check.log", CommandUtilities.makeNoticeable(new TranslatableComponent("crafttweaker.command.misc.tool_tiers")), CommandUtilities.getFormattedLogFile()).withStyle(ChatFormatting.GREEN)), context.getSource()
                        .getPlayerOrException());
                
                return Command.SINGLE_SUCCESS;
            });
        });
    }
    
}
