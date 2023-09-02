import exception.DateTimeParseBotException;
import exception.FileErrorBotException;
import exception.IllegalExpressionBotException;
import exception.IncompleteBotException;
import storage.Storage;
import task.TaskList;

import java.util.Scanner;

public class Bot {

    private final Ui ui;

    public Bot(TaskList taskList) {
        this.ui = new Ui(new Scanner(System.in), taskList);
    }

    public static void main(String[] args) throws FileErrorBotException, DateTimeParseBotException {
        Bot bot = new Bot(Storage.read());
        bot.start();
    }

    public void start() {
        this.ui.start();
    }
}
