package me.quickscythe.dragonforge.api.events;

import me.quickscythe.dragonforge.api.entity.DragonForgePlayer;
import me.quickscythe.dragonforge.api.events.listeners.Listener;
import org.jetbrains.annotations.Nullable;

public record DragonForgePlayerDeathEvent(DragonForgePlayer player, @Nullable DragonForgePlayer killer, String message) implements DragonForgeEvent {
    @Override
    public Class<? extends Listener> listener() {
        return Listener.QuiptPlayerDeathEventListener.class;
    }
}
