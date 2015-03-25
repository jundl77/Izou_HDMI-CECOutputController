package org.intellimate.izou.exampleaddon;

import org.intellimate.izou.activator.ActivatorModel;
import org.intellimate.izou.events.EventsControllerModel;
import org.intellimate.izou.output.OutputExtensionModel;
import org.intellimate.izou.output.OutputPluginModel;
import org.intellimate.izou.sdk.addon.AddOn;
import org.intellimate.izou.sdk.contentgenerator.ContentGenerator;
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

    /**
     * The default constructor for AddOns
     */
    public ExampleAddOn() {
        super(ID);
    }

    /**
     * This method gets called before registering
     */
    @Override
    public void prepare() {

    }

    /**
     * Use this method to register (if needed) your Activators.
     *
     * @return Array containing Instances of Activators
     */
    @Override
    public ActivatorModel[] registerActivator() {
        ActivatorModel[] activators = new ActivatorModel[1];
        activators[0] = new ExampleActivator(getContext());
        return activators;
    }

    /**
     * Use this method to register (if needed) your ContentGenerators.
     *
     * @return Array containing Instances of ContentGenerators
     */
    @Override
    public ContentGenerator[] registerContentGenerator() {
        ContentGenerator[] contentGenerators = new ContentGenerator[2];
        contentGenerators[0] = new ExampleContentGenerator(getContext());
        contentGenerators[1] = new ExampleContentGeneratorResource(getContext());
        return contentGenerators;
    }

    /**
     * Use this method to register (if needed) your EventControllers.
     *
     * @return Array containing Instances of EventControllers
     */
    @Override
    public EventsControllerModel[] registerEventController() {
        return null;
    }

    /**
     * Use this method to register (if needed) your OutputPlugins.
     *
     * @return Array containing Instances of OutputPlugins
     */
    @Override
    public OutputPluginModel[] registerOutputPlugin() {
        OutputPluginModel[] outputPlugins = new OutputPluginModel[1];
        outputPlugins[0] = new ExampleOutputPlugin(getContext());
        return outputPlugins;
    }

    /**
     * Use this method to register (if needed) your Output.
     *
     * @return Array containing Instances of OutputExtensions
     */
    @Override
    public OutputExtensionModel[] registerOutputExtension() {
        OutputExtensionModel[] outputExtensions = new OutputExtensionModel[1];
        outputExtensions[0] = new ExampleOutputExtension(getContext());
        return outputExtensions;
    }
}
