/*
 * Copyright 2013 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.terasology.entitySystem.event.internal;

import org.terasology.entitySystem.Component;
import org.terasology.entitySystem.entity.EntityRef;
import org.terasology.entitySystem.event.Event;

/**
 */
public class PendingEvent {
    private EntityRef entity;
    private Event event;
    private Component component;

     public PendingEvent(EntityRef entity, Event event) {
        this.event = event;
        this.entity = entity;
    }

     public PendingEvent(EntityRef entity, Event event, Component component) {
        this.entity = entity;
        this.event = event;
        this.component = component;
    }

    public EntityRef getEntity() {
        return entity;
    }

    public Event getEvent() {
        return event;
    }

    public Component getComponent() {
        return component;
    }
}
