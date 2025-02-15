package com.kryeit;

import com.kryeit.event.BreakHandler;
import com.kryeit.event.EatHandler;
import com.kryeit.event.KillHandler;
import com.kryeit.event.PlaceHandler;
import io.github.fabricators_of_create.porting_lib.entity.events.living.LivingEntityEvents;
import io.github.fabricators_of_create.porting_lib.event.common.BlockEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public class MissionHandler {
    public static void registerEvents() {
        PlayerBlockBreakEvents.AFTER.register(new BreakHandler());
        BlockEvents.AFTER_PLACE.register(new PlaceHandler());
        LivingEntityEvents.FINISH_USING_ITEM.register(new EatHandler());
        ServerEntityCombatEvents.AFTER_KILLED_OTHER_ENTITY.register(new KillHandler());
    }

    public static boolean isNotServerPlayer(Player player) {
        return !(player instanceof ServerPlayer);
    }
}
