package chamoisest.usefulutilities.init;

import chamoisest.usefulutilities.Reference;
import chamoisest.usefulutilities.UsefulUtilities;
import chamoisest.usefulutilities.Util;
import chamoisest.usefulutilities.items.ItemModAxe;
import chamoisest.usefulutilities.items.ItemModHoe;
import chamoisest.usefulutilities.items.ItemModPickaxe;
import chamoisest.usefulutilities.items.ItemModShovel;
import chamoisest.usefulutilities.items.ItemModSword;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModTools {
	
		public static final ToolMaterial copperMaterial = EnumHelper.addToolMaterial(Reference.MODID + ":copper", 2, 300, 5.0F, 2.0F, 12);
		
		public static ItemPickaxe copperPickaxe;
		public static ItemModAxe copperAxe;
		public static ItemSpade copperShovel;
		public static ItemHoe copperHoe;
		public static ItemSword copperSword;
	
		public static void init(){
			copperPickaxe = new ItemModPickaxe(copperMaterial, "copperpickaxe");
			copperAxe = new ItemModAxe(copperMaterial, "copperaxe");
			copperShovel = new ItemModShovel(copperMaterial, "coppershovel");
			copperHoe = new ItemModHoe(copperMaterial, "copperhoe");
			copperSword = new ItemModSword(copperMaterial, "coppersword");
		}
		
		public static void register(){
			registerItem(copperPickaxe);
			registerItem(copperSword);
			registerItem(copperAxe);
			registerItem(copperShovel);
			registerItem(copperHoe);
		}
		
		public static void registerRenders(){
			registerRender(copperPickaxe);
			registerRender(copperSword);
			registerRender(copperAxe);
			registerRender(copperShovel);
			registerRender(copperHoe);
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
