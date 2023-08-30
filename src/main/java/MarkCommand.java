/**
 * Represents a Command class that is responsible for marking / unmarking a task as done.
 *
 * @author Anthony Tamzil
 * @version CS2103T Individual Project AY2023/24 Semester 1
 */
public class MarkCommand extends Command{
    /** Task number to be marked as done. */
    private int taskNumber;
    /** Boolean containing whether user wants to mark or unmark a task as done. */
    private boolean isDone;


    /**
     * Constructor for the MarkCommand class for users to mark a task as done.
     */
    public MarkCommand(boolean isDone, int taskNumber) {
        this.taskNumber = taskNumber;
        this.isDone = isDone;
    }

    /**
     * Marks the specified task as done.
     *
     * @param tasks TaskList class storing an ArrayList of Task objects.
     * @param ui Ui class to handle user interactions.
     * @param storage Storage class to read and store tasks by the user.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (isDone) {
            tasks.markTaskAsDone(taskNumber );
            ui.showMarkedTask(tasks.getTask(taskNumber - 1));
        } else {
            tasks.markTaskAsNotDone(taskNumber);
            ui.showUnmarkedTask(tasks.getTask(taskNumber - 1));
        }
        storage.saveFile(tasks.toStorageString());
    }
}
