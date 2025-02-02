package me.quickscythe.dragonforge.api.events;

import me.quickscythe.dragonforge.api.DragonForgeIntegration;
import me.quickscythe.dragonforge.api.events.listeners.Listener;

import java.util.ArrayList;
import java.util.List;

public class EventHandler {

    private final DragonForgeIntegration integration;
    private final List<Listener> listeners = new ArrayList<>();

    public EventHandler(DragonForgeIntegration integration){
        this.integration = integration;
    }

    public void register(Listener listener){
        listeners.add(listener);
    }

    public void handle(DragonForgeEvent event){
        for(Listener listener : listeners){
            if(event.listener().isAssignableFrom(listener.getClass())){
                if(event.listener().equals(Listener.QuiptPlayerJoinListener.class)) ((Listener.QuiptPlayerJoinListener) listener).onPlayerJoin((DragonForgePlayerJoinEvent) event);
                if(event.listener().equals(Listener.QuiptPlayerLeaveListener.class)) ((Listener.QuiptPlayerLeaveListener) listener).onPlayerLeave((DragonForgePlayerLeaveEvent) event);
                if(event.listener().equals(Listener.QuiptPlayerDeathEventListener.class)) ((Listener.QuiptPlayerDeathEventListener) listener).onPlayerDeath((DragonForgePlayerDeathEvent) event);
                if(event.listener().equals(Listener.QuiptPlayerChatListener.class)) ((Listener.QuiptPlayerChatListener) listener).onPlayerChat((DragonForgePlayerChatEvent) event);
            }
        }
    }
}
