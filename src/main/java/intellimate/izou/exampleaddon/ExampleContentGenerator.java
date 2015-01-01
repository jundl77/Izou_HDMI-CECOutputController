package intellimate.izou.exampleaddon;

import intellimate.izou.contentgenerator.ContentGenerator;
import intellimate.izou.events.Event;
import intellimate.izou.resource.Resource;
import intellimate.izou.system.Context;
import intellimate.izou.system.IdentificationManager;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by julianbrendl on 10/24/14.
 */
public class ExampleContentGenerator extends ContentGenerator {
    /*
    ID consists of class_name
     */
    private static final String ID = ExampleContentGenerator.class.getCanonicalName();
    public static final String ResourceID = ExampleContentGenerator.class.getCanonicalName()+"resource";

    //TODO: use super instance variable in next version
    private IdentificationManager identificationManager = IdentificationManager.getInstance();

    public ExampleContentGenerator(Context context) {
        super(ID, context);
    }

    @Override
    public List<Resource> announceResources() {
        Resource<String> resource = new Resource<>(ResourceID);
        identificationManager.getIdentification(this)
                .ifPresent(resource::setProvider);
        return Arrays.asList(resource);
    }

    @Override
    public List<String> announceEvents() {
        return Arrays.asList(ExampleActivator.EXAMPLE_EVENT_TYPE);
    }

    @Override
    public List<Resource> provideResource(List<Resource> list, Optional<Event> optional) {
        if(!list.stream().anyMatch(resource -> resource.getResourceID().equals(ResourceID)))
            return null;

        System.out.println("ContentGenerator generates Resource for the Event");
        return identificationManager.getIdentification(this)
                .map(id -> new Resource<String>(ResourceID, id))
                .orElse(new Resource<String>(ResourceID))
                .setResource("Hello World!")
                .map(Arrays::asList);
    }
}
