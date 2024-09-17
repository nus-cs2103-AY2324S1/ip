package taskcreator;

import java.util.ArrayList;

import contentsplitter.EventContentSplitter;
import duke.IrisException;
import duplicatedetectors.EventDuplicateDetector;
import tasks.Event;
import tasks.Task;

/**
 * This class handles the creation of Event objects.
 */
public class CreateEventTask extends CreateTask {
    /**
     * This method creates an Event object.
     * @param content the String representing the content of the user input
     * @param taskList the list of tasks
     * @return A new Event object using the inputs.
     */
    @Override
    public Event create(String content, ArrayList<Task> taskList) {
        EventContentSplitter splitter = new EventContentSplitter(content);
        EventDuplicateDetector eventDuplicateDetector = new EventDuplicateDetector();
        if (eventDuplicateDetector.checkDuplicates(splitter.getDescription(),
                splitter.getDateOfFrom(), splitter.getDateOfTo(), taskList)) {
            throw new IrisException("Error: This event task already exists.");
        }
        return new Event(splitter.getDescription(), splitter.getDateOfFrom(), splitter.getDateOfTo());
    }
}
