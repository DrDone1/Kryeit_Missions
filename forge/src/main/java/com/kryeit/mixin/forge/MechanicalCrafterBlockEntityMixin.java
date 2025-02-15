package com.kryeit.mixin.forge;

import com.kryeit.missions.mission_types.vanilla.CraftMission;
import com.kryeit.mixin.interfaces.BlockEntityAccessor;
import com.kryeit.utils.MixinUtils;
import com.simibubi.create.content.kinetics.crafter.MechanicalCrafterBlockEntity;
import com.simibubi.create.content.kinetics.crafter.RecipeGridHandler;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = MechanicalCrafterBlockEntity.class, remap = false)
public class MechanicalCrafterBlockEntityMixin {

    @Shadow
    protected RecipeGridHandler.GroupedItems groupedItems;
    @Shadow
    private ItemStack scriptedResult;
    @Shadow
    protected int countDown;
    @Inject(method = "tick", at = @At(value = "HEAD"))
    private void onApplyMechanicalCraftingRecipe(CallbackInfo ci) {

        MechanicalCrafterBlockEntity blockEntity = (MechanicalCrafterBlockEntity) (Object) this;
        BlockEntityAccessor accessor = (BlockEntityAccessor) this;

        boolean runLogic = !accessor.getLevel().isClientSide || blockEntity.isVirtual();
        if (!runLogic)
            return;

        ItemStack result =
                blockEntity.isVirtual() ? scriptedResult : RecipeGridHandler.tryToApplyRecipe(accessor.getLevel(), groupedItems);

        if (result != null && countDown == 20)
            MixinUtils.handleMixinMissionItem(accessor, CraftMission.class, result);
    }
}
