package todoify.util.events;

/**
 * An interface capable of listening to certain utils.events.
 *
 * @param <T> The type of the event.
 */
@FunctionalInterface
public interface EventListener<T> {
    /**
     * A method to process an event that has been fired.
     *
     * @param event Fired event.
     */
    void eventFired(T event);
}
