package org.intellimate.izou.exampleaddon;

import org.intellimate.izou.events.EventModel;
import org.intellimate.izou.resource.ResourceModel;
import org.intellimate.izou.sdk.Context;
import org.intellimate.izou.sdk.output.OutputExtension;

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

    /**
     * generates the data for the given Event
     *
     * @param event the event to generate for
     * @return the result
     */
    @Override
    public ExampleOutputData generate(EventModel event) {
        System.out.println("OutputExtension generates Output Data");
        return event.getListResourceContainer()
                .provideResource(ExampleContentGenerator.ResourceID).stream()
                .map(ResourceModel::getResource)
                .map(object -> object instanceof String ? (String) object : "")
                .collect(Collectors.collectingAndThen(Collectors.joining(), ExampleOutputData::new));
    }
}
