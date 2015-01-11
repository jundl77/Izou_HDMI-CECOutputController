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
        return identificationManager.getIdentification(this)
                .map(id -> new Resource<String>(ResourceID, id))
                .orElse(new Resource<String>(ResourceID))
                .map(resource -> Arrays.asList((Resource) resource));

    }

    @Override
    public List<String> announceEvents() {
        return null;
    }

    @Override
    public List<Resource> provideResource(List<Resource> list, Optional<Event> optional) {
        //normally you would have to check which resource to generate, but we only provide more than 1
        System.out.println("ContentGenerator generates Resource for the Request");
        return identificationManager.getIdentification(this)
                .map(id -> new Resource<String>(ResourceID, id))
                .orElse(new Resource<String>(ResourceID))
                .setResource("Awesome Resource!")
                .map(Arrays::asList);
    }
}
