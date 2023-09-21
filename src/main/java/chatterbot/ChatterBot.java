package chatterbot;

import chatterbot.data.Task;
import chatterbot.data.TaskList;
import chatterbot.parser.Parser;
import chatterbot.storage.Storage;
import chatterbot.ui.Ui;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the chatbot that is able to maintain a list of tasks.
 */
public class ChatterBot {

    protected static ArrayList<Task> list = new ArrayList<>();
    protected static String file = "data/ChatterBot.txt";
    protected Ui ui;
    protected Storage storage;
    protected TaskList taskList;

    public ChatterBot(TaskList taskList) {
        this.taskList = taskList;
        this.ui = new Ui(taskList.list);
        this.storage = new Storage(file, taskList.list);
    }

    /**
     * This is the main method which executes the ChatterBot program.
     * @param args Unused.
     */
    public static void main(String[] args) {

        Ui ui = new Ui(list);
        Storage storage = new Storage(file, list);
        TaskList taskList = new TaskList(list);

        File f = new File(file);
        File folder = f.getParentFile();
        if (!folder.exists()) {
            System.out.println("Error! No data folder found");
        }

        ui.showWelcomeMessage();
        taskList.initiateTaskList(storage);

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String userMessage = scanner.nextLine();
            Parser.evaluateCommand(userMessage, ui, list, storage, file, taskList);
        }
    }

    public String getResponse(String input) {
        return Parser.evaluateCommand(input, ui, list, storage, file, taskList);
    }
}