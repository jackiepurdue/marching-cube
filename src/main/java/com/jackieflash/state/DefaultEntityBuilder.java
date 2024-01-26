package com.jackieflash.state;

import com.jackieflash.component.MapableComponent;
import com.simsilica.es.EntityComponent;
import com.simsilica.es.EntityData;
import com.simsilica.es.EntityId;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiPredicate;

public abstract class DefaultEntityBuilder {

    protected MapableComponent uniqueComponent;
    protected MapableComponent mappableComponent;

    protected final EntityComponentFactory componentFactory;
    protected final List<EntityComponent> components;
    protected final HashMap<String, HashMap<String, EntityId>> mappables;

    //TODO: set up logger and unit tests

    protected HashMap<String, EntityComponent> mappableComponentMap;

    public DefaultEntityBuilder(EntityComponentFactory componentFactory, HashMap<String, HashMap<String, EntityId>> mappables) {
        this.componentFactory = componentFactory;
        this.components = Collections.emptyList();
        this.mappables = mappables;
        this.mappableComponent = null;
    }


    public DefaultEntityBuilder(EntityComponentFactory componentFactory, List<EntityComponent> components, HashMap<String, HashMap<String, EntityId>> mappables, MapableComponent mapableComponent) {
        this.componentFactory = componentFactory;
        this.components = components;
        this.mappables = mappables;
        this.mappableComponent = mapableComponent;
    }

    protected abstract DefaultEntityBuilder withMapableComponent(String type, String identifier);
    protected abstract DefaultEntityBuilder addComponent(EntityComponent component);
    protected abstract DefaultEntityBuilder addComponent();


    public EntityId getMappedEntityIdByTypeAndIdentifier(String type, String identifier){
        return this.mappables.get(type).get(identifier);
    }

    //by default don't overwrite
    public DefaultEntityBuilder build(EntityId entityId, EntityData entityData) {
        return build(entityId, entityData, (newId, oldId) -> (Boolean.FALSE));
    }


    //TODO: non unique mappables will not work ehre no condition for them yet
    public DefaultEntityBuilder build(EntityId newEntityId, EntityData entityData, BiPredicate<EntityId, EntityId> overwritePredicate) {
        // Check for uniqueness constraint

        if (mappableComponent != null && mappableComponent.getIsIndividualized()) {
            String constraintType = mappableComponent.getType();
            String identifier = mappableComponent.getIdentifier();

            // Create the entity with the components
            makeEntity(entityData,newEntityId);

            // Check if the identifier is already used
            if (mappables.containsKey(constraintType) && mappables.get(constraintType).containsKey(identifier)) {
                EntityId oldEntityId = mappables.get(constraintType).get(identifier);
                boolean shouldOverwrite = overwritePredicate.test(newEntityId, oldEntityId);
                if (!shouldOverwrite) {
                    entityData.removeEntity(newEntityId);
                } else {
                    entityData.removeEntity(oldEntityId);
                    mappables.get(constraintType).remove(identifier);
                }
            }
            // Add the entity to the unique constraints map
            mappables.computeIfAbsent(constraintType, k -> new HashMap<>()).put(identifier, newEntityId);
        }

        makeEntity(entityData,newEntityId);
        return addComponent();
    }

    private void makeEntity(EntityData entityData, EntityId newEntityId){
        for(EntityComponent ec: components){
            entityData.setComponent(newEntityId, ec);
        }
    }
}