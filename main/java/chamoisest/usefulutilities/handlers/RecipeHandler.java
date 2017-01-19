package chamoisest.usefulutilities.handlers;

import chamoisest.usefulutilities.Util;
import chamoisest.usefulutilities.init.ModBlocks;
import chamoisest.usefulutilities.init.ModItems;
import chamoisest.usefulutilities.init.ModTools;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RecipeHandler {
	
	public static void registerCraftingRecipes(){
		GameRegistry.addRecipe(new ItemStack(ModItems.healingRod), new Object[] {" M ", " S ", " S ", 'M', ModItems.healingGem, 'S', Items.STICK});
		GameRegistry.addRecipe(new ItemStack(ModItems.healingRod), new Object[] {"M  ", "S  ", "S  ", 'M', ModItems.healingGem, 'S', Items.STICK});
		GameRegistry.addRecipe(new ItemStack(ModItems.healingRod), new Object[] {"  M", "  S", "  S", 'M', ModItems.healingGem, 'S', Items.STICK});
		GameRegistry.addRecipe(new ItemStack(ModItems.healingGem), new Object[] {"SSS", "SDS", "SSS", 'D', Items.DIAMOND, 'S', Items.SPECKLED_MELON});
		GameRegistry.addRecipe(new ItemStack(ModBlocks.machineFrame), new Object[] {"SIS", "ISI", "SIS", 'I', Items.IRON_INGOT, 'S', Blocks.STONE});
		GameRegistry.addRecipe(new ItemStack(ModBlocks.blockBreaker), new Object[] {"ICI", "RMR", "ICI", 'I', Items.IRON_INGOT, 'R', Items.REDSTONE, 'M', ModBlocks.machineFrame, 'C', ModItems.copperIngot});
		GameRegistry.addRecipe(new ItemStack(ModItems.speedUpgrade), new Object[] {"RBR", "RIR", "RBR", 'R', Items.REDSTONE, 'B', Blocks.REDSTONE_BLOCK, 'I', Items.ITEM_FRAME});
		GameRegistry.addRecipe(new ItemStack(ModItems.fortuneUpgrade), new Object[] {" D ", "NIN", " D ", 'D', Items.DIAMOND, 'N', Blocks.LAPIS_BLOCK, 'I', Items.ITEM_FRAME});
		GameRegistry.addRecipe(new ItemStack(ModItems.silkTouchUpgrade), new Object[] {"SSS", "BIB", "SSS", 'S', Items.SLIME_BALL, 'B', Blocks.SLIME_BLOCK, 'I', Items.ITEM_FRAME});
		
		registerToolRecipe(ModTools.copperPickaxe, ModTools.copperAxe, ModTools.copperHoe, ModTools.copperShovel, ModTools.copperSword, ModItems.copperIngot);
		Util.getLogger().info("Registered Crafting Recipes!");
	}
	
	public static void registerSmeltingRecipes(){
		GameRegistry.addSmelting(ModBlocks.copperOre, new ItemStack(ModItems.copperIngot), 0.7F );
		Util.getLogger().info("Registered Smelting Recipes!");
	}
	
	private static void registerToolRecipe(Item pickaxe, Item axe, Item hoe, Item shovel, Item sword, Item ingot) {
		GameRegistry.addRecipe(new ItemStack(pickaxe), new Object[] {"III"," S "," S ", 'I', ingot, 'S', Items.STICK });
		GameRegistry.addRecipe(new ItemStack(axe), new Object[] {" II"," SI"," S ", 'I', ingot, 'S', Items.STICK });
		GameRegistry.addRecipe(new ItemStack(axe), new Object[] {"II ","IS "," S ", 'I', ingot, 'S', Items.STICK });
		GameRegistry.addRecipe(new ItemStack(hoe), new Object[] {"II "," S "," S ", 'I', ingot, 'S', Items.STICK });
		GameRegistry.addRecipe(new ItemStack(hoe), new Object[] {" II"," S "," S ", 'I', ingot, 'S', Items.STICK });
		GameRegistry.addRecipe(new ItemStack(shovel), new Object[] {" I "," S "," S ", 'I', ingot, 'S', Items.STICK });
		GameRegistry.addRecipe(new ItemStack(shovel), new Object[] {"I  ","S  ","S  ", 'I', ingot, 'S', Items.STICK });
		GameRegistry.addRecipe(new ItemStack(shovel), new Object[] {"  I","  S","  S", 'I', ingot, 'S', Items.STICK });
		GameRegistry.addRecipe(new ItemStack(sword), new Object[] {" I "," I "," S ", 'I', ingot, 'S', Items.STICK });
		GameRegistry.addRecipe(new ItemStack(sword), new Object[] {"I  ","I  ","S  ", 'I', ingot, 'S', Items.STICK });
		GameRegistry.addRecipe(new ItemStack(sword), new Object[] {"  I","  I","  S", 'I', ingot, 'S', Items.STICK });
	}
	
}
