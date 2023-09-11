package duke;

public class UpdateTask extends Command{
    private int taskToUpdate;
    private String changeDetails;

    public UpdateTask(int taskToUpdate, String changeDetails) {
        this.taskToUpdate = taskToUpdate - 1;
        this.changeDetails = changeDetails;
    }
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.get(taskToUpdate);
            if (this.changeDetails.isEmpty()) {
                throw new InvalidInputException("Please provide proper details");
            }
            task.update(this.changeDetails);
            return ui.updatedMessage(tasks, this.taskToUpdate);
        } catch (InvalidInputException e){
            return e.getMessage();
        }
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
