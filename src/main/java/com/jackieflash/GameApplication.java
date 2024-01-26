package com.jackieflash;


import com.jackieflash.state.StartAppState;
import com.jme3.app.SimpleApplication;

public class GameApplication extends SimpleApplication {

    @Override
    public void simpleInitApp() {
        flyCam.setMoveSpeed(5);
        stateManager.attach(new StartAppState());


    }

}
