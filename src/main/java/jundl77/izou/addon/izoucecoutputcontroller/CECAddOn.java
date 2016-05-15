package jundl77.izou.addon.izoucecoutputcontroller;

import org.intellimate.izou.activator.ActivatorModel;
import org.intellimate.izou.events.EventsControllerModel;
import org.intellimate.izou.output.OutputControllerModel;
import org.intellimate.izou.output.OutputExtensionModel;
import org.intellimate.izou.output.OutputPluginModel;
import org.intellimate.izou.sdk.addon.AddOn;
import org.intellimate.izou.sdk.contentgenerator.ContentGenerator;

/**
 * <p>
 *     The HDMI-CEC AddOn allows you to control your CEC enabled TV through Izou.
 * </p>
 *
 * @author Julian Brendl
 */
public class CECAddOn extends AddOn {
    /**
     * The ID of the CECAddOn.
     */
    private static final String ID = CECAddOn.class.getCanonicalName();

    /**
     * Creates a new CECAddOn.
     */
    public CECAddOn() {
        super(ID);
    }

    /**
     * This method gets called before registering.
     */
    @Override
    public void prepare() {

    }
    @Override
    public ActivatorModel[] registerActivator() {
        return null;
    }

    @Override
    public ContentGenerator[] registerContentGenerator() {
        return null;
    }

    @Override
    public EventsControllerModel[] registerEventController() {
        return null;
    }

    @Override
    public OutputPluginModel[] registerOutputPlugin() {
        return null;
    }

    @Override
    public OutputExtensionModel[] registerOutputExtension() {
        return null;
    }

    @Override
    public OutputControllerModel[] registerOutputController() {
        CECOutputController[] outputControllers = new CECOutputController[1];
        outputControllers[0] = new CECOutputController(getContext());
        return outputControllers;
    }
}
