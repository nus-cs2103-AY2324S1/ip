package duke;

/**
 * Adds the task into the DukeList.
 */
public class DeadlineAdder extends Command {

    //The name of the task.
    private String taskName;

    //The deadline of the task.
    private String deadLine;

    //The DukeList to store the Deadline in.
    private TaskList list;


    /**
     * Instantiates a DeadlineAdder class
     * @param taskName Name of the task
     * @param deadLine Deadline of the task
     * @param list The DukeList to store said task in
     */
    public DeadlineAdder(String taskName, String deadLine, TaskList list) {
        this.taskName = taskName;
        this.deadLine = deadLine;
        this.list = list;
    }


    /**
     * Adds the Deadline into the DukeList
     * @return The confirmation of the addition of the Deadline.
     */
    @Override
    public String execute() {
        Task deadline = new Deadline(taskName, deadLine);
        return list.store(deadline);
    }
}
