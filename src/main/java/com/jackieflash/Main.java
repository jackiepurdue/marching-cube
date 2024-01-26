package com.jackieflash;

import com.jme3.system.AppSettings;

public class Main {

    public static void main(String[] args) {
        GameApplication application = new GameApplication();
        AppSettings settings = new AppSettings(true);

        settings.setWidth(1600);
        settings.setHeight(900);
        settings.setVSync(true);
        settings.setSamples(4);
        settings.setFrameRate(60);
        settings.setFrequency(60);
        //settings.setFullscreen(true);

        application.setSettings(settings);
        application.setShowSettings(false);
        application.start();
    }

}

