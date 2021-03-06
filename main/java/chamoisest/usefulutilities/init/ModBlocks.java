package chamoisest.usefulutilities.init;

import chamoisest.usefulutilities.UsefulUtilities;
import chamoisest.usefulutilities.Reference;
import chamoisest.usefulutilities.Util;
import chamoisest.usefulutilities.blocks.BlockBreaker;
import chamoisest.usefulutilities.blocks.BlockCopperOre;
import chamoisest.usefulutilities.blocks.BlockMachineFrame;
import chamoisest.usefulutilities.blocks.item.ItemBlockBreaker;
import chamoisest.usefulutilities.creativetabs.UsefulUtilitiesTab;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {
	
	public static Block copperOre;
	public static Block blockBreaker;
	public static Block machineFrame;
	
	public static void init(){
		copperOre = new BlockCopperOre("copperore", "copperore");
		blockBreaker = new BlockBreaker("blockbreaker");
		machineFrame = new BlockMachineFrame("machineframe", "machineframe");
	}
	
	public static void register(){
		registerBlock(copperOre);
		registerBlock(blockBreaker);
		registerBlock(machineFrame);
	}
	
	public static void registerRenders(){
		registerRender(copperOre);
		registerRender(blockBreaker);
		registerRender(machineFrame);
	}
	
	public static void registerBlock(Block block){
		block.setCreativeTab(UsefulUtilities.usefulutilities);
		GameRegistry.register(block);
		GameRegistry.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
		Util.getLogger().info("Registered block: " + block.getUnlocalizedName().substring(5));
	}
	public static void registerRender(Block block){
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(new ResourceLocation(Reference.MODID, block.getUnlocalizedName().substring(5)), "inventory"));
		Util.getLogger().info("Register render for " + block.getUnlocalizedName().substring(5));
	}
}
