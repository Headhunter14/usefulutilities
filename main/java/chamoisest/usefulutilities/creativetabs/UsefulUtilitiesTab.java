package chamoisest.usefulutilities.creativetabs;

import chamoisest.usefulutilities.init.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class UsefulUtilitiesTab extends CreativeTabs{



	public UsefulUtilitiesTab() {
		super("usefulutilities");
	}

	@Override
	public Item getTabIconItem() {
		
		return ModItems.healingGem;
	}

}
