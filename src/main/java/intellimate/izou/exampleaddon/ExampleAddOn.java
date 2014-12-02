package intellimate.izou.exampleaddon;

import intellimate.izou.activator.Activator;
import intellimate.izou.addon.AddOn;
import intellimate.izou.contentgenerator.ContentGenerator;
import intellimate.izou.events.EventsController;
import intellimate.izou.output.OutputExtension;
import intellimate.izou.output.OutputPlugin;
import ro.fortsoft.pf4j.Extension;

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
        activators[0] = new ExampleActivator(getContext());
        return activators;
    }

    @Override
    public ContentGenerator[] registerContentGenerator() {
        ContentGenerator[] contentGenerators = new ContentGenerator[2];
        contentGenerators[0] = new ExampleContentGenerator(getContext());
        contentGenerators[1] = new ExampleContentGeneratorResource(getContext());
        return contentGenerators;
    }

    @Override
    public EventsController[] registerEventController() {
        return null;
    }

    @Override
    public OutputPlugin[] registerOutputPlugin() {
        OutputPlugin[] outputPlugins = new OutputPlugin[1];
        outputPlugins[0] = new ExampleOutputPlugin(getContext());
        return outputPlugins;
    }

    @Override
    public OutputExtension[] registerOutputExtension() {
        OutputExtension[] outputExtensions = new OutputExtension[1];
        outputExtensions[0] = new ExampleOutputExtension(getContext());
        //outputExtensions[0].addContentDataToWishList("example");
        return outputExtensions;
    }

    /**
     * An ID must always be unique.
     * A Class like Activator or OutputPlugin can just provide their .class.getCanonicalName()
     * If you have to implement this interface multiple times, just concatenate unique Strings to
     * .class.getCanonicalName()
     *
     * @return A String containing an ID
     */
    @Override
    public String getID() {
        return ExampleAddOn.class.getCanonicalName();
    }
}
