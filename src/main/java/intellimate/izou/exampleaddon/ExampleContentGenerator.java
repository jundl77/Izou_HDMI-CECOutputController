package intellimate.izou.exampleaddon;

import intellimate.izou.contentgenerator.ContentGenerator;
import intellimate.izou.events.Event;
import intellimate.izou.resource.Resource;
import intellimate.izou.system.Context;
import intellimate.izou.system.Identification;
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

    IdentificationManager identificationManager = IdentificationManager.getInstance();

    public ExampleContentGenerator(Context context) {
        super(ID, context);
    }

    @Override
    public List<Resource> announceResources() {
        Optional<Identification> identification = identificationManager.getIdentification(this);
        Resource<String> resource = new Resource<>(ResourceID);
        identification.ifPresent(resource::setProvider);
        return Arrays.asList(resource);
    }

    @Override
    public List<String> announceEvents() {
        return Arrays.asList(ExampleActivator.EXAMPLE_EVENT_TYPE);
    }

    @Override
    public List<Resource> provideResource(List<Resource> list, Optional<Event> optional) {
        if(!list.stream().anyMatch(resource -> resource.getResourceID().equals(ResourceID))) return null;
        System.out.println("ContentGenerator generates Resource for the Event");
        Optional<Identification> identification = identificationManager.getIdentification(this);
        Resource<String> resource = new Resource<>(ResourceID);
        identification.ifPresent(resource::setProvider);
        resource.setResource("Hello World!");
        return Arrays.asList(resource);
    }
}
