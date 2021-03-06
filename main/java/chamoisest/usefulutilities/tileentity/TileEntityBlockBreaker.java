package chamoisest.usefulutilities.tileentity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.mojang.authlib.GameProfile;

import chamoisest.usefulutilities.blocks.BlockBreaker;
import chamoisest.usefulutilities.container.ContainerBlockBreaker;
import chamoisest.usefulutilities.init.ModBlocks;
import chamoisest.usefulutilities.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDynamicLiquid;
import net.minecraft.block.BlockStaticLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityBlockBreaker extends TileEntity implements ITickable, ICapabilityProvider{
	
	private int cooldown;
	private int cooldownCap;
	private boolean fortune;
	private boolean silktouch;
	private ItemStackHandler handler;
	private Random random;
	
	public TileEntityBlockBreaker() {
		this.cooldown = 0;
		this.cooldownCap = 80;
		this.fortune = false;
		this.silktouch = false;
		this.handler = new ItemStackHandler(12);
		this.random = new Random();
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.cooldown = nbt.getInteger("Cooldown");
		this.handler.deserializeNBT(nbt.getCompoundTag("ItemStackHandler"));
		ModBlocks.blockBreaker.getBlockState();
	}
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		nbt.setInteger("Cooldown", this.cooldown);
		nbt.setTag("ItemStackHandler", this.handler.serializeNBT());
		
		return super.writeToNBT(nbt);
	}
	
	@Override
	public void update() {
		
		if (this.worldObj != null) { //Makes sure we have a world. RENAMED IN 1.11.2 from worldObj to world
			if (!this.worldObj.isAirBlock(pos) && !this.worldObj.isRemote && this.worldObj.isBlockPowered(pos)) { //Calls it server side and checks if our block is powered
				IBlockState currentState = this.worldObj.getBlockState(pos); //Gets our block state
				this.worldObj.setBlockState(pos,currentState.withProperty(BlockBreaker.ACTIVATED, Boolean.valueOf(true))); //Updates it if it is powered
				this.updateUpgradeSettings();
				this.cooldown++; //Increases the cooldown
				this.cooldown %= this.cooldownCap;
				if (this.cooldown == 0) { //Only runs when the cooldown is 0 (i.e every 50 or 100 ticks (2.5 or 5 seconds))
					currentState = this.worldObj.getBlockState(pos); //Updates our current state variable
					EnumFacing facing = (EnumFacing) currentState.getValue(BlockBreaker.FACING); //Gets which way our block is facing
					breakBlock(facing); //Calls our break block method which handles the actual breaking of the block
				}
			} else if (!this.worldObj.isRemote && !this.worldObj.isBlockPowered(pos)) { //If the block is not powered
				if (!this.worldObj.isAirBlock(pos) && this.worldObj.getBlockState(pos).getBlock() == ModBlocks.blockBreaker) { //The block is not air and it is a block breaker
					if (this.worldObj.getBlockState(pos).getValue(BlockBreaker.ACTIVATED)) { //Checks if it is activated
						IBlockState currentState = this.worldObj.getBlockState(pos);
						this.worldObj.setBlockState(pos,
								currentState.withProperty(BlockBreaker.ACTIVATED, Boolean.valueOf(false))); //Makes it not activated
					}
				}
			}
		}
	}
	
	public void updateUpgradeSettings() {
		int speedAmount = 80;
		boolean fortuneLevel = false;
		boolean silkTouchLevel = false;
		for(int i = 9; i < 12; i++){
			if(this.handler.getStackInSlot(i) != null){
				if(this.handler.getStackInSlot(i).getItem() == ModItems.speedUpgrade){
					speedAmount -= 20;
				}
				else if(this.handler.getStackInSlot(i).getItem() == ModItems.fortuneUpgrade){
					fortuneLevel = true;
				}
				else if(this.handler.getStackInSlot(i).getItem() == ModItems.silkTouchUpgrade){
					silkTouchLevel = true;
				}
			}
			
		}
		
		this.cooldownCap = speedAmount;
		this.fortune = fortuneLevel;
		this.silktouch = silkTouchLevel;
		
	}
	
	public void breakBlock(EnumFacing facing) {
		BlockPos newPos = pos.offset(facing, 1); //Gets the block pos in front of the block breaker
		IBlockState state = this.worldObj.getBlockState(newPos); //Gets the block state
		Block block = state.getBlock(); //Gets the block
		//If the block is not air, is not unbreakable or a liquid it will try and break it
		if (!block.isAir(state, this.worldObj, newPos) && block.getBlockHardness(state, this.worldObj, newPos) >= 0 && !(block instanceof BlockDynamicLiquid) && !(block instanceof BlockStaticLiquid)) {
			//Creates a fake player which will berak the block
			EntityPlayer player = new EntityPlayer(worldObj, new GameProfile(null, "BlockBreaker")) {

				@Override
				public boolean isSpectator() {
					return true;
				}

				@Override
				public boolean isCreative() {
					return false;
				}
			};
			List<ItemStack> drops = new ArrayList<ItemStack>();
			boolean customDrops = false;
			if(fortune == true){
				drops.add(new ItemStack(block.getItemDropped(state, this.random, 3), block.quantityDroppedWithBonus(3, this.random), block.damageDropped(state)));
				customDrops = true;
			}
			else if(silktouch == true){
				if(block == Blocks.LAPIS_ORE)
					drops.add(new ItemStack(block, 1));
				else
					drops.add(new ItemStack(block, 1, block.damageDropped(state)));
				customDrops = true;
			}
			if(!customDrops)
				drops = block.getDrops(worldObj, newPos, state, 0);
			
			//Use block.harvestBlock if you don't want the item to go into the inventory
			int full = 0;
			for (ItemStack stack : drops) { //This then puts the item into the inventory correctly
				ItemStack remainder = this.handler.insertItem(0, stack, false);
				full = 0;
				if (remainder == (ItemStack)null)// If it is a null
															// item
					continue;
				else
					full++;
				remainder = this.handler.insertItem(1, remainder, false);
				if (remainder == (ItemStack)null)// If it is a null
															// item
					continue;
				else
					full++;
				remainder = this.handler.insertItem(2, remainder, false);
				if (remainder == (ItemStack)null)// If it is a null
															// item
					continue;
				else
					full++;
				remainder = this.handler.insertItem(3, remainder, false);
				if (remainder == (ItemStack)null)// If it is a null
															// item
					continue;
				else
					full++;
				remainder = this.handler.insertItem(4, remainder, false);
				if (remainder == (ItemStack)null)// If it is a null
															// item
					continue;
				else
					full++;
				remainder = this.handler.insertItem(5, remainder, false);
				if (remainder == (ItemStack)null)// If it is a null
															// item
					continue;
				else
					full++;
				remainder = this.handler.insertItem(6, remainder, false);
				if (remainder == (ItemStack)null)// If it is a null
															// item
					continue;
				else
					full++;
				remainder = this.handler.insertItem(7, remainder, false);
				if (remainder == (ItemStack)null)// If it is a null
															// item
					continue;
				else
					full++;
				remainder = this.handler.insertItem(8, remainder, false);
				if (remainder == (ItemStack)null)// If it is a null
															// item
					continue;
				else
					full++;
			}
			
			if(full < handler.getSlots() - 3) {
				this.worldObj.playSound(null, pos, block.getSoundType(state, worldObj, newPos, player).getBreakSound(), SoundCategory.BLOCKS, 1, 1); //Plays the block breaking sound
				this.worldObj.setBlockToAir(newPos); //Makes the block air
				if(block == Blocks.ICE) //If the block was ice it will place flowing water there instead
					this.worldObj.setBlockState(newPos, Blocks.FLOWING_WATER.getDefaultState());
			}
		}
	}
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);
		int metadata = getBlockMetadata();
		return new SPacketUpdateTileEntity(this.pos, metadata, nbt);
	}

	/**
	 * Reads the nbt when it receives a packet
	 */
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		this.readFromNBT(pkt.getNbtCompound());
	}

	/**
	 * Gets the nbt for a new packet
	 */
	@Override
	public NBTTagCompound getUpdateTag() {
		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);
		return nbt;
	}

	/**
	 * Handles when you get an update
	 */
	@Override
	public void handleUpdateTag(NBTTagCompound tag) {
		this.readFromNBT(tag);
	}

	/**
	 * Gets the tile entities nbt with all of the data stored in it
	 */
	@Override
	public NBTTagCompound getTileData() {
		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);
		return nbt;
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return (T) this.handler; //Makes it so that you can get the capability from our tile entity
		return super.getCapability(capability, facing);
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return true;
		return super.hasCapability(capability, facing);
	}
	
	public boolean isUseableByPlayer(EntityPlayer player) {
		return this.worldObj.getTileEntity(this.getPos()) == this
				&& player.getDistanceSq(this.pos.add(0.5, 0.5, 0.5)) <= 64;
	}

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
		return false;
	}
}
