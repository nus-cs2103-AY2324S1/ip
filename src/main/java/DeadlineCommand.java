public class DeadlineCommand extends Command{
    String description;
    String date;
    public DeadlineCommand(String description, String date){
        this.description = description;
        this.date = date;
    }
    @Override
    public boolean isExit() {
        return false;
    }
    @Override
    public void execute(TaskList tasksList, Ui ui, Storage storage) {
        Task deadline = new Deadline(description,date);
        tasksList.addTask(deadline);
        ui.showAddedTask(tasksList);
    }
}
