package com.jackieflash.component;

import com.simsilica.es.EntityComponent;

public class VisualComponent implements EntityComponent {
    public static final String WIREFRAME_UNIT_CUBE = "wireframe_unit_cube";
    public static final String SOLID_UNIT_CUBE = "solid_unit_cube";
    public static final String VERTEX = "vertex";
    public static final String GRID = "grid";
    public static final String MESH = "mesh";
    private String name;
    private Boolean isVisible;
    private Integer repeat;

    public VisualComponent() {
    }

    public VisualComponent(String name, Boolean isVisible, Integer repeat) {
        this.name = name;
        this.isVisible = isVisible;
        this.repeat = repeat;
    }

    public String getName() {
        return name;
    }

    public Boolean getVisible() {
        return isVisible;
    }

    public Integer getRepeat() {
        return repeat;
    }

    @Override
    public String toString() {
        return "model:" + name + "";
    }
}