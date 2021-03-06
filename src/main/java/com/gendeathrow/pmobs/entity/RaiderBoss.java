package com.gendeathrow.pmobs.entity;

import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import com.gendeathrow.pmobs.client.RaidersSkinManager;
import com.gendeathrow.pmobs.entity.New.EntityRaiderBase;
import com.gendeathrow.pmobs.handlers.RaiderManager;
import com.google.common.collect.Iterables;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import com.mojang.authlib.properties.Property;

public class RaiderBoss extends EntityRaiderBase
{

	public RaiderBoss(World worldIn) 
	{
		super(worldIn);
	}

	@Override
    public ResourceLocation getLocationSkin()
    {
		ResourceLocation resourcelocation =  DefaultPlayerSkin.getDefaultSkinLegacy();
		
		if (this.getPlayerProfile() != null && this.getPlayerProfile().getName() != null) 
		{
			Minecraft minecraft = Minecraft.getMinecraft();
			
            Property property = (Property)Iterables.getFirst(this.getPlayerProfile().getProperties().get("textures"), null);

            if ((this.profileset == true && property == null ))
            {
            	RaidersSkinManager.addToBadList(this.getOwner());
            	return resourcelocation;
            }
            else if(this.profileset = false) return resourcelocation;
            
            
			Map<Type, MinecraftProfileTexture> map = minecraft.getSkinManager().loadSkinFromCache(getPlayerProfile());

			if (map.containsKey(Type.SKIN))
			{
				//System.out.println("get skin");
				resourcelocation = minecraft.getSkinManager().loadSkin((MinecraftProfileTexture)map.get(Type.SKIN), Type.SKIN);
			}
		}
		else
		{
			this.setPlayerProfile(RaiderManager.getRaiderProfile(this.getOwner()));
			RaidersSkinManager.updateProfile(this);
		}
		
		return resourcelocation;
    	
    }
}
