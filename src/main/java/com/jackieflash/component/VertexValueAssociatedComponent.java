package com.jackieflash.component;

import com.simsilica.es.EntityComponent;

public class VertexValueAssociatedComponent implements EntityComponent {

    private float[][] vertexValues;

    public VertexValueAssociatedComponent() {
    }

    public VertexValueAssociatedComponent(float[][] vertexValues) {
        this.vertexValues = vertexValues;
    }

    public float[][] getVertexValues() {
        return vertexValues;
    }
}
