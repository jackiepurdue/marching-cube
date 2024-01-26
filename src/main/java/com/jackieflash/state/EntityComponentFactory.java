package com.jackieflash.state;

import com.jackieflash.component.*;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;

public class EntityComponentFactory {
    public TransformedComponent createPositionComponent(Vector3f position, Quaternion quaternion) {
        return new TransformedComponent(position, quaternion);
    }

    public BoundedComponent createThresholdComponent(Float alpha, Float threshold) {
        return new BoundedComponent(alpha, threshold);
    }

    public MapableComponent createMapableComponent(String type, String identifier, Boolean isIndividualized) {
        return new MapableComponent(type, identifier, isIndividualized);
    }

    public ThreeDimensionalComponent createRectangleComponent(Float width, Float height, Float depth) {
        return new ThreeDimensionalComponent(width, height, depth);
    }

    public VisualComponent createModelComponent(String modelType) {
        return new VisualComponent(modelType, Boolean.TRUE, 1);
    }

    public VisualComponent createModelComponent(String modelType, Integer repeat) {
        return new VisualComponent(modelType, Boolean.TRUE, repeat);
    }

    public VertexAssociatedComponent createVertexAssociatedComponent(Vector3f[] verticies) {
        return new VertexAssociatedComponent(verticies);
    }

    public IndividualizedComponent createIndividualizedComponent(String identifier, Integer numberAllowed) {
        return new IndividualizedComponent(identifier, numberAllowed);
    }

    public VertexValueAssociatedComponent createVertexValueAssociatedComponent(float[][] values) {
        return new VertexValueAssociatedComponent(values);
    }

    public VertexIndexAssociatedComponent createVertexIndexAssociatedComponent(int[] indexList) {
        return new VertexIndexAssociatedComponent(indexList);
    }
}
