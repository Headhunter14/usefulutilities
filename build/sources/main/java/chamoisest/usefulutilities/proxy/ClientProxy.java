package chamoisest.usefulutilities.proxy;

import chamoisest.usefulutilities.UsefulUtilities;
import chamoisest.usefulutilities.client.gui.GuiHandler;
import chamoisest.usefulutilities.init.ModBlocks;
import chamoisest.usefulutilities.init.ModItems;
import chamoisest.usefulutilities.init.ModTools;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class ClientProxy extends CommonProxy{

	@Override
	public void init() {
		NetworkRegistry.INSTANCE.registerGuiHandler(UsefulUtilities.instance, new GuiHandler());
	}
	
	@Override
	public void registerRenders() {
		ModItems.registerRenders();
		ModBlocks.registerRenders();
		ModTools.registerRenders();
	}

}
