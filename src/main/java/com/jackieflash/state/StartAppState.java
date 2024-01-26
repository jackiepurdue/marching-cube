package com.jackieflash.state;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.simsilica.es.EntityData;
import com.simsilica.es.base.DefaultEntityData;

public class StartAppState extends AbstractAppState {

    private final EntityData entityData;

    public StartAppState() {
        this.entityData = new DefaultEntityData();
    }

    private static void registerMessages() {
        //Serializer.registerClass(ServerConfirmClientMessage.class);
    }

    private void registerComponents() {
        //Serializer.registerClass(GridVertex.class, new FieldSerializer());
        //Serializer.registerClass(GridCube.class, new FieldSerializer());
    }

    private void attachStates(AppStateManager appStateManager) {
        appStateManager.attach(new DebugAppState(entityData));
        appStateManager.attach(new VolumeDataState(entityData));
    }

    @Override
    public void initialize(AppStateManager appStateManager, Application application) {
        super.initialize(appStateManager, application);



        registerComponents();
        attachStates(appStateManager);
    }


    @Override
    public void cleanup() {
        super.cleanup();
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (enabled) {
        } else {
        }
    }

    @Override
    public void update(float tpf) {
    }

}

