package task_creator;

import content_splitter.DeadlineContentSplitter;
import duke.IrisException;
import duplicate_detectors.DeadlineDuplicateDetector;
import tasks.Deadline;
import tasks.Task;

import java.util.ArrayList;

public class CreateDeadlineTask extends CreateTask{
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
