package com.jackieflash.component;

import com.simsilica.es.EntityComponent;

public class BoundedComponent implements EntityComponent {

    private Float alpha;
    private Float threshold;

    public BoundedComponent() {
    }

    public BoundedComponent(float alpha, float threshold) {
        this.alpha = alpha;
        this.threshold = threshold;
    }

    public Float getAlpha() {
        return alpha;
    }

    public Float getThreshold() {
        return threshold;
    }

}
