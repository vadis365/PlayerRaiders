package com.gendeathrow.pmobs.commands.common;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

import com.gendeathrow.pmobs.commands.Base_Command;
import com.gendeathrow.pmobs.core.RaidersCore;
import com.gendeathrow.pmobs.handlers.RaiderManager;

public class AddNewPlayer extends Base_Command
{

	@Override
	public String getCommand() 
	{
		return "raider";
	}
	
	@Override
	public String getUsageSuffix()
	{
		return "<add, remove> <playerName> <weight>";
	}
	
	@Override
	public boolean validArgs(String[] args)
	{
		return args.length >= 3;
	}

	@Override
	public List<String> autoComplete(ICommandSender sender, String[] args)
	{
		if(args.length == 2)
		{
			return new ArrayList<String>(){{add("add");add("remove");}};
		}
		else if(args.length == 3)
		{
			return new ArrayList<String>(){{add("playerName");}};
		}
		else if(args.length == 4)
		{
			return new ArrayList<String>(){{add("10");}};
		}

		return new ArrayList<String>();
	}
	
	@Override
	public void runCommand(CommandBase command, ICommandSender sender, String[] args)
	{
		
		if(args.length < 3)
		{
			return;
		}

		String ownerName = args[2];
		
		if(args[1].equalsIgnoreCase("add"))
		{
			int weight = 10;
			if(args.length > 4)
			{
				
				try
				{
					weight = Integer.parseInt(args[3]);
				}catch(NumberFormatException e)
				{
					RaidersCore.logger.error(e);
				}
			}
			
			if(RaiderManager.raidersList.containsKey(ownerName))
			{
				sender.addChatMessage(new TextComponentString(ownerName + " already exist!"));
			}
			else
			{
				RaiderManager.addNewRaider(ownerName, weight);	
				sender.addChatMessage(new TextComponentString(ownerName + " was added!"));
			}
			
		}
		else if(args[1].equalsIgnoreCase("remove"))
		{
			if(RaiderManager.raidersList.containsKey(ownerName))
			{
				RaiderManager.removeRaider(ownerName);	
				sender.addChatMessage(new TextComponentString(ownerName + " was removed!"));
			}
			else
			{
				sender.addChatMessage(new TextComponentString(ownerName + " doesn't exist!"));
			}
		}
	}


}
