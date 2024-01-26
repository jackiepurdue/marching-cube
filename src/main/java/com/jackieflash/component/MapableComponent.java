package com.jackieflash.component;

import com.simsilica.es.EntityComponent;

public class MapableComponent implements EntityComponent {

    private Boolean isIndividualized;
    private String type;
    private String identifer;

    public MapableComponent() {
    }

    public MapableComponent(String type, String identifier, Boolean isIndividualized) {
        this.type = type;
        this.identifer = identifier;
        this.isIndividualized = isIndividualized;
    }

    public String getType() {
        return type;
    }

    public String getIdentifier() {
        return identifer;
    }

    public boolean getIsIndividualized() {
        return isIndividualized;
    }
}
