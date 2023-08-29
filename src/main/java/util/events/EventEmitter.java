package util.events;

import java.util.ArrayList;
import java.util.List;

/**
 * A class capable of emitting events to event listeners.
 */
public class EventEmitter<T> {
    private final List<EventListener<? super T>> eventListeners = new ArrayList<>();

    /**
     * Fires a new event to all event listeners listening to this instance.
     *
     * @param event The event to fire.
     */
    protected void fireEvent(T event) {
        for (var l : this.eventListeners) {
            l.eventFired(event);
        }
    }

    /**
     * Adds an event listener to listen to events by this instance.
     *
     * @param listener The listener to add.
     */
    public void addEventListener(EventListener<? super T> listener) {
        this.eventListeners.add(listener);
    }

    /**
     * Removes an event listener to remove it from listening to events by this instance.
     *
     * @param listener The listener to remove.
     */
    public void removeEventListener(EventListener<? super T> listener) {
        this.eventListeners.remove(listener);
    }
}
