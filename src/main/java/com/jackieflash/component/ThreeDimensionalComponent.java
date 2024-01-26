package com.jackieflash.component;

import com.simsilica.es.EntityComponent;

public class ThreeDimensionalComponent implements EntityComponent {

    private Float width;
    private Float height;
    private Float depth;

    public ThreeDimensionalComponent() {
    }

    public ThreeDimensionalComponent(Float width, Float height, Float depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
    }

    public Float getWidth() {
        return width;
    }

    public Float getHeight() {
        return height;
    }

    public Float getDepth() {
        return depth;
    }
}
