package com.jackieflash.component;

import com.simsilica.es.EntityComponent;

public class VertexIndexAssociatedComponent implements EntityComponent {

    private int[] indexList;

    public VertexIndexAssociatedComponent() {
    }

    public VertexIndexAssociatedComponent(int[] indexList) {
        this.indexList = indexList;
    }

    public int[] getIndexList() {
        return indexList;
    }
}
