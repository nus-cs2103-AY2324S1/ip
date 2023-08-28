import java.util.List;

public class CreateToDoCommand extends Command{

    private ToDo task;

    public CreateToDoCommand(String task) {
        this.task = new ToDo(task);
    }
    @Override
    public void execute(TaskList list) {
        list.add(task);
        Ui.ui.createTaskPrompt(task);
        Duke.run();
    }

}
