package bot;

import bot.command.*;
import bot.exception.DateTimeParseBotException;
import bot.exception.FileErrorBotException;
import bot.exception.IllegalExpressionBotException;
import bot.exception.IncompleteBotException;
import bot.task.TaskList;

import java.io.IOException;
import java.util.Scanner;

public class Ui {

    private static final String INTRO = "____________________________________________________________\n" +
            "Hello! I'm Bot\n" +
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
        String str;
        ControlFlow control = new ControlFlow(taskLst);
        Command command = null;
        do {
            str = sc.nextLine();
            try {
                command = control.execute(str);
                command.execute();
            } catch (IncompleteBotException | IllegalExpressionBotException |
                     FileErrorBotException | DateTimeParseBotException | IOException e) {
                System.out.println(e);
            }
        } while (!(command instanceof TerminateCommand));
    }


}
