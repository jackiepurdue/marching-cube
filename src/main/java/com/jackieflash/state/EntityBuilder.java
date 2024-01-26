package com.jackieflash.state;

import com.jackieflash.component.*;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.simsilica.es.EntityComponent;
import com.simsilica.es.EntityId;

import java.util.*;

public class EntityBuilder extends DefaultEntityBuilder {



    public EntityBuilder(EntityComponentFactory componentFactory, HashMap<String, HashMap<String, EntityId>> uniqueConstraints) {
        super(componentFactory, uniqueConstraints);
    }

    public EntityBuilder(EntityComponentFactory componentFactory, List<EntityComponent> components, HashMap<String, HashMap<String, EntityId>> uniqueConstraints, MapableComponent mapableComponent) {
        super(componentFactory, components, uniqueConstraints, mapableComponent);
    }

    @Override
    protected EntityBuilder withMapableComponent(String type, String identifier) {
        MapableComponent component = componentFactory.createMapableComponent(type, identifier, Boolean.FALSE);
        super.mappableComponent = component;
        return addComponent(component);
    }

    protected EntityBuilder withUniquelyMapableComponent(String type, String identifier) {
        MapableComponent component = componentFactory.createMapableComponent(type, identifier, Boolean.TRUE);
        super.mappableComponent = component;
        return addComponent(component);
    }

    protected EntityBuilder withIndividualizedComponent(String identifier, Integer numberAllowed) {
       IndividualizedComponent component = componentFactory.createIndividualizedComponent(identifier, numberAllowed);
       return addComponent(component);
    }

    protected EntityBuilder withVisualComponent(String modelType) {
        VisualComponent component = componentFactory.createModelComponent(modelType);
        return addComponent(component);
    }

    protected EntityBuilder withVisualComponent(String modelType, Integer repeat) {
        VisualComponent component = componentFactory.createModelComponent(modelType, repeat);
        return addComponent(component);
    }

    public EntityBuilder withThreeDimensionalComponent(Float width, Float height, Float depth) {
        ThreeDimensionalComponent component = componentFactory.createRectangleComponent(width, height, depth);
        return addComponent(component);
    }

    public EntityBuilder withTransformedComponent(Vector3f position) {
        TransformedComponent component = componentFactory.createPositionComponent(position, Quaternion.ZERO);
        return addComponent(component);
    }

    public EntityBuilder withVertexAssociatedComponent(Vector3f[] verticies) {
        VertexAssociatedComponent component = componentFactory.createVertexAssociatedComponent(verticies);
        return addComponent(component);
    }

    public EntityBuilder withVertexIndexAssociatedComponent(int[] indexList) {
        VertexIndexAssociatedComponent component = componentFactory.createVertexIndexAssociatedComponent(indexList);
        return addComponent(component);
    }

    public EntityBuilder withVertexValueAssociatedComponent(float[][] values) {
        VertexValueAssociatedComponent component = componentFactory.createVertexValueAssociatedComponent(values);
        return addComponent(component);
    }

    public EntityBuilder withSingleVertexAssociatedComponent() {
        Vector3f[] vertices = {
                new Vector3f(0,0,0),
        };
        VertexAssociatedComponent component = componentFactory.createVertexAssociatedComponent(vertices);
        return addComponent(component);
    }

    public EntityBuilder withUnitCubeBasedVertexAssociatedComponent() {
        Vector3f[] vertices = {
                new Vector3f(0,0,0),
                new Vector3f(1,0,0),
                new Vector3f(1,0,1),
                new Vector3f(0,0,1),
                new Vector3f(0,1,0),
                new Vector3f(1,1,0),
                new Vector3f(1,1,1),
                new Vector3f(0,1,1)
        };

        VertexAssociatedComponent component = componentFactory.createVertexAssociatedComponent(vertices);
        return addComponent(component);
    }

    public EntityBuilder withThresholdAbove() {
        BoundedComponent component = componentFactory.createThresholdComponent(101f, 100f);
        return addComponent(component);
    }

    public EntityBuilder withThresholdBelow() {
        BoundedComponent component = componentFactory.createThresholdComponent(99f, 100f);
        return addComponent(component);
    }

    @Override
    protected EntityBuilder addComponent(EntityComponent component) {
        List<EntityComponent> newComponents = new ArrayList<>(components);
        newComponents.add(component);
        return new EntityBuilder(componentFactory, newComponents, this.mappables, super.mappableComponent);
    }

    @Override
    protected EntityBuilder addComponent() {
        return new EntityBuilder(componentFactory, Collections.emptyList(), this.mappables, super.mappableComponent);
    }



}