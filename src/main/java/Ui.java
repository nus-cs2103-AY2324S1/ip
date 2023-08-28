import Commands.*;
import Task.TaskList;

import java.util.Scanner;

public class Ui {

    private static final String INTRO = "____________________________________________________________\n" +
            "Hello! I'm [YOUR CHATBOT NAME]\n" +
            "What can I do for you?\n" +
            "____________________________________________________________";

    private final Scanner sc;
    private final TaskList taskLst;

    public Ui(Scanner sc, TaskList taskLst) {
        this.sc = sc;
        this.taskLst = taskLst;
    }

    public void start() {
        System.out.println(Ui.INTRO);
        this.begin();
    }

    private void begin() {
        String str = sc.nextLine();
        ControlFlow control = new ControlFlow(taskLst);
        Command command;
        do {
            command = control.execute(str);
            command.execute();
            str = sc.nextLine();
        } while (!(command instanceof TerminateCommand));
    }


}
