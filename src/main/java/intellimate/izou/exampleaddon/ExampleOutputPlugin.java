package intellimate.izou.exampleaddon;

import intellimate.izou.output.OutputPlugin;
import intellimate.izou.system.Context;

import java.util.stream.Collectors;

/**
 * Created by julianbrendl on 10/24/14.
 */
public class ExampleOutputPlugin extends OutputPlugin<ExampleOutputData> {
    /*
    ID consists of class_name
     */
    public static final String ID = ExampleOutputExtension.class.getCanonicalName();

    public ExampleOutputPlugin(Context context) {
        super(ID, context);
    }

    @Override
    public void renderFinalOutput() {
        String totalOutput = getTDoneList().stream()
                .map(exampleOutputData -> exampleOutputData.data)
                .collect(Collectors.joining(", "));
        System.out.println(totalOutput);
    }
}
