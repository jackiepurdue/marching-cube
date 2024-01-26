package com.jackieflash.state;

import com.jackieflash.component.VisualComponent;
import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.math.Vector3f;
import com.simsilica.es.EntityData;
import com.simsilica.es.EntityId;

import java.util.HashMap;

public class DebugAppState extends AbstractAppState {

    private final EntityData entityData;
    private AppStateManager appStateManager;

    public DebugAppState(EntityData entityData) {
        this.entityData = entityData;
    }


    @Override
    public void initialize(AppStateManager appStateManager, Application application) {
        super.initialize(appStateManager, application);
        this.appStateManager = appStateManager;

        //now these new parameters don't allow for communication between other maps etc
        EntityBuilder entityBuilder = new EntityBuilder(new EntityComponentFactory(), new HashMap<>());
        EntityId gridId = entityData.createEntity();
        entityBuilder.withVisualComponent(VisualComponent.GRID, 32).withTransformedComponent(Vector3f.ZERO).build(gridId, entityData);


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

