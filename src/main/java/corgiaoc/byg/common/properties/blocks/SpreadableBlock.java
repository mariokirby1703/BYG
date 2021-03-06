package corgiaoc.byg.common.properties.blocks;

import corgiaoc.byg.common.world.feature.end.EndVegetationFeature;
import corgiaoc.byg.common.world.feature.overworld.OverworldVegetationFeature;
import corgiaoc.byg.core.BYGBlocks;
import net.minecraft.block.*;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.*;
import net.minecraft.world.gen.feature.BlockStateProvidingFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.FlowersFeature;
import net.minecraft.world.gen.feature.NetherVegetationFeature;
import net.minecraft.world.lighting.LightEngine;
import net.minecraft.world.server.ServerWorld;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.AbstractBlock.Properties;

public class SpreadableBlock extends SnowyDirtBlock implements IGrowable {

    private final Block blockToSpreadToo;
    private final BlockStateProvidingFeatureConfig featureConfig;
    private final ForDimension forDimension;



    private final boolean isNotOverworld;

    public SpreadableBlock(Properties properties, Block blockToSpreadToo, ForDimension type, BlockStateProvidingFeatureConfig featureConfig) {
        super(properties);
        this.blockToSpreadToo = blockToSpreadToo;
        this.featureConfig = featureConfig;
        this.forDimension = type;

        isNotOverworld = forDimension != ForDimension.OVERWORLD;

    }

    @Override
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        if (isNotOverworld) {
            if (!areConditionsGood(state, worldIn, pos))
                worldIn.setBlockState(pos, blockToSpreadToo.getDefaultState());
        } else {
            if (!areConditionsGood(state, worldIn, pos)) {
                if (!worldIn.isAreaLoaded(pos, 3))
                    return;
                worldIn.setBlockState(pos, blockToSpreadToo.getDefaultState());
            } else {
                if (worldIn.getLight(pos.up()) >= 9) {
                    BlockState blockstate = this.getDefaultState();

                    for (int i = 0; i < 4; ++i) {
                        BlockPos blockpos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                        if (worldIn.getBlockState(blockpos).isIn(blockToSpreadToo) && areConditionsGoodAndNotUnderWater(blockstate, worldIn, blockpos)) {
                            worldIn.setBlockState(blockpos, blockstate.with(SNOWY, worldIn.getBlockState(blockpos.up()).isIn(Blocks.SNOW)));
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return worldIn.getBlockState(pos.up()).isAir();
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random rand, BlockPos blockPos, BlockState state) {
        BlockPos blockpos = blockPos.up();
        BlockState thisBlockState = (rand.nextInt(2) == 0) ? Blocks.GRASS.getDefaultState() : BYGBlocks.SHORT_GRASS.getDefaultState();


        for (int i = 0; i < world.getHeight(); ++i) {
            BlockPos blockpos1 = blockpos;
            int j = 0;

            while (true) {
                if (j >= i / 16) {
                    BlockState blockstate2 = world.getBlockState(blockpos1);
                    if (isNotOverworld) {
                        if (blockstate2 == thisBlockState && rand.nextInt(10) == 0) {
                            ((IGrowable) thisBlockState.getBlock()).grow(world, rand, blockpos1, blockstate2);
                        }
                    }

                    if (!blockstate2.isAir()) {
                        break;
                    }

                    if (forDimension == ForDimension.NETHER) {
                        NetherVegetationFeature.func_236325_a_(world, rand, blockpos1, this.featureConfig, 3, 1);
                        break;
                    }

                    if (forDimension == ForDimension.END) {
                        EndVegetationFeature.placeBonemeal(world, rand, blockpos1, this.featureConfig, 3, 1);
                        break;
                    }

                    if (forDimension == ForDimension.OVERWORLD && this.featureConfig != null) {
                        OverworldVegetationFeature.placeBonemeal(world, rand, blockpos1, this.featureConfig, 3, 1, this);
                        break;
                    }

                    BlockState flowerState;
                    if (rand.nextInt(8) == 0) {
                        List<ConfiguredFeature<?, ?>> flowerListForBiome = world.getBiome(blockpos1).getGenerationSettings().getFlowerFeatures();
                        if (flowerListForBiome.isEmpty()) {
                            continue;
                        }

                        ConfiguredFeature<?, ?> configuredfeature = flowerListForBiome.get(0);
                        FlowersFeature flowersfeature = (FlowersFeature) configuredfeature.feature;
                        flowerState = flowersfeature.getFlowerToPlace(rand, blockpos1, configuredfeature.getConfig());
                    } else {
                        flowerState = thisBlockState;
                    }

                    if (flowerState.isValidPosition(world, blockpos1)) {
                        world.setBlockState(blockpos1, flowerState, 3);
                    }
                    break;
                }

                blockpos1 = blockpos1.add(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1);
                if (world.getBlockState(blockpos1.down()).getBlock() != this || world.getBlockState(blockpos1).isOpaqueCube(world, blockpos1)) {
                    break;
                }
                ++j;
            }
        }
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        if (!isNotOverworld)
            super.fillStateContainer(builder);
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (!isNotOverworld)
            return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
        else
            return this.getDefaultState();
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        if (!isNotOverworld)
            return super.getStateForPlacement(context);
        else
            return this.getDefaultState();
    }


    private boolean areConditionsGood(BlockState state, IWorldReader worldReader, BlockPos pos) {
        BlockPos blockpos = pos.up();
        BlockState blockstate = worldReader.getBlockState(blockpos);
        if (!isNotOverworld) {
            if (blockstate.isIn(Blocks.SNOW) && blockstate.get(SnowBlock.LAYERS) == 1) {
                return true;
            } else if (blockstate.getFluidState().getLevel() == 8) {
                return false;
            }
        }

        int i = LightEngine.func_215613_a(worldReader, state, pos, blockstate, blockpos, Direction.UP, blockstate.getOpacity(worldReader, blockpos));
        return i < worldReader.getMaxLightLevel();
    }

    private boolean areConditionsGoodAndNotUnderWater(BlockState state, IWorldReader worldReader, BlockPos pos) {
        BlockPos blockpos = pos.up();
        return areConditionsGood(state, worldReader, pos) && !worldReader.getFluidState(blockpos).isTagged(FluidTags.WATER);
    }

    public static void addGrassBlocksForConsumption() {



    }


    public enum ForDimension {
        OVERWORLD(DimensionType.OVERWORLD_TYPE),
        NETHER(DimensionType.NETHER_TYPE),
        END(DimensionType.END_TYPE);

        final DimensionType dimensionType;

        ForDimension(DimensionType dimensionType) {
            this.dimensionType = dimensionType;
        }

        public DimensionType getDimType() {
            return this.dimensionType;
        }
    }
}
