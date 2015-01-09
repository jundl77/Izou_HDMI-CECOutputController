package intellimate.izou.exampleaddon;

import intellimate.izou.activator.Activator;
import intellimate.izou.events.Event;
import intellimate.izou.events.MultipleEventsException;
import intellimate.izou.resource.Resource;
import intellimate.izou.system.Context;
import intellimate.izou.system.Identification;
import intellimate.izou.system.IdentificationManager;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * Created by julianbrendl on 10/24/14.
 */
public class ExampleActivator extends Activator {

    public final static String EXAMPLE_EVENT_TYPE = ExampleActivator.class.getCanonicalName()+".exampleEvent";
    private Context context;
    private IdentificationManager identificationManager = IdentificationManager.getInstance();

    public ExampleActivator(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public void activatorStarts() {
        System.out.println("Activator starts");
        retrieveResource();
        fireEvent();
    }

    public void retrieveResource() {
        Resource resourceRequest = new Resource(ExampleContentGeneratorResource.ResourceID);

        //optional, if you want to specify which provider should generate the resource
        Optional<Identification> providerID = identificationManager.getIdentification(ExampleContentGeneratorResource.ID);
        providerID.ifPresent(resourceRequest::setProvider);

        Consumer<List<Resource>> consumer = resourceResultList -> resourceResultList.stream()
                .map(Resource::getResource)
                .filter(object -> object instanceof String)
                .map(object -> (String) object)
                .forEach(System.out::println);

        context.resources.generateResource(resourceRequest, consumer);
    }

    public void fireEvent() {
        try {

            identificationManager.getIdentification(this)
                    .flatMap(id -> Event.createEvent(Event.RESPONSE, id))
                    .orElseThrow(() -> new IllegalStateException("unable to create Event"))
                    .addDescriptor(EXAMPLE_EVENT_TYPE)
                    .tryFire(getCaller(), (event, counter) -> false);

        } catch (MultipleEventsException e) {
            context.logger.getLogger().error("Unable to fire Event", e);
        } catch (IllegalStateException e) {
            context.logger.getLogger().error(e);
        }
        //imperative style
        /*
        Optional<Identification> id = identificationManager.getIdentification(this);
        if(!id.isPresent()) return;

        Optional<Event> event = Event.createEvent(Event.RESPONSE, id.get());
        if(!event.isPresent()) return;

        try {
            fireEvent(event.get().addDescriptor(EXAMPLE_EVENT_TYPE));
        } catch (LocalEventManager.MultipleEventsException e) {
            context.logger.getLogger().error(e);
        }*/
    }

    @Override
    public boolean terminated(Exception e) {
        context.logger.getLogger().fatal(e);
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
