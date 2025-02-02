package me.quickscythe.dragonforge.api.events.listeners;

import me.quickscythe.dragonforge.api.events.*;

public interface Listener {


    interface QuiptPlayerLeaveListener extends Listener {

        void onPlayerLeave(DragonForgePlayerLeaveEvent event);

    }

    interface QuiptPlayerDeathEventListener extends Listener {

        void onPlayerDeath(DragonForgePlayerDeathEvent event);

    }

    interface QuiptPlayerChatListener extends Listener {

        void onPlayerChat(DragonForgePlayerChatEvent event);

    }



    interface QuiptPlayerJoinListener extends Listener {

        void onPlayerJoin(DragonForgePlayerJoinEvent event);

    }
}
