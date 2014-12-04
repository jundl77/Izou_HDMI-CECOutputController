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
 * Created by LeanderK on 02/12/14.
 */
public class ExampleContentGeneratorResource extends ContentGenerator{
    public static String ID = ExampleContentGeneratorResource.class.getCanonicalName();
    public static String ResourceID = ExampleContentGenerator.class.getCanonicalName() +".Resource1";

    private IdentificationManager identificationManager = IdentificationManager.getInstance();

    public ExampleContentGeneratorResource(Context context) {
        super(ExampleContentGenerator.class.getCanonicalName(), context);
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
        return null;
    }

    @Override
    public List<Resource> provideResource(List<Resource> list, Optional<Event> optional) {
        if(!list.stream().anyMatch(resource -> resource.getResourceID().equals(ResourceID))) return null;
        System.out.println("ContentGenerator generates Resource for the Request");
        Optional<Identification> identification = identificationManager.getIdentification(this);
        Resource<String> resource = new Resource<>(ResourceID);
        identification.ifPresent(resource::setProvider);
        resource.setResource("Awesome Resource!");
        return Arrays.asList(resource);
    }
}
