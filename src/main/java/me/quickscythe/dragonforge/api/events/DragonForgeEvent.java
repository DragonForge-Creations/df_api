package me.quickscythe.dragonforge.api.events;

import me.quickscythe.dragonforge.api.events.listeners.Listener;

public interface DragonForgeEvent {

    Class<? extends Listener> listener();

}
