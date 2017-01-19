package chamoisest.usefulutilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.items.ItemStackHandler;

public class Util {
	private static Logger logger;
	private static Lang lang;
	
	public static Logger getLogger(){
		if(logger == null){
			logger = LogManager.getFormatterLogger(Reference.MODID);
		}
		return logger;
	}
	
	public static Lang getLang(){
		if(lang == null){
			lang = new Lang(Reference.MODID);
		}
		return lang;
	}
	
	
}
