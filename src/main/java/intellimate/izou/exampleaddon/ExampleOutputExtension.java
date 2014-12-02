package intellimate.izou.exampleaddon;

import intellimate.izou.events.Event;
import intellimate.izou.output.OutputExtension;
import intellimate.izou.resource.Resource;
import intellimate.izou.system.Context;

import java.util.stream.Collectors;

/**
 * Created by julianbrendl on 10/24/14.
 */
public class ExampleOutputExtension extends OutputExtension<ExampleOutputData> {
    /*
    ID consists of class_name
     */
    public static final String ID = ExampleOutputExtension.class.getCanonicalName();

    public ExampleOutputExtension(Context context) {
        super(ID, context);
        addResourceIdToWishList(ExampleContentGenerator.ResourceID);
        setPluginId(ExampleOutputPlugin.ID);
    }

    @Override
    public ExampleOutputData generate(Event event) {
        System.out.println("OutputExtension generates Output Data");
        String totalOutput = event.getListResourceContainer()
                .provideResource(ExampleContentGenerator.ResourceID)
                .stream()
                .map(Resource::getResource)
                .map(object -> object instanceof String ? (String) object : "")
                .collect(Collectors.joining());
        return new ExampleOutputData(totalOutput);
    }
}
