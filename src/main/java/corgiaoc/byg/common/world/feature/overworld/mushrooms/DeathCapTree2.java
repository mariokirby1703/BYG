package corgiaoc.byg.common.world.feature.overworld.mushrooms;

import com.mojang.serialization.Codec;
import corgiaoc.byg.common.world.feature.config.BYGMushroomConfig;
import corgiaoc.byg.common.world.feature.overworld.mushrooms.util.BYGAbstractMushroomFeature;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;

import java.util.Random;

public class DeathCapTree2 extends BYGAbstractMushroomFeature<BYGMushroomConfig> {

    public DeathCapTree2(Codec<BYGMushroomConfig> configIn) {
        super(configIn);
    }

    protected boolean placeMushroom(ISeedReader worldIn, Random rand, BlockPos pos, boolean isMushroom, BYGMushroomConfig config) {
        BlockState STEM = config.getStemProvider().getBlockState(rand, pos);
        BlockState MUSHROOM = config.getMushroomProvider().getBlockState(rand, pos);
        BlockState MUSHROOM2 = config.getMushroom2Provider().getBlockState(rand, pos);
        BlockState MUSHROOM3 = config.getMushroom3Provider().getBlockState(rand, pos);
        BlockState POLLEN = config.getPollenProvider().getBlockState(rand, pos);
        int randTreeHeight = 7 + rand.nextInt(5);
        BlockPos.Mutable mainmutable = new BlockPos.Mutable().setPos(pos);

        if (pos.getY() + randTreeHeight + 1 < worldIn.getHeight()) {
            if (!isDesiredGroundwDirtTag(config, worldIn, pos.down(), Blocks.GRASS_BLOCK)) {
                return false;
            } else if (!this.isAnotherMushroomLikeThisNearby(worldIn, pos, randTreeHeight, 0, STEM.getBlock(), MUSHROOM.getBlock(), isMushroom)) {
                return false;
            } else if (!this.doesMushroomHaveSpaceToGrow(worldIn, pos, randTreeHeight, 5, 5, 5, isMushroom)) {
                return false;
            } else {
                placeStem(STEM, worldIn, mainmutable.setPos(pos).move(0, 0, 0));
                placeStem(STEM, worldIn, mainmutable.setPos(pos).move(0, 1, 0));
                placeStem(STEM, worldIn, mainmutable.setPos(pos).move(0, 2, 0));
                placeStem(STEM, worldIn, mainmutable.setPos(pos).move(0, 3, 0));
                placeStem(STEM, worldIn, mainmutable.setPos(pos).move(0, 4, 0));
                placeStem(STEM, worldIn, mainmutable.setPos(pos).move(0, 5, 0));
                placeStem(STEM, worldIn, mainmutable.setPos(pos).move(0, 6, 0));
                placeStem(STEM, worldIn, mainmutable.setPos(pos).move(0, 7, 0));
                placeStemBranch(STEM, worldIn, mainmutable.setPos(pos).move(1, 3, 1));
                placeStemBranch(STEM, worldIn, mainmutable.setPos(pos).move(1, 4, 1));
                placeStemBranch(STEM, worldIn, mainmutable.setPos(pos).move(2, 4, 2));
                placeStemBranch(STEM, worldIn, mainmutable.setPos(pos).move(2, 5, 2));
                placeStemBranch(STEM, worldIn, mainmutable.setPos(pos).move(2, 5, 3));
                placeStemBranch(STEM, worldIn, mainmutable.setPos(pos).move(1, 6, 4));
                placeStemBranch(STEM, worldIn, mainmutable.setPos(pos).move(2, 6, 3));
                placeStemBranch(STEM, worldIn, mainmutable.setPos(pos).move(1, 7, 4));
                placeStemBranch(STEM, worldIn, mainmutable.setPos(pos).move(1, 8, 4));
                placeStemBranch(STEM, worldIn, mainmutable.setPos(pos).move(1, 9, 4));
                placeStemBranch(STEM, worldIn, mainmutable.setPos(pos).move(1, 10, 4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(-2, 7, -1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(-2, 7, 0));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(-2, 7, 1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(-1, 7, -2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(-1, 7, -1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(-1, 7, 0));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(-1, 7, 1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(-1, 7, 2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(0, 7, -2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(0, 7, -1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(0, 7, 1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(0, 7, 2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(1, 7, -2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(1, 7, -1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(1, 7, 0));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(1, 7, 1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(1, 7, 2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(2, 7, -1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(2, 7, 0));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(2, 7, 1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(-3, 8, -2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(-3, 8, -1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(-3, 8, 0));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(-3, 8, 1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(-3, 8, 2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(-2, 8, -3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(-2, 8, -2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(-2, 8, 2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(-2, 8, 3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(-1, 8, -3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(-1, 8, 3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(0, 8, -3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(0, 8, 3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(1, 8, -3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(1, 8, 3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(2, 8, -3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(2, 8, -2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(2, 8, 2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(2, 8, 3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(3, 8, -2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(3, 8, -1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(3, 8, 0));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(3, 8, 1));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(3, 8, 2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(0, 10, 3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(0, 10, 4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(0, 10, 5));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(1, 10, 3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(1, 10, 5));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(2, 10, 3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(2, 10, 4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(2, 10, 5));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(-1, 11, 3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(-1, 11, 4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(-1, 11, 5));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(0, 11, 2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(0, 11, 6));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(1, 11, 2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(1, 11, 6));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(2, 11, 2));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(2, 11, 6));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(3, 11, 3));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(3, 11, 4));
                placeMushroom(MUSHROOM, worldIn, mainmutable.setPos(pos).move(3, 11, 5));
            }
        }
        return true;
    }
}