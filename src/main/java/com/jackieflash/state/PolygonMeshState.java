package com.jackieflash.state;

import com.jackieflash.component.*;
import com.jackieflash.state.constants.MarchingCubeConstants;
import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.math.Vector3f;
import com.simsilica.es.Entity;
import com.simsilica.es.EntityData;
import com.simsilica.es.EntityId;
import com.simsilica.es.EntitySet;

import java.util.HashMap;

public class PolygonMeshState extends AbstractAppState {

    private final EntityData entityData;
    private final EntityComponentFactory entityComponentFactory;
    private AppStateManager appStateManager;
    HashMap<String, HashMap<String, EntityId>> uniqueConstraints;
    EntityBuilder entityBuilder;

    public PolygonMeshState(EntityData entityData, EntityBuilder entityBuilder) {
        this.entityData = entityData;
        this.uniqueConstraints = new HashMap<>();
        this.entityComponentFactory = new EntityComponentFactory();
        this.entityBuilder = entityBuilder;
    }


    @Override
    public void initialize(AppStateManager appStateManager, Application application) {
        super.initialize(appStateManager, application);
        this.appStateManager = appStateManager;

        EntityId unitCubeBasedVertexAssociatedEntityId = entityData.createEntity();

        entityBuilder.withUnitCubeBasedVertexAssociatedComponent()
                .build(unitCubeBasedVertexAssociatedEntityId, entityData);

        VertexAssociatedComponent unitCubeChecker = entityData.getComponent(
                unitCubeBasedVertexAssociatedEntityId,
                VertexAssociatedComponent.class
        );

        Vector3f[] localCubeVerticies = unitCubeChecker.getVertices();
        Vector3f lv0 = localCubeVerticies[0];
        Vector3f lv1 = localCubeVerticies[1];
        Vector3f lv2 = localCubeVerticies[2];
        Vector3f lv3 = localCubeVerticies[3];
        Vector3f lv4 = localCubeVerticies[4];
        Vector3f lv5 = localCubeVerticies[5];
        Vector3f lv6 = localCubeVerticies[6];
        Vector3f lv7 = localCubeVerticies[7];

        EntitySet vertices = entityData.getEntities(
                TransformedComponent.class,
                BoundedComponent.class,
                MapableComponent.class,
                VertexAssociatedComponent.class
        );


        for (Entity e : vertices) {
            Vector3f position = e.get(TransformedComponent.class).getPosition();
            Vector3f v0 = lv0.add(position);
            Vector3f v1 = lv1.add(position);
            Vector3f v2 = lv2.add(position);
            Vector3f v3 = lv3.add(position);
            Vector3f v4 = lv4.add(position);
            Vector3f v5 = lv5.add(position);
            Vector3f v6 = lv6.add(position);
            Vector3f v7 = lv7.add(position);

            // These will have some null at the edges... find a safe solution. for now we will just ignore
            EntityId v0entityId = entityBuilder.getMappedEntityIdByTypeAndIdentifier("vertex_position", v0.x + "," + v0.y + "," + v0.z);
            EntityId v1entityId = entityBuilder.getMappedEntityIdByTypeAndIdentifier("vertex_position", v1.x + "," + v1.y + "," + v1.z);
            EntityId v2entityId = entityBuilder.getMappedEntityIdByTypeAndIdentifier("vertex_position", v2.x + "," + v2.y + "," + v2.z);
            EntityId v3entityId = entityBuilder.getMappedEntityIdByTypeAndIdentifier("vertex_position", v3.x + "," + v3.y + "," + v3.z);
            EntityId v4entityId = entityBuilder.getMappedEntityIdByTypeAndIdentifier("vertex_position", v4.x + "," + v4.y + "," + v4.z);
            EntityId v5entityId = entityBuilder.getMappedEntityIdByTypeAndIdentifier("vertex_position", v5.x + "," + v5.y + "," + v5.z);
            EntityId v6entityId = entityBuilder.getMappedEntityIdByTypeAndIdentifier("vertex_position", v6.x + "," + v6.y + "," + v6.z);
            EntityId v7entityId = entityBuilder.getMappedEntityIdByTypeAndIdentifier("vertex_position", v7.x + "," + v7.y + "," + v7.z);
            // These will have some null at the edges... find a safe solution. for now we will just ignore
            if (v0entityId != null
                    && v1entityId != null
                    && v2entityId != null
                    && v3entityId != null
                    && v4entityId != null
                    && v5entityId != null
                    && v6entityId != null
                    && v7entityId != null

            ) {

                Boolean[] marchingCubeMap = {
                        entityData.getComponent(v0entityId, BoundedComponent.class).getAlpha() >= entityData.getComponent(v0entityId, BoundedComponent.class).getThreshold(),
                        entityData.getComponent(v1entityId, BoundedComponent.class).getAlpha() >= entityData.getComponent(v1entityId, BoundedComponent.class).getThreshold(),
                        entityData.getComponent(v2entityId, BoundedComponent.class).getAlpha() >= entityData.getComponent(v2entityId, BoundedComponent.class).getThreshold(),
                        entityData.getComponent(v3entityId, BoundedComponent.class).getAlpha() >= entityData.getComponent(v3entityId, BoundedComponent.class).getThreshold(),
                        entityData.getComponent(v4entityId, BoundedComponent.class).getAlpha() >= entityData.getComponent(v4entityId, BoundedComponent.class).getThreshold(),
                        entityData.getComponent(v5entityId, BoundedComponent.class).getAlpha() >= entityData.getComponent(v5entityId, BoundedComponent.class).getThreshold(),
                        entityData.getComponent(v6entityId, BoundedComponent.class).getAlpha() >= entityData.getComponent(v6entityId, BoundedComponent.class).getThreshold(),
                        entityData.getComponent(v7entityId, BoundedComponent.class).getAlpha() >= entityData.getComponent(v7entityId, BoundedComponent.class).getThreshold(),

                };

                float[][] surroundingVerAlphas = {
                        {
                                entityData.getComponent(v0entityId, BoundedComponent.class).getAlpha(),
                                entityData.getComponent(v1entityId, BoundedComponent.class).getAlpha(),
                                entityData.getComponent(v2entityId, BoundedComponent.class).getAlpha(),
                                entityData.getComponent(v3entityId, BoundedComponent.class).getAlpha(),
                                entityData.getComponent(v4entityId, BoundedComponent.class).getAlpha(),
                                entityData.getComponent(v5entityId, BoundedComponent.class).getAlpha(),
                                entityData.getComponent(v6entityId, BoundedComponent.class).getAlpha(),
                                entityData.getComponent(v7entityId, BoundedComponent.class).getAlpha()
                        },
                        {
                                entityData.getComponent(v0entityId, BoundedComponent.class).getThreshold(),
                                entityData.getComponent(v1entityId, BoundedComponent.class).getThreshold(),
                                entityData.getComponent(v2entityId, BoundedComponent.class).getThreshold(),
                                entityData.getComponent(v3entityId, BoundedComponent.class).getThreshold(),
                                entityData.getComponent(v4entityId, BoundedComponent.class).getThreshold(),
                                entityData.getComponent(v5entityId, BoundedComponent.class).getThreshold(),
                                entityData.getComponent(v6entityId, BoundedComponent.class).getThreshold(),
                                entityData.getComponent(v7entityId, BoundedComponent.class).getThreshold()
                        }
                };

                EntityId marchingCube = entityData.createEntity();
                Vector3f[] surroundngVertPositions = {v0, v1, v2, v3, v4, v5, v6, v7};
                entityBuilder.withTransformedComponent(position)
                        .withVertexAssociatedComponent(surroundngVertPositions)
                        .withVertexValueAssociatedComponent(surroundingVerAlphas).build(marchingCube, entityData);
                Integer cubeInt = computeCubeIndex(marchingCubeMap);
                createTriangle(cubeInt, marchingCube, position);
            }
        }
        vertices.release();
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


    public int createTriangle(int cubeindex, EntityId gridMarchingEntity, Vector3f position) {

        Vector3f[] gridPs = this.entityData.getComponent(gridMarchingEntity, VertexAssociatedComponent.class).getVertices();
        float[][] gridValsAndThresholds = this.entityData.getComponent(gridMarchingEntity, VertexValueAssociatedComponent.class).getVertexValues();
        float[] gridVals = gridValsAndThresholds[0];
        float[] gridThresholds = gridValsAndThresholds[1];


        Vector3f vertlist[] = new Vector3f[12];

        /* Cube is entirely in/out of the surface */
        if (MarchingCubeConstants.EDGE_TABLE[cubeindex] == 0)
            return (0);

        /* Find the vertices where the surface intersects the cube */
        if ((MarchingCubeConstants.EDGE_TABLE[cubeindex] & 1) == 1)
            vertlist[0] =
                    VertexInterp(gridThresholds[0], gridPs[0], gridPs[1], gridVals[0], gridVals[1]);
        if ((MarchingCubeConstants.EDGE_TABLE[cubeindex] & 2) == 2)
            vertlist[1] =
                    VertexInterp(gridThresholds[1], gridPs[1], gridPs[2], gridVals[1], gridVals[2]);
        if ((MarchingCubeConstants.EDGE_TABLE[cubeindex] & 4) == 4)
            vertlist[2] =
                    VertexInterp(gridThresholds[2], gridPs[2], gridPs[3], gridVals[2], gridVals[3]);
        if ((MarchingCubeConstants.EDGE_TABLE[cubeindex] & 8) == 8)
            vertlist[3] =
                    VertexInterp(gridThresholds[3], gridPs[3], gridPs[0], gridVals[3], gridVals[0]);
        if ((MarchingCubeConstants.EDGE_TABLE[cubeindex] & 16) == 16)
            vertlist[4] =
                    VertexInterp(gridThresholds[4], gridPs[4], gridPs[5], gridVals[4], gridVals[5]);
        if ((MarchingCubeConstants.EDGE_TABLE[cubeindex] & 32) == 32)
            vertlist[5] =
                    VertexInterp(gridThresholds[5], gridPs[5], gridPs[6], gridVals[5], gridVals[6]);
        if ((MarchingCubeConstants.EDGE_TABLE[cubeindex] & 64) == 64)
            vertlist[6] =
                    VertexInterp(gridThresholds[6], gridPs[6], gridPs[7], gridVals[6], gridVals[7]);
        if ((MarchingCubeConstants.EDGE_TABLE[cubeindex] & 128) == 128)
            vertlist[7] =
                    VertexInterp(gridThresholds[7], gridPs[7], gridPs[4], gridVals[7], gridVals[4]);
        if ((MarchingCubeConstants.EDGE_TABLE[cubeindex] & 256) == 256)
            vertlist[8] =
                    VertexInterp(gridThresholds[0], gridPs[0], gridPs[4], gridVals[0], gridVals[4]);
        if ((MarchingCubeConstants.EDGE_TABLE[cubeindex] & 512) == 512)
            vertlist[9] =
                    VertexInterp(gridThresholds[1], gridPs[1], gridPs[5], gridVals[1], gridVals[5]);
        if ((MarchingCubeConstants.EDGE_TABLE[cubeindex] & 1024) == 1024)
            vertlist[10] =
                    VertexInterp(gridThresholds[2], gridPs[2], gridPs[6], gridVals[2], gridVals[6]);
        if ((MarchingCubeConstants.EDGE_TABLE[cubeindex] & 2048) == 2048)
            vertlist[11] =
                    VertexInterp(gridThresholds[3], gridPs[3], gridPs[7], gridVals[3], gridVals[7]);

        /* Create the triangle */
        //problem in the vert list creation... figure it out
        int ntriang = 0;
        for (int i = 0; MarchingCubeConstants.TRI_TABLE[cubeindex][i] != -1; i += 3) {
            // triangles[ntriang].p[0] =
            int tp0i = MarchingCubeConstants.TRI_TABLE[cubeindex][i];
            Vector3f tp0 = vertlist[tp0i];
            // triangles[ntriang].p[1] =
            int tp1i = MarchingCubeConstants.TRI_TABLE[cubeindex][i + 1];
            Vector3f tp1 = vertlist[tp1i];
            // triangles[ntriang].p[2] =
            int tp2i = MarchingCubeConstants.TRI_TABLE[cubeindex][i + 2];
            Vector3f tp2 = vertlist[tp2i];
            ntriang++;
            //System.out.print(tp0 + " " + tp1 + " " + tp2 + " -- ");

            // make a triangle entitiy (a mesh entity)
            Vector3f[] triverts = {tp0, tp1, tp2};
            int[] triinds = {0, 1, 2};
            EntityId triangleEntityId = entityData.createEntity();
            //EntityBuilder entityBuilder = new EntityBuilder(entityComponentFactory, uniqueConstraints);
            entityBuilder.withVisualComponent(VisualComponent.MESH)
                    .withVertexAssociatedComponent(triverts)
                    .withVertexIndexAssociatedComponent(triinds)
                    .build(triangleEntityId, entityData);

            int x = 2;
        }
        return (ntriang);
    }

    public static Vector3f VertexInterp(float isolevel, Vector3f p1, Vector3f p2, float valp1, float valp2) {
        float mu;
        Vector3f p = new Vector3f();
        if (Math.abs(isolevel - valp1) < 0.00001)
            return (p1);
        if (Math.abs(isolevel - valp2) < 0.00001)
            return (p2);
        if (Math.abs(valp1 - valp2) < 0.00001)
            return (p1);
        mu = (isolevel - valp1) / (valp2 - valp1);
        p.x = p1.x + mu * (p2.x - p1.x);
        p.y = p1.y + mu * (p2.y - p1.y);
        p.z = p1.z + mu * (p2.z - p1.z);
        return (p);
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

