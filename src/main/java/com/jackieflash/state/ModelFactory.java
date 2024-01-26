package com.jackieflash.state;

import com.jackieflash.component.VertexAssociatedComponent;
import com.jackieflash.component.VertexIndexAssociatedComponent;
import com.jackieflash.component.VisualComponent;
import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.*;
import com.jme3.scene.debug.Grid;
import com.jme3.scene.debug.WireBox;
import com.jme3.scene.shape.Sphere;
import com.jme3.util.BufferUtils;
import com.simsilica.es.EntityId;

public class ModelFactory {



    private final AssetManager assetManager;

    public ModelFactory(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    //do something nicer
    public Spatial create(VisualComponent visualComponent, EntityId entityId) {
        if (visualComponent.getName().equals(VisualComponent.WIREFRAME_UNIT_CUBE)) {
            Geometry g = new Geometry("wireframe cube", new WireBox(0.5f, 0.5f, 0.5f));
            Material mat = new Material(this.assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
            mat.getAdditionalRenderState().setWireframe(true);
            mat.setColor("Color", ColorRGBA.Cyan);
            g.setMaterial(mat);
            Node node = new Node();
            node.attachChild(g);
            node.getChild(0).setLocalTranslation(0.5f,0.5f,0.5f);
            return node;
        } else if(visualComponent.getName().equals(VisualComponent.GRID)){
            Float unitSize = 1f;
            Integer w = visualComponent.getRepeat();
            Integer l = visualComponent.getRepeat();
            Grid grid = new Grid(w+1,l+1,unitSize);
            Geometry geo = new Geometry("OurMesh", grid); // using our custom mesh object
            Material mat = new Material(assetManager,
                    "Common/MatDefs/Misc/Unshaded.j3md");
            mat.setColor("Color", ColorRGBA.Red);
            geo.setMaterial(mat);

            Node gridnode = new Node();


            Grid gridSmall = new Grid(w*4 + 1, l*4 +1, unitSize/4);
            Geometry geoSmall = new Geometry("OurMesh2", gridSmall); // using our custom mesh object
            Material mat2 = new Material(this.assetManager,
                    "Common/MatDefs/Misc/Unshaded.j3md");
            mat2.setColor("Color", ColorRGBA.Brown);
            geoSmall.setMaterial(mat2);

            gridnode.attachChild(geo);
            gridnode.getChild(0).setLocalTranslation(-(float)w/2,-0,-(float)l/2);
            gridnode.attachChild(geoSmall);
            gridnode.getChild(1).setLocalTranslation(-(float)w/2,-0.01f,-(float)l/2);
            return gridnode;
        } else if(visualComponent.getName().equals(VisualComponent.VERTEX)){
            Sphere b = new Sphere(8,8,0.05f); // create cube shape
            Geometry geom = new Geometry("Sphere", b);  // create cube geometry from the shape
            Material mat2 = new Material(assetManager,
                    "Common/MatDefs/Misc/Unshaded.j3md");  // create a simple material
            mat2.setColor("Color", ColorRGBA.fromRGBA255(255,0, visualComponent.getRepeat(),50));   // set color of material to blue
            geom.setMaterial(mat2);
            return geom;
        }
        return null;
    }

    public Spatial create(VisualComponent visualComponent, VertexAssociatedComponent vac, VertexIndexAssociatedComponent viac, EntityId id) {
        if(visualComponent.getName().equals(VisualComponent.MESH)) {

            ColorRGBA[] colors = {ColorRGBA.Blue, ColorRGBA.Red, ColorRGBA.Green, ColorRGBA.Orange, ColorRGBA.Pink, ColorRGBA.Yellow, ColorRGBA.White, ColorRGBA.Cyan};


            Mesh mesh = new Mesh();
            mesh.setBuffer(VertexBuffer.Type.Position, 3, BufferUtils.createFloatBuffer(vac.getVertices()));
            // mesh.setBuffer(VertexBuffer.Type.TexCoord, 2, BufferUtils.createFloatBuffer(texCoord));
            //int[] inds = viac.getIndexList();
            int[] inds = {0,1,2};
            mesh.setBuffer(VertexBuffer.Type.Index, 3, BufferUtils.createIntBuffer(inds));
            mesh.updateBound();

            Geometry geo = new Geometry("triangle", mesh); // using our custom mesh object
            Material mat = new Material(assetManager,
                    "Common/MatDefs/Misc/Unshaded.j3md");
            // mat.getAdditionalRenderState().setWireframe(true);
            //mat.getAdditionalRenderState().setLineWidth(4);
            //mat.getAdditionalRenderState().setFaceCullMode(RenderState.FaceCullMode.Off);
            //if(vac.getVertices()[0].x == 0.5f && vac.getVertices()[0].y == 0 && vac.getVertices()[0].z == 0)
            //    mat.setColor("Color", ColorRGBA.Green);
           // else
                mat.setColor("Color", colors[(int)id.getId() % 8]);

            geo.setMaterial(mat);

            Node meshNode = new Node();
            meshNode.attachChild(geo);
            return meshNode;
        }
        return null;
    }
}