package chamoisest.usefulutilities.handlers;

import chamoisest.usefulutilities.init.ModBlocks;
import chamoisest.usefulutilities.init.ModItems;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictionaryHandler {
	public static void registerOreDictionary(){
		OreDictionary.registerOre("ingotCopper", ModItems.copperIngot);
		OreDictionary.registerOre("oreCopper", ModBlocks.copperOre);
	}
}
