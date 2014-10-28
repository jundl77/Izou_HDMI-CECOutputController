package intellimate.izou.exampleaddon;

import intellimate.izou.activator.Activator;
import intellimate.izou.events.EventManager;

/**
 * Created by julianbrendl on 10/24/14.
 */
public class ExampleActivator extends Activator {

    public final static String EXAMPLE_EVENT_ID = ExampleActivator.class.getCanonicalName()+".exampleEvent";

    public ExampleActivator() {

    }

    @Override
    public void activatorStarts() throws InterruptedException {
        registerEvent(EXAMPLE_EVENT_ID);
        try {
            fireEvent(EXAMPLE_EVENT_ID);
        } catch (EventManager.MultipleEventsException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean terminated(Exception e) {
        return false;
    }
}
