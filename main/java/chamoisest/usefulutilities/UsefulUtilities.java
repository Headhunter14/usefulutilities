package chamoisest.usefulutilities;

import chamoisest.usefulutilities.creativetabs.UsefulUtilitiesTab;
import chamoisest.usefulutilities.handlers.OreDictionaryHandler;
import chamoisest.usefulutilities.handlers.RecipeHandler;
import chamoisest.usefulutilities.init.ModBlocks;
import chamoisest.usefulutilities.init.ModItems;
import chamoisest.usefulutilities.init.ModTools;
import chamoisest.usefulutilities.proxy.CommonProxy;
import chamoisest.usefulutilities.worldgen.OreGen;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class UsefulUtilities {
	
	public static final CreativeTabs usefulutilities = new UsefulUtilitiesTab();
	
	@Mod.Instance(Reference.MODID)
	
	public static UsefulUtilities instance;
	
	@SidedProxy(serverSide = Reference.SERVER_PROXY_CLASS, clientSide = Reference.CLIENT_PROXY_CLASS)
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		ModItems.init();
		ModBlocks.init();
		ModTools.init();
		ModItems.register();
		ModBlocks.register();
		ModTools.register();
		
		
		proxy.registerRenders();
		proxy.registerTileEntities();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event){
		proxy.init();
		GameRegistry.registerWorldGenerator(new OreGen(), 0);
		OreDictionaryHandler.registerOreDictionary();
		RecipeHandler.registerCraftingRecipes();
		RecipeHandler.registerSmeltingRecipes();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event){
		
	}
}
