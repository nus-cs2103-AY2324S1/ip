/**
 * An interface capable of listening to certain events.
 * @param <T> The type of the event.
 */
public interface EventListener<T> {
    /**
     * A method to process an event that has been fired.
     * @param event Fired event.
     */
    public void eventFired(T event);
}
