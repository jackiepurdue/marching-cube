package com.jackieflash.component;

import com.jme3.math.Vector3f;
import com.simsilica.es.EntityComponent;

public class VertexAssociatedComponent implements EntityComponent {

    private Vector3f[] vertices;

    public VertexAssociatedComponent() {
    }

    public VertexAssociatedComponent(Vector3f[] vertices) {
        this.vertices = vertices;
    }

    public Vector3f[] getVertices() {
        return vertices;
    }
}
