package com.mrbysco.morecauldrons.util;

import com.mrbysco.morecauldrons.init.CauldronReg;
import com.mrbysco.morecauldrons.init.CauldronRegistry;
import net.minecraft.world.level.block.state.BlockState;

public class CauldronHelper {
	public static BlockState getEquivalentCauldron(BlockState state) {
		for(CauldronReg reg : CauldronRegistry.CAULDRON_REGS) {
			if(reg.matchesCauldronType(state)) {
				return reg.getCauldron().get().defaultBlockState();
			}
		}

		return null;
	}

	public static BlockState getEquivalentWaterCauldron(BlockState state) {
		for(CauldronReg reg : CauldronRegistry.CAULDRON_REGS) {
			if(reg.matchesCauldronType(state)) {
				return reg.getWaterCauldron().get().defaultBlockState();
			}
		}

		return null;
	}

	public static BlockState getEquivalentLavaCauldron(BlockState state) {
		for(CauldronReg reg : CauldronRegistry.CAULDRON_REGS) {
			if(reg.matchesCauldronType(state)) {
				return reg.getLavaCauldron().get().defaultBlockState();
			}
		}

		return null;
	}

	public static BlockState getEquivalentSnowCauldron(BlockState state) {
		for(CauldronReg reg : CauldronRegistry.CAULDRON_REGS) {
			if(reg.matchesCauldronType(state)) {
				return reg.getPowderSnowCauldron().get().defaultBlockState();
			}
		}

		return null;
	}
}
