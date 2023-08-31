import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
public class Duke {
    private Parser parser;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    public static void main(String[] args) throws IOException {
        new Duke("../data/duke.txt").run();
    }

    public Duke(String filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() throws IOException{
        ui.welcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            String str = bf.readLine();
            String command = parser.parseCommand(str);
            //regex detect all space
            if (command.equals("list")) {
                ui.printTaskList(tasks);
            } else if (command.equals("mark")) {
                int index = parser.parseToIndex();
                Task curr = tasks.getTask(index);
                curr.markAsDone();
                storage.store(tasks);
                ui.printMark(curr, index);
            } else if (command.equals("unmark")) {
                int index = parser.parseToIndex();
                Task curr = tasks.getTask(index);
                curr.markAsNotDone();
                storage.store(tasks);
                ui.printUnmark(curr, index);
            } else if (command.equals("bye")) {
                ui.goodbyeMessage();
                break;
            } else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                Task curr = parser.parseToTask();
                if (curr == null) {
                    continue;
                }
                tasks.addTask(curr);
                storage.store(tasks);
                ui.printAddTask(curr, tasks.size());
            } else if (command.equals("delete")){
                int index = parser.parseToIndex();
                Task curr = tasks.getTask(index);
                tasks.deleteTask(index);
                storage.store(tasks);
                ui.printDelete(curr, tasks.size());
            } else {
                //nothing found
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                isExit = true;
            }
        }

    }
}

//todo: debug error when loading event or deadline