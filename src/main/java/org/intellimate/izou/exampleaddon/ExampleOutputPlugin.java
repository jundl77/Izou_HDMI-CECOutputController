package org.intellimate.izou.exampleaddon;

import org.intellimate.izou.events.EventModel;
import org.intellimate.izou.sdk.Context;
import org.intellimate.izou.sdk.output.OutputPlugin;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by julianbrendl on 10/24/14.
 */
public class ExampleOutputPlugin extends OutputPlugin<ExampleOutputData> {
    /*
    ID consists of class_name
     */
    public static final String ID = ExampleOutputPlugin.class.getCanonicalName();

    public ExampleOutputPlugin(Context context) {
        super(context, ID);
    }

    /**
     * method that uses tDoneList to generate a final output that will then be rendered.
     *
     * @param data the data generated
     */
    @Override
    public void renderFinalOutput(List<ExampleOutputData> data, EventModel eventModel) {
        String totalOutput = data.stream()
                .map(exampleOutputData -> exampleOutputData.data)
                .collect(Collectors.joining(", "));
        System.out.println(totalOutput);
    }
}
