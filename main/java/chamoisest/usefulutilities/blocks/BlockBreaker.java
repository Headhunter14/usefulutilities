package chamoisest.usefulutilities.blocks;

import java.util.List;

import javax.annotation.Nullable;

import chamoisest.usefulutilities.Reference;
import chamoisest.usefulutilities.UsefulUtilities;
import chamoisest.usefulutilities.Util;
import chamoisest.usefulutilities.client.gui.GuiHandler;
import chamoisest.usefulutilities.tileentity.TileEntityBlockBreaker;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class BlockBreaker extends Block implements ITileEntityProvider{
	
	public static final PropertyDirection FACING = PropertyDirection.create("facing");
	public static final PropertyBool ACTIVATED = PropertyBool.create("activated");

	public BlockBreaker(String unlocalizedName) {
		super(Material.IRON);
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(new ResourceLocation(Reference.MODID, unlocalizedName));
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(ACTIVATED, Boolean.valueOf(false)));
		this.setHardness(2);
		this.setResistance(20);
		this.setHarvestLevel("pickaxe", 1);
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		
		return new TileEntityBlockBreaker();
	}
	
	
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced){
		super.addInformation(stack, player, tooltip, advanced);
		tooltip.add(TextFormatting.AQUA + Util.getLang().localize("blockBreaker.tooltip"));
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {FACING,ACTIVATED});
	}
	
	@Override
	public IBlockState  onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(FACING, getFacingFromEntity(pos, placer));
    }

	
	@Override
	public int getMetaFromState(IBlockState state) {
		int facing = ((EnumFacing)state.getValue(FACING)).getIndex();
		return facing;
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(FACING, getFacing(meta));
    }
	
	@Nullable
    public static EnumFacing getFacing(int meta)
    {
        int i = meta & 7;
        return i > 5 ? null : EnumFacing.getFront(i);
    }
	
	public static EnumFacing getFacingFromEntity(BlockPos pos, EntityLivingBase player)
    {
		 if (MathHelper.abs((float)player.posX - (float)pos.getX()) < 2.0F && MathHelper.abs((float)player.posZ - (float)pos.getZ()) < 2.0F)
	        {
	            double d0 = player.posY + (double)player.getEyeHeight();

	            if (d0 - (double)pos.getY() > 2.0D)
	            {
	                return EnumFacing.UP;
	            }

	            if ((double)pos.getY() - d0 > 0.0D)
	            {
	                return EnumFacing.DOWN;
	            }
	        }
        return player.getHorizontalFacing().getOpposite();
    }

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		
		return new TileEntityBlockBreaker();
	}
	
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		TileEntityBlockBreaker te = (TileEntityBlockBreaker) world.getTileEntity(pos);
		IItemHandler handler = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		for(int slot = 0; slot < 12; slot++) {
			if(handler.getStackInSlot(slot) != null){
				ItemStack stack = handler.getStackInSlot(slot);
				InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), stack);
			}
		}
		super.breakBlock(world, pos, state);
	}
	
    @Override
    public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player){
        if(!player.capabilities.isCreativeMode){
            this.dropBlockAsItem(world, pos, state, 0);
            //dirty workaround because of Forge calling Item.onBlockStartBreak() twice
            world.setBlockToAir(pos);
        }
    }
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if(!worldIn.isRemote) {
			playerIn.openGui(UsefulUtilities.instance, GuiHandler.BLOCK_BREAKER, worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}
	
	
	

}
