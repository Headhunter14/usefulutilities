package chamoisest.usefulutilities.items;

import net.minecraft.item.Item;

public class ItemSpeedUpgrade extends Item{
	
	public ItemSpeedUpgrade(String unlocalizedName, String registryName){
		this.maxStackSize = 1;
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(registryName);
		
	}
	
}
