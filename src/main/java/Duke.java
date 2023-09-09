import java.util.List;
import java.util.Scanner;

public class Duke {

    public static TaskList taskList = new TaskList();
    static List<Task> allTasks = taskList.getTasks();

    public static void main(String[] args) throws DukeException {
        Storage.createDataLocation();
        Storage.loadTasksFromFile(taskList);
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser();
        Ui ui = new Ui();
        if (allTasks.size() == 0) {
            parser.run = 1;
        }
        String task = "";
        ui.greet();
        System.out.flush();
        parser.parse(sc);
        ui.sayBye();
    }
}



