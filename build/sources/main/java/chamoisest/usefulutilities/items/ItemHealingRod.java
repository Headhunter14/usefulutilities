package chamoisest.usefulutilities.items;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;

import chamoisest.usefulutilities.Reference;
import chamoisest.usefulutilities.Util;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

public class ItemHealingRod extends ItemTool{
	
	public static final Set<Block> EFFECTIVE_BLOCKS = Sets.newHashSet(new Block[] {});
	
	public ItemHealingRod(String unlocalizedName) {
		super(EnumHelper.addToolMaterial(Reference.MODID + ":healingRod", 0, 50, 0, 1, 0), EFFECTIVE_BLOCKS);
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(new ResourceLocation(Reference.MODID, unlocalizedName));
	}
	
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced){
		super.addInformation(stack, player, tooltip, advanced);
		tooltip.add(TextFormatting.AQUA + Util.getLang().localize("itemHealing.tooltip"));
	}
	
	public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player,
			EnumHand hand) {
		if(player.getHealth() < player.getMaxHealth()){
			player.heal(1);
			stack.damageItem(1, player);
		}
		return super.onItemRightClick(stack, world, player, hand);
	}
	

}
