import exception.IllegalExpressionBotException;
import exception.IncompleteBotException;
import task.TaskList;

import java.util.Scanner;

public class Bot {

    private final Ui ui;

    public Bot() {
        TaskList taskLst = new TaskList();
        this.ui = new Ui(new Scanner(System.in), taskLst);
    }

    public static void main(String[] args) throws IllegalExpressionBotException, IncompleteBotException {
        Bot bot = new Bot();
        bot.start();
    }

    public void start() throws IllegalExpressionBotException, IncompleteBotException {
        this.ui.start();
    }
}
