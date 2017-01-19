package chamoisest.usefulutilities.worldgen;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import chamoisest.usefulutilities.blocks.BlockCopperOre;
import chamoisest.usefulutilities.init.ModBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class OreGen implements IWorldGenerator{

	//World generators
	private WorldGenerator copperGen;
	
	public OreGen(){
		int randomNum = ThreadLocalRandom.current().nextInt(4, 8 + 1);
		copperGen = new WorldGenMinable(ModBlocks.copperOre.getDefaultState(), randomNum);
		//Generating for nether and end
		//oreGen = new WorldGenMinable(ModBlocks.Ore.getDefaultState(), 8, new Nether/EndGenPredicate());
	}
	
	private void runGenerator(WorldGenerator generator, World world, Random rand, int chunk_X, int chunk_Z, int chancesToSpawn, int minHeight, int maxHeight) {
		if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight)
			throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator");

		int heightDiff = maxHeight - minHeight + 1;
		for (int i = 0; i < chancesToSpawn; i ++) {
			int x = chunk_X * 16 + rand.nextInt(16);
		        int y = minHeight + rand.nextInt(heightDiff);
		        int z = chunk_Z * 16 + rand.nextInt(16);
		        generator.generate(world, rand, new BlockPos(x, y, z));
		}
	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		// TODO Auto-generated method stub
		switch(world.provider.getDimension()){
		case 0: //overworld
				this.runGenerator(copperGen, world, random, chunkX, chunkZ, 20, 16, 64);
		case 1: //end
			
		case -1: //nether
			
		}
	}

}
