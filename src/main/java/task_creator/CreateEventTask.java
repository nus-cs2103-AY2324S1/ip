package task_creator;

import content_splitter.EventContentSplitter;
import duke.IrisException;
import duplicate_detectors.EventDuplicateDetector;
import tasks.Event;
import tasks.Task;

import java.util.ArrayList;

public class CreateEventTask extends CreateTask {
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
