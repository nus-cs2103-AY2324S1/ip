import DukeException.DukeException;
import Task.TaskList;

import java.util.Scanner;

public class Duke {
    private  String name = "Iris";
    private TaskList taskList;

    private Storage storage;
    private Ui ui;
    private boolean awake = true;
    public Duke(String filePath) {
        Ui ui= new Ui();
        storage = new Storage(filePath);
        try {
            taskList = storage.LoadTaskList();
        } catch (DukeException e) {
            //ui.showLoadingError();
            taskList = new TaskList();
        }
    }
    public void run() {
        Scanner scanner = new Scanner(System.in);
        Message.OnGreeting(name).Print();
        while(awake){
            if(scanner.hasNext()) {
                ui.HandleLine(scanner.nextLine());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    protected void Exit()
    {
        awake = false;
    }
}
