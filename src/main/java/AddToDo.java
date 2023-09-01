public class AddToDo extends Command{

    public AddToDo(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        tasks.addTask(input, TaskType.TODO);

        ui.print(new String[] {
            "What? You ain't finishing it. Added: ",
            tasks.getTask(tasks.getLength()).toString(),
            "Now you have an overwhelming " + tasks.getLength() + " things to do."
        });

        storage.saveTasks(tasks);
    }
}
