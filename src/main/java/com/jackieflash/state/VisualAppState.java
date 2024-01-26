package com.jackieflash.state;

import com.jackieflash.component.VertexAssociatedComponent;
import com.jackieflash.component.VertexIndexAssociatedComponent;
import com.jackieflash.component.VisualComponent;
import com.jackieflash.component.TransformedComponent;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.scene.Spatial;
import com.simsilica.es.Entity;
import com.simsilica.es.EntityData;
import com.simsilica.es.EntitySet;

public class VisualAppState extends AbstractAppState {

    private EntityData entityData;
    private SimpleApplication application;
    private ModelFactory modelFactory;

    public VisualAppState(EntityData entityData) {
        this.entityData = entityData;
    }


    @Override
    public void initialize(AppStateManager appStateManager, Application application) {
        super.initialize(appStateManager, application);
        this.application = (SimpleApplication) application;
        this.modelFactory = new ModelFactory(application.getAssetManager());
        EntitySet models = entityData.getEntities(TransformedComponent.class, VisualComponent.class);
        models.applyChanges();
        models.release();

        for (Entity e: models){
            Spatial cube = modelFactory.create(e.get(VisualComponent.class), e.getId());
            cube.setLocalTranslation(e.get(TransformedComponent.class).getPosition());
            //cube.scale(e.get(Rectangle.class).getWidth(),e.get(Rectangle.class).getDepth(),e.get(Rectangle.class).getHeight());
            ((SimpleApplication) application).getRootNode().attachChild(cube);
        }

        EntitySet meshes = entityData.getEntities(VisualComponent.class, VertexAssociatedComponent.class, VertexIndexAssociatedComponent.class);
        meshes.applyChanges();
        meshes.release();


        for (Entity e: meshes){
            Spatial cube = modelFactory.create(e.get(VisualComponent.class), e.get(VertexAssociatedComponent.class), e.get(VertexIndexAssociatedComponent.class), e.getId());
            //cube.setLocalTranslation(e.get(TransformedComponent.class).getPosition());
            //cube.scale(e.get(Rectangle.class).getWidth(),e.get(Rectangle.class).getDepth(),e.get(Rectangle.class).getHeight());
            ((SimpleApplication) application).getRootNode().attachChild(cube);
        }
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

