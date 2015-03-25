package org.intellimate.izou.exampleaddon;


import org.intellimate.izou.events.EventModel;
import org.intellimate.izou.resource.ResourceModel;
import org.intellimate.izou.sdk.Context;
import org.intellimate.izou.sdk.contentgenerator.ContentGenerator;
import org.intellimate.izou.sdk.contentgenerator.EventListener;
import org.intellimate.izou.sdk.resource.Resource;

import java.util.*;

/**
 * Created by julianbrendl on 10/24/14.
 */
public class ExampleContentGenerator extends ContentGenerator {
    /*
    ID consists of class_name
     */
    private static final String ID = ExampleContentGenerator.class.getCanonicalName();
    public static final String ResourceID = ExampleContentGenerator.class.getCanonicalName()+"resource";
    public final static String EXAMPLE_EVENT_TYPE = ExampleContentGenerator.class.getCanonicalName()+".exampleEvent";
    public final static String EXAMPLE_EVENT_ID = "Example_Event_ID";

    public ExampleContentGenerator(Context context) {
        super(ID, context);
    }

    /**
     * this method returns a List of EventListener, which indicate for which Events the ContentGenerator should be
     * triggered.
     *
     * @return a List of EventListeners
     */
    @Override
    public List<? extends EventListener> getTriggeredEvents() {
        Optional<EventListener> listenerOpt = EventListener.createEventListener(
                EXAMPLE_EVENT_TYPE,
                "Example Event ID, serves no real purpose",
                EXAMPLE_EVENT_ID, this);
        return optionalToList(listenerOpt);
    }

    /**
     * This method is called to register what resources the object provides.
     * just pass a List of Resources without Data in it.
     *
     * @return a List containing the resources the object provides
     */
    @Override
    public List<? extends Resource> getTriggeredResources() {
        return optionalToList(createResource(ResourceID));
    }

    /**
     * this method is called when an object wants to get a Resource.
     * <p>
     * Don't use the Resources provided as arguments, they are just the requests.
     * There is a timeout after 1 second.
     * </p>
     *
     * @param list     a list of resources without data
     * @param optional if an event caused the action, it gets passed. It can also be null.
     * @return a list of resources with data
     */
    @Override
    public List<? extends Resource> triggered(List<? extends ResourceModel> list, Optional<EventModel> optional) {
        System.out.println("ContentGenerator generates Resource for the Event");
        return optionalToList(createResource(ResourceID, "Hello World!"));
    }
}
