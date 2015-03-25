package org.intellimate.izou.exampleaddon;

import org.intellimate.izou.events.EventModel;
import org.intellimate.izou.resource.ResourceModel;
import org.intellimate.izou.sdk.Context;
import org.intellimate.izou.sdk.contentgenerator.ContentGenerator;
import org.intellimate.izou.sdk.contentgenerator.EventListener;
import org.intellimate.izou.sdk.resource.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by LeanderK on 02/12/14.
 */
public class ExampleContentGeneratorResource extends ContentGenerator {
    public static String ID = ExampleContentGeneratorResource.class.getCanonicalName();
    public static String ResourceID = ExampleContentGenerator.class.getCanonicalName() +".Resource1";

    public ExampleContentGeneratorResource(Context context) {
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
        return new ArrayList<>();
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
    public List<? extends Resource<?>> triggered(List<? extends ResourceModel> list, Optional<EventModel> optional) {
        //normally you would have to check which resource to generate, but we only provide more than 1
        System.out.println("ContentGenerator generates Resource for the Request");
        return optionalToList(createResource(ResourceID, "Awesome Resource!"));
    }
}
