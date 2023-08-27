import java.util.ArrayList;

public abstract class Command {
    public enum TaskType {
        TODO, DEADLINE, EVENT
    }
    public abstract void doCommand(ArrayList<Task> tasks, Ui ui, Storage storage);

    public static int extractTaskNum(String userInput, String command) {
        String taskNumberStr = userInput.substring(command.length()).trim();
        return Integer.parseInt(taskNumberStr);
    }
    public static String extractTaskDesc(String userInput, String command) {
        return userInput.substring(command.length()).trim();
    }
}
