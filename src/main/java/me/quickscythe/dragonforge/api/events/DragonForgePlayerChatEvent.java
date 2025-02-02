package me.quickscythe.dragonforge.api.events;

import me.quickscythe.dragonforge.api.entity.DragonForgePlayer;
import me.quickscythe.dragonforge.api.events.listeners.Listener;

public record DragonForgePlayerChatEvent(DragonForgePlayer player, String message) implements DragonForgeEvent {
    @Override
    public Class<? extends Listener> listener() {
        return Listener.QuiptPlayerChatListener.class;
    }
}
