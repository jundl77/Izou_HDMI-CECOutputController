package org.intellimate.izou.exampleaddon;

import org.intellimate.izou.resource.ResourceModel;
import org.intellimate.izou.sdk.Context;
import org.intellimate.izou.sdk.activator.Activator;
import org.intellimate.izou.sdk.events.CommonEvents;
import org.intellimate.izou.sdk.events.Event;
import org.intellimate.izou.sdk.util.ResourceUser;

import java.util.Arrays;

/**
 * Created by julianbrendl on 10/24/14.
 */
public class ExampleActivator extends Activator implements ResourceUser {

    public ExampleActivator(Context context) {
        super(context, ExampleActivator.class.getCanonicalName());
    }

    @Override
    public void activatorStarts() {
        System.out.println("Activator starts");
        retrieveResource();
        fireEvent();
        stop();
    }

    /**
     * this method will just receive resources, without firing events
     */
    private void retrieveResource() {
        generateResource(ExampleContentGeneratorResource.ResourceID)
            .ifPresent(future ->
                future.thenAccept(list -> list.stream()
                    .map(ResourceModel::getResource)
                    .filter(object -> object instanceof String)
                    .map(object -> (String) object)
                    .forEach(System.out::println)
                )
            );
    }

    /**
     * fires the event
     */
    private void fireEvent() {
        boolean fire = fire(CommonEvents.Type.RESPONSE_TYPE, Arrays.asList(ExampleContentGenerator.EXAMPLE_EVENT_TYPE));
        if (!fire) error("unable to fire event: " + CommonEvents.Type.RESPONSE_TYPE + ", " + ExampleContentGenerator.EXAMPLE_EVENT_TYPE);
    }
}
