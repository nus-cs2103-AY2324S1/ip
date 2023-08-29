/**
 * Represents the Todo command
 */
public class TodoCommand extends Command {

    /**
     * Stores the description of the task
     */
    private String desc;


    /**
     * Constructor of the todo command
     *
     * @param desc - the desc of the command
     */
    public TodoCommand(String desc) {
        this.desc = desc;
    }

    /**
     * Method to encapsulate the execution logic of the command
     *
     * @param taskList - the task list instance  of the current duke
     * @param ui       - the ui instance of DUKE
     * @param storage  - the storage instance to allow the command to write to the storage
     * @throws DukeBadInputException - if the input cannot be used
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeBadInputException {
        if (taskList.length() >= 100) {
            throw new DukeBadInputException("quack cannot remember any more tasks!!");
        }
        Task newTask = new TodoTask(desc);
        taskList.add(newTask);
        if (!storage.writeToFile(newTask.getStored())) {
            ui.unexpectedError("unable to write to storage");
            return;
        }
        ui.println("Quack! I have added this task:");
        ui.println(newTask.toString());
        ui.println("Quack! Quack is currently remembering " + taskList.length() + " tasks.");

    }

    /**
     * Checks if the command is the exit command
     *
     * @return true if it is the exit command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
