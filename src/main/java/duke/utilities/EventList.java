package duke.utilities;

import java.util.ArrayList;

import duke.tasks.Events;
import duke.tasks.Task;

/**
 * A list of Events the user inputs.
 */
public class EventList {
    private ArrayList<Events> eventList;

    /**
     * Event List Constructor.
     *
     * @param taskList Takes in a taskList and returns a list of events.
     */
    public EventList(TaskList taskList) {
        ArrayList<Events> filteredList = new ArrayList<>();
        for (Task task : taskList.getTaskList()) {
            if (task instanceof Events) {
                Events event = (Events) task;
                filteredList.add(event);
            }
        }
        this.eventList = filteredList;
    }

    /**
     * Separate constructor for event list.
     *
     * @param eventList A list of all the events.
     */
    public EventList(ArrayList<Events> eventList) {
        this.eventList = eventList;
    }

    /**
     * A getter command to obtain the arraylist of events.
     *
     * @return The arraylist of events.
     */
    public ArrayList<Events> getEventList() {
        return this.eventList;
    }

    /**
     * Sort the event list and return the sorted event list.
     *
     * @return The sorted event list.
     */
    public EventList sort() {
        eventList.sort((x, y) -> (x.getStartDate().compareTo(y.getStartDate())));
        return new EventList(eventList);
    }
}
