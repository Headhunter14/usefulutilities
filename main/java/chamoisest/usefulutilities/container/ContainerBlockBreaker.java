package chamoisest.usefulutilities.container;

import javax.annotation.Nullable;

import chamoisest.usefulutilities.container.slots.SlotUpgrade;
import chamoisest.usefulutilities.init.ModItems;
import chamoisest.usefulutilities.tileentity.TileEntityBlockBreaker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerBlockBreaker extends Container{
	
	private TileEntityBlockBreaker te;
	private IItemHandler handler;
	
	public ContainerBlockBreaker(IInventory playerInv, TileEntityBlockBreaker te){
		this.te = te;
		this.handler = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null); //Gets the inventory from our tile entity

		//Our tile entity slots
		this.addSlotToContainer(new SlotItemHandler(handler, 0, 62, 17));
		this.addSlotToContainer(new SlotItemHandler(handler, 1, 80, 17));
		this.addSlotToContainer(new SlotItemHandler(handler, 2, 98, 17));
		this.addSlotToContainer(new SlotItemHandler(handler, 3, 62, 35));
		this.addSlotToContainer(new SlotItemHandler(handler, 4, 80, 35));
		this.addSlotToContainer(new SlotItemHandler(handler, 5, 98, 35));
		this.addSlotToContainer(new SlotItemHandler(handler, 6, 62, 53));
		this.addSlotToContainer(new SlotItemHandler(handler, 7, 80, 53));
		this.addSlotToContainer(new SlotItemHandler(handler, 8, 98, 53));
		this.addSlotToContainer(new SlotUpgrade(handler, 9, 141, 17));
		this.addSlotToContainer(new SlotUpgrade(handler, 10, 141, 35));
		this.addSlotToContainer(new SlotUpgrade(handler, 11, 141, 53));


		//The player's inventory slots
		int xPos = 8; //The x position of the top left player inventory slot on our texture
		int yPos = 84; //The y position of the top left player inventory slot on our texture

		//Player slots
		for (int y = 0; y < 3; ++y) {
			for (int x = 0; x < 9; ++x) {
				this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, xPos + x * 18, yPos + y * 18));
			}
		}

		for (int x = 0; x < 9; ++x) {
			this.addSlotToContainer(new Slot(playerInv, x, xPos + x * 18, yPos + 58));
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return this.te.isUseableByPlayer(player);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {
	    ItemStack previous = null;
	    Slot slot = (Slot) this.inventorySlots.get(fromSlot);

	    if (slot != null && slot.getHasStack()) {
	        ItemStack current = slot.getStack();
	        previous = current.copy();

	        if (fromSlot < this.handler.getSlots()) {
	            // From the block breaker inventory to player's inventory
	            if (!this.mergeItemStack(current, handler.getSlots(), handler.getSlots() + 36, true))
	                return null;
	        } else {
	        	if(current.getItem() == ModItems.speedUpgrade){
	           		 if (!this.mergeItemStack(current, 9, handler.getSlots(), false)){
	           			 return null;
	           		 } 
			    }else if(current.getItem() == ModItems.fortuneUpgrade){
	           		 if (!this.mergeItemStack(current, 9, handler.getSlots(), false)){
	           			 return null;
	           		 }	 
		         }
		         else if(current.getItem() == ModItems.silkTouchUpgrade){
	           		 if (!this.mergeItemStack(current, 9, handler.getSlots(), false)){
	           			 return null;
	           		 }	 
		         }else{
	           			return null;
		         }
	        }

	        if (current.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

	        if (current.stackSize == previous.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(playerIn, current);
	    }
	    return previous;
	}
	

}

