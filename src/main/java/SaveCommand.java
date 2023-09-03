public class SaveCommand extends Command {
    protected String response;
    public SaveCommand(String response) {
        super();
        this.response = response;
    }

    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) {
        storage.save(taskList);
        System.out.println(taskList);
    }
}
