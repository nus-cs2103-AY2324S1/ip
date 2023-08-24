import java.util.ArrayList;
import java.util.List;

public class InputHandler {

    final private List<Task> tasks = new ArrayList<>();
    public InputHandler() {}
    public void HandleInput(String input) {
        switch(input) {
            case "bye" :
                Message.OnExit().Print();
                Duke.Exit();
                break;
            case "list" :
                Message.ChainList(Message.ConvertTasks(tasks), "\n").Print();
                break;
            default :
                Task task = new Task(input);
                tasks.add(task);
                Message.OnTaskAdd((task)).Print();
                break;
        }
    }
}
