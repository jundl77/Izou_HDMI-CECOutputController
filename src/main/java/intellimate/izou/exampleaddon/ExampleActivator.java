package intellimate.izou.exampleaddon;

import intellimate.izou.activator.Activator;
import intellimate.izou.events.Event;
import intellimate.izou.resource.Resource;
import intellimate.izou.system.Context;
import intellimate.izou.system.IdentificationManager;

/**
 * Created by julianbrendl on 10/24/14.
 */
public class ExampleActivator extends Activator {

    public final static String EXAMPLE_EVENT_TYPE = ExampleActivator.class.getCanonicalName()+".exampleEvent";
    private IdentificationManager identificationManager = IdentificationManager.getInstance();

    public ExampleActivator(Context context) {
        super(context);
    }

    @Override
    public void activatorStarts() {
        System.out.println("Activator starts");
        retrieveResource();
        fireEvent();
    }

    public void retrieveResource() {
        identificationManager.getIdentification(ExampleContentGeneratorResource.ID)
                .map(id -> new Resource(ExampleContentGeneratorResource.ResourceID, id))
                .flatMap(getContext().resources::generateResource)
                .ifPresent(future ->
                    future.thenAccept(list -> list.stream()
                            .map(Resource::getResource)
                            .filter(object -> object instanceof String)
                            .map(object -> (String) object)
                            .forEach(System.out::println)
                    )
                );
    }

    public void fireEvent() {
        try {
            identificationManager.getIdentification(this)
                    .flatMap(id -> Event.createEvent(Event.RESPONSE, id))
                    .orElseThrow(() -> new IllegalStateException("unable to create Event"))
                    .addDescriptor(EXAMPLE_EVENT_TYPE)
                    .fire(getCaller(), event -> getContext().logger.getLogger().error("unable to fire"));

        } catch (IllegalStateException e) {
            getContext().logger.getLogger().error(e);
        }
        //imperative style
        /*
        Optional<Identification> id = identificationManager.getIdentification(this);
        if(!id.isPresent()) return;

        Optional<Event> event = Event.createEvent(Event.RESPONSE, id.get());
        if(!event.isPresent()) return;
        event.of(event.addDescriptor(EXAMPLE_EVENT_TYPE));

        try {
            fireEvent(event.get());
        } catch (LocalEventManager.MultipleEventsException e) {
            context.logger.getLogger().error(e);
        }*/
    }

    @Override
    public boolean terminated(Exception e) {
        getContext().logger.getLogger().fatal(e);
        return false;
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
        return ExampleActivator.class.getCanonicalName();
    }
}
