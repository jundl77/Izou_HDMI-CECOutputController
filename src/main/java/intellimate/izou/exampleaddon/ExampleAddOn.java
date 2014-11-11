package intellimate.izou.exampleaddon;

import intellimate.izou.activator.Activator;
import intellimate.izou.addon.AddOn;
import intellimate.izou.contentgenerator.ContentGenerator;
import intellimate.izou.events.EventController;
import intellimate.izou.output.OutputExtension;
import intellimate.izou.output.OutputPlugin;
import ro.fortsoft.pf4j.Extension;

import java.nio.file.Path;

/**
 * Example addOn for Izou, PLEASE DELETE THIS PACKAGE ON FINAL DISTRIBUTION. It is only meant as a model for
 * how addOns are structured. It is not meant to be included in the final addOn. 
 */
@Extension
public class ExampleAddOn extends AddOn {
    /*
    ID consists of package_name.class_name
     */
    private static final String ID = ExampleAddOn.class.getCanonicalName();

    public ExampleAddOn() {
        super(ID);
    }

    @Override
    public void prepare() {

    }

    @Override
    public Activator[] registerActivator() {
        Activator[] activators = new Activator[1];
        activators[0] = new ExampleActivator();
        return activators;
    }

    @Override
    public ContentGenerator[] registerContentGenerator() {
        ContentGenerator[] contentGenerators = new ContentGenerator[1];
        contentGenerators[0] = new ExampleContentGenerator();
        contentGenerators[0].registerEvent(ExampleActivator.EXAMPLE_EVENT_ID);
        return contentGenerators;
    }

    @Override
    public EventController[] registerEventController() {
        return null;
    }

    @Override
    public OutputPlugin[] registerOutputPlugin() {
        OutputPlugin[] outputPlugins = new OutputPlugin[1];
        outputPlugins[0] = new ExampleOutputPlugin();
        return outputPlugins;
    }

    @Override
    public OutputExtension[] registerOutputExtension() {
        OutputExtension[] outputExtensions = new OutputExtension[1];
        outputExtensions[0] = new ExampleOutputExtension();
        //outputExtensions[0].addContentDataToWishList("example");
        return outputExtensions;
    }

    /**
     * use this method to register a property file (if you have one) so that Izou reloads it when you update it manually
     *
     * @return the path to the properties file
     */
    @Override
    public Path registerPropertiesFile() {
        return null;
    }
}
