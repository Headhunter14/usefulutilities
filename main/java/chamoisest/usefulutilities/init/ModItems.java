package chamoisest.usefulutilities.init;

import chamoisest.usefulutilities.Reference;
import chamoisest.usefulutilities.UsefulUtilities;
import chamoisest.usefulutilities.Util;
import chamoisest.usefulutilities.items.ItemCopperIngot;
import chamoisest.usefulutilities.items.ItemHealingGem;
import chamoisest.usefulutilities.items.ItemHealingRod;
import chamoisest.usefulutilities.items.ItemSpeedUpgrade;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {
	
	public static Item copperIngot;
	public static Item healingRod;
	public static Item healingGem;
	public static Item speedUpgrade;
	public static Item fortuneUpgrade;
	public static Item silkTouchUpgrade;
	
	public static void init(){
		copperIngot = new ItemCopperIngot("copperingot", "copperingot");
		healingRod = new ItemHealingRod("healingrod");
		healingGem = new ItemHealingGem("healinggem", "healinggem");
		speedUpgrade = new ItemSpeedUpgrade("speedupgrade", "speedupgrade");
		fortuneUpgrade = new ItemSpeedUpgrade("fortuneupgrade", "fortuneupgrade");
		silkTouchUpgrade = new ItemSpeedUpgrade("silktouchupgrade", "silktouchupgrade");
		
	}
	
	public static void register(){
		registerItem(copperIngot);
		registerItem(healingRod);
		registerItem(healingGem);
		registerItem(speedUpgrade);
		registerItem(fortuneUpgrade);
		registerItem(silkTouchUpgrade);
	}
	
	public static void registerRenders(){
		registerRender(copperIngot);
		registerRender(healingRod);
		registerRender(healingGem);
		registerRender(speedUpgrade);
		registerRender(fortuneUpgrade);
		registerRender(silkTouchUpgrade);
	}
	
	public static void registerItem(Item item){
		item.setCreativeTab(UsefulUtilities.usefulutilities);
		GameRegistry.register(item);
		Util.getLogger().info("Registered item "+ item.getUnlocalizedName().substring(5));
	}
	
	public static void registerRender(Item item){
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(new ResourceLocation(Reference.MODID, item.getUnlocalizedName().substring(5)), "inventory"));
		Util.getLogger().info("Register render for " + item.getUnlocalizedName().substring(5));
	}
}
