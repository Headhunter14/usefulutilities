package chamoisest.usefulutilities.blocks;

import java.util.Random;

import chamoisest.usefulutilities.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class BlockCopperOre extends Block{

	public BlockCopperOre(String unlocalizedName, String registryName) {
		super(Material.ROCK);
		// TODO Auto-generated constructor stub
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(new ResourceLocation(Reference.MODID, registryName));
		this.setHardness(2);
		this.setResistance(20);
		this.setHarvestLevel("pickaxe", 1);
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune){
		return Item.getItemFromBlock(this);
	}
	
}
