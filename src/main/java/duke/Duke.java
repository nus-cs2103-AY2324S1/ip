package duke;

import java.util.List;
import java.util.Scanner;

public class Duke {

    public static TaskList taskList = new TaskList();;

    public static void main(String[] args) throws DukeException {
        Storage storage = new Storage();
        Storage.createDataLocation();
        storage.loadTasksFromFile(taskList);
        List<Task> allTasks = taskList.getTasks();
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



