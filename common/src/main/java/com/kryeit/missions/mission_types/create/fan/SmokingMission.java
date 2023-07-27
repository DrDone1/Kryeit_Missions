package com.kryeit.missions.mission_types.create.fan;

import com.kryeit.missions.MissionDifficulty;
import com.kryeit.missions.mission_types.MultiResourceMissionType;
import net.minecraft.network.chat.Component;

public class SmokingMission implements MultiResourceMissionType {

    @Override
    public String id() {
        return "smoke";
    }

    @Override
    public MissionDifficulty difficulty() {
        return MissionDifficulty.EASY;
    }

    @Override
    public Component description() {
        return Component.nullToEmpty("Smoking mission");
    }
}
