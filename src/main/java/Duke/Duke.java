package Duke;

import DukeException.*;
import Task.TaskList;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private  String name = "Iris";
    private TaskList taskList;
    private Storage storage;
    private Ui ui;
    private boolean awake = true;
    public Duke(String filePath) {
        try {
            storage = new Storage(filePath);
            taskList = storage.LoadTaskList();
            ui = new Ui(this, taskList);
        } catch (DukeException e) {
            taskList = new TaskList(storage);
            ui = new Ui(this, taskList);
            ui.ShowMessage(Message.OnLoadingError());
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
        new Duke("C:\\Users\\ortt2\\Documents\\ip\\src\\data\\tasks.txt").run();
    }

    protected void Exit()
    {
        awake = false;
    }
}
