package com.jackieflash.component;

import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.simsilica.es.EntityComponent;

public class TransformedComponent implements EntityComponent {

    private Vector3f position;
    private Quaternion rotation;

    public TransformedComponent() {
    }

    public TransformedComponent(Vector3f position, Quaternion rotation) {
        this.position = position;
        this.rotation = rotation;
    }

    public Vector3f getPosition() {
        return position;
    }

    public Quaternion getRotation(){
        return rotation;
    }

}
