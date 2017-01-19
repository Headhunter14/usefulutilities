package chamoisest.usefulutilities.proxy;

import chamoisest.usefulutilities.Reference;
import chamoisest.usefulutilities.tileentity.TileEntityBlockBreaker;
import chamoisest.usefulutilities.worldgen.OreGen;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {
	public void init(){
		
	}
	public void registerRenders(){
		
	}
	
	public void registerTileEntities(){
		GameRegistry.registerTileEntity(TileEntityBlockBreaker.class, Reference.MODID + ":block_breaker");
	}
}
