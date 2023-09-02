package bot;

import bot.exception.DateTimeParseBotException;
import bot.exception.FileErrorBotException;
import bot.storage.Storage;
import bot.task.TaskList;

import java.io.IOException;
import java.util.Scanner;

public class Bot {

    private final Ui ui;

    public Bot(TaskList taskList) {
        this.ui = new Ui(new Scanner(System.in), taskList);
    }

    public static void main(String[] args) throws FileErrorBotException,
            DateTimeParseBotException, IOException {
        Bot bot = new Bot(Storage.read());
        bot.start();
    }

    public void start() {
        this.ui.start();
    }
}
