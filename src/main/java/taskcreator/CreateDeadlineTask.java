package taskcreator;

import java.util.ArrayList;

import contentsplitter.DeadlineContentSplitter;
import duke.IrisException;
import duplicatedetectors.DeadlineDuplicateDetector;
import tasks.Deadline;
import tasks.Task;

/**
 * This class handles the creation of Deadline objects.
 */
public class CreateDeadlineTask extends CreateTask {
    /**
     * The method creates a Deadline object.
     * @param content the String
     * @param taskList the list of tasks
     * @return a new Deadline object.
     */
    @Override
    public Deadline create(String content, ArrayList<Task> taskList) {
        DeadlineContentSplitter splitter = new DeadlineContentSplitter(content);
        DeadlineDuplicateDetector deadlineDuplicateDetector = new DeadlineDuplicateDetector();
        if (deadlineDuplicateDetector.checkDuplicates(splitter.getDescription(),
                splitter.getDate(), taskList)) {
            throw new IrisException("Error: This deadline task already exists.");
        }
        return new Deadline(splitter.getDescription(), splitter.getDate());
    }
}
