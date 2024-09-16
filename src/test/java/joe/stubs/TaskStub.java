package joe.stubs;

import joe.tasks.Task;
/**
 * A stub implementation of Task for testing.
 */
public class TaskStub extends Task {
    public TaskStub() {
        super("");
    }

    @Override
    public String toString() {
        return "toString";
    }

    @Override
    public String getDescription() {
        return "Description";
    }

    @Override
    public void markAsDone() {
        //Do Nothing
    }

    @Override
    public void markAsNotDone() {
        //Do Nothing
    }

}
