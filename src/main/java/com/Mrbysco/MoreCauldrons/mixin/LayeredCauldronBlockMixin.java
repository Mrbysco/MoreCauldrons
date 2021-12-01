package com.mrbysco.morecauldrons.mixin;

import com.mrbysco.morecauldrons.ModReference;
import com.mrbysco.morecauldrons.util.CauldronHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LayeredCauldronBlock.class)
public class LayeredCauldronBlockMixin {
	@Shadow @Final public static IntegerProperty LEVEL;

	@Inject(at = @At("HEAD"),
			method = "lowerFillLevel(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;)V",
			cancellable = true)
	private static void lowerFillLevel(BlockState state, Level level, BlockPos pos, CallbackInfo ci) {
		if(state.getBlock().getRegistryName().getNamespace().equals(ModReference.MOD_ID)) {
			int i = state.getValue(LEVEL) - 1;
			BlockState cauldronState = state;
			BlockState equalState = CauldronHelper.getEquivalentCauldron(state);
			if(equalState != null) {
				cauldronState = equalState;
			}

			level.setBlockAndUpdate(pos, i == 0 ? cauldronState : state.setValue(LEVEL, Integer.valueOf(i)));

			ci.cancel();
		}
	}
}
