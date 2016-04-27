package me.MiniDigger.Foundation.Handler;

import lombok.extern.java.Log;
import me.MiniDigger.Foundation.Game.GameHandler;
import me.MiniDigger.Foundation.Lang.Lang;
import me.MiniDigger.Foundation.Lang.LangHandler;

/**
 * LLLLLLLLOOOOLL That name
 */
@Log
public class HandlerHandler {

    private static HandlerHandler INSTANCE;

    private final Handler[] handlers = {GameHandler.getInstance(), LangHandler.getInstance()};

    public void load(){
        for(Handler h : handlers){
            if(!h.onLoad()){
                Lang.error(new RuntimeException("Error while loading " + h.getClass().getName()));
            }
        }
    }

    public void disable(){
        for(Handler h : handlers){
            if(!h.onDisable()){
                Lang.error(new RuntimeException("Error while disabling " + h.getClass().getName()));
            }
        }
    }

    public static HandlerHandler getInstance(){
        if(INSTANCE == null){
            INSTANCE = new HandlerHandler();
        }
        return INSTANCE;
    }
}
