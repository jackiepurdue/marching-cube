package com.jackieflash.component;

import com.simsilica.es.EntityComponent;

public class IndividualizedComponent implements EntityComponent {

    private String identifer;
    private Integer numberAllowed;

    public IndividualizedComponent() {
    }

    public IndividualizedComponent(String identifer, Integer numberAllowed) {
        this.identifer = identifer;
        this.numberAllowed = numberAllowed;
    }

    public String getIdentifier() {
        return identifer;
    }

    public Integer getNumberAllowed() {
        return numberAllowed;
    }
}
