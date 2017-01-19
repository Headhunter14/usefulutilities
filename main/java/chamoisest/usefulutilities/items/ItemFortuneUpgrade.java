package chamoisest.usefulutilities.items;

import net.minecraft.item.Item;

public class ItemFortuneUpgrade extends Item{
	
	public ItemFortuneUpgrade(String unlocalizedName, String registryName){
		this.maxStackSize = 1;
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(registryName);
		
	}
	
}
