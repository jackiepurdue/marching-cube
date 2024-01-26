package com.jackieflash.state;

import com.jackieflash.component.*;
import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.math.Vector3f;
import com.simsilica.es.EntityData;
import com.simsilica.es.EntityId;
import com.simsilica.es.EntitySet;

import java.util.HashMap;
import java.util.function.BiPredicate;

public class VolumeDataState extends AbstractAppState {

    private final EntityData entityData;
    private final EntityComponentFactory entityComponentFactory;
    private AppStateManager appStateManager;
    //TODO: make an object to encapsulate this.
    HashMap<String, HashMap<String, EntityId>> uniqueConstraints;
    EntityBuilder entityBuilder;

    public VolumeDataState(EntityData entityData) {
        this.entityData = entityData;
        this.uniqueConstraints = new HashMap<>();
        this.entityComponentFactory = new EntityComponentFactory();
        this.entityBuilder = new EntityBuilder(entityComponentFactory, uniqueConstraints);
    }

    @Override
    public void initialize(AppStateManager appStateManager, Application application) {
        super.initialize(appStateManager, application);
        this.appStateManager = appStateManager;

        createBasePoints(32);

        EntitySet vertices = entityData.getEntities(
                TransformedComponent.class,
                BoundedComponent.class,
                MapableComponent.class,
                VertexAssociatedComponent.class
        );

        vertices.release();

        createHotVertexCube(new Vector3f(0, 0, 0));
        createHotVertexCube(new Vector3f(0, 0, 1));
        createHotVertexCube(new Vector3f(0, 0, 2));
        createHotVertexCube(new Vector3f(0, 0, 1));
        createHotVertexCube(new Vector3f(0, 0, 0));
        createHotVertexCube(new Vector3f(1, 0, 1));
        createHotVertexCube(new Vector3f(1, 0, 2));
        createHotVertexCube(new Vector3f(1, 0, 1));

        createHotVertexCube(new Vector3f(1, 0, 0));
        createHotVertexCube(new Vector3f(1, 0, 1));
        createHotVertexCube(new Vector3f(1, 0, 2));
        createHotVertexCube(new Vector3f(1, 0, 1));
        createHotVertexCube(new Vector3f(1, 0, 0));
        createHotVertexCube(new Vector3f(2, 0, 1));
        createHotVertexCube(new Vector3f(2, 0, 2));
        createHotVertexCube(new Vector3f(2, 0, 1));

        createHotVertexCube(new Vector3f(0, 3, 0));
        createHotVertexCube(new Vector3f(0, 3, 1));
        createHotVertexCube(new Vector3f(0, 3, 2));
        createHotVertexCube(new Vector3f(0, 3, 1));
        createHotVertexCube(new Vector3f(0, 3, 0));
        createHotVertexCube(new Vector3f(1, 3, 1));
        createHotVertexCube(new Vector3f(1, 3, 2));
        createHotVertexCube(new Vector3f(1, 3, 1));
        deleteHotVertex(new Vector3f(1,0,1));
        createVertex(new Vector3f(0, 2, 0), Boolean.TRUE);

        attachStates(appStateManager);

    }

    private void attachStates(AppStateManager appStateManager) {
        appStateManager.attach(new PolygonMeshState(this.entityData, this.entityBuilder));
        appStateManager.attach(new VisualAppState(entityData));
    }


    public static int computeCubeIndex(Boolean[] values) {
        int cubeindex = 0;
        if (values[0]) cubeindex |= 1;
        if (values[1]) cubeindex |= 2;
        if (values[2]) cubeindex |= 4;
        if (values[3]) cubeindex |= 8;
        if (values[4]) cubeindex |= 16;
        if (values[5]) cubeindex |= 32;
        if (values[6]) cubeindex |= 64;
        if (values[7]) cubeindex |= 128;
        return cubeindex;
    }

    //TODO: Do concurrent once things get more serious
    private void createBasePoints(int i) {
        for (int x = -i / 2; x < i / 2; x += 1) {
            for (int y = -i / 2; y < i / 2; y += 1) {
                for (int z = -i / 2; z < i / 2; z += 1) {
                    createVertex(new Vector3f(x, y, z), Boolean.FALSE);
                }
            }
        }
    }


    private EntityBuilder createHotVertexCube(Vector3f position) {

        createVertex(position.add(Vector3f.UNIT_Z).add(Vector3f.UNIT_Y), Boolean.TRUE);
        //create this with a vertexassociated and whatever
        //
        createVertex(position.add(Vector3f.UNIT_XYZ), Boolean.TRUE);

        createVertex(position.add(Vector3f.UNIT_X), Boolean.TRUE);
        createVertex(position.add(Vector3f.UNIT_Y), Boolean.TRUE);
        createVertex(position.add(Vector3f.UNIT_Z), Boolean.TRUE);

        createVertex(position.add(Vector3f.UNIT_X).add(Vector3f.UNIT_Z), Boolean.TRUE);
        createVertex(position.add(Vector3f.UNIT_X).add(Vector3f.UNIT_Y), Boolean.TRUE);
        return createVertex(position, Boolean.TRUE);

    }

    private EntityBuilder deleteHotVertex(Vector3f position) {

        EntityId dwad = entityBuilder.getMappedEntityIdByTypeAndIdentifier("vertex_position", position.getX() + "," + position.getY() + "," + position.getZ());
        entityData.removeEntity(dwad);

        return createVertex(position, Boolean.FALSE);

    }

    private EntityBuilder createVertex(Vector3f position, Boolean isAboveThreshold) {

        BiPredicate<EntityId, EntityId> thresholdOverwritePredicate = (entityId, oldEntityId) -> {
            BoundedComponent oldBoundedComponent = entityData.getComponent(oldEntityId, BoundedComponent.class);
            BoundedComponent newBoundedComponent = entityData.getComponent(entityId, BoundedComponent.class);

            if (oldBoundedComponent != null && oldBoundedComponent.getThreshold() <= oldBoundedComponent.getAlpha()) {
                return false;
            } else if (newBoundedComponent != null && newBoundedComponent.getThreshold() >= newBoundedComponent.getAlpha()) {
                return false;
            }
            return true;
        };

        //EntityBuilder entityBuilder = new EntityBuilder(this.entityComponentFactory, this.uniqueConstraints);
        EntityId pointEntity = entityData.createEntity();
        if (isAboveThreshold) {
            entityBuilder.withTransformedComponent(position).withSingleVertexAssociatedComponent().withThresholdAbove()
                    .withVisualComponent(VisualComponent.VERTEX, 255)
                    .withUniquelyMapableComponent("vertex_position", position.getX() + "," + position.getY() + "," + position.getZ())
                    .build(pointEntity, entityData, thresholdOverwritePredicate);
        } else {
            entityBuilder.withTransformedComponent(position).withSingleVertexAssociatedComponent().withThresholdBelow()
                    //.withVisualComponent(VisualComponent.VERTEX, 20)
                    .withUniquelyMapableComponent("vertex_position", position.getX() + "," + position.getY() + "," + position.getZ())
                    .build(pointEntity, entityData, thresholdOverwritePredicate);
        }


        return entityBuilder;
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

