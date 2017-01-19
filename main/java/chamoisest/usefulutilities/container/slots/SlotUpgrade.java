package chamoisest.usefulutilities.container.slots;

import chamoisest.usefulutilities.container.ContainerBlockBreaker;
import chamoisest.usefulutilities.init.ModItems;
import chamoisest.usefulutilities.tileentity.TileEntityBlockBreaker;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotUpgrade extends SlotItemHandler{
	

	
	public SlotUpgrade(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
		super(itemHandler, index, xPosition, yPosition);
		
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return (super.isItemValid(stack) && stack.getItem() == ModItems.speedUpgrade) || (super.isItemValid(stack) && stack.getItem() == ModItems.fortuneUpgrade) || (super.isItemValid(stack) && stack.getItem() == ModItems.silkTouchUpgrade);
		
		
	}

}
