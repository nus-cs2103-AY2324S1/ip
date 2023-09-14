package bob;

import java.util.Scanner;

/**
 * Represents a bot that can record three types of tasks: todo, deadline and event, as well as
 * mark those tasks as done and delete.
 */
public class Bob {

    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;
    private static Parser parser;

    /**
     * Constructor for Bob class.
     *
     * @param filePath the path to the file containing the previous tasks.
     */
    public Bob(String filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (BobException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Overloaded constructor that takes in no parameters.
     */
    public Bob() {
        this("data/tasks.txt");
    }

    /**
     * Runs the bot and calls for certain functions depending on the user input.
     */
    public void run() {
        Scanner obj = new Scanner(System.in);

        while (true) {
            String input = obj.nextLine();

            if (parser.isMark(input)) {
                ui.markTask(tasks, parser.getMarkDigit(input));
            } else if (parser.isDelete(input)) {
                ui.deleteTask(tasks, parser.getDeleteDigit(input));
            } else if (parser.isFind(input)) {
                ui.findTask(tasks, parser.findKeyword(input));
            } else if (input.equals("bye")) {
                ui.printGoodbye();
                storage.saveNewList(tasks);
                return;
            } else if (input.equals("list")) {
                ui.printTasks(tasks);
            } else {
                try {
                    ui.checkAndAddTask(tasks, input);
                } catch (BobException e) {
                    System.out.println(e.toString());
                }
            }
        }
    }

    /**
     * Returns the Bob's response to user depending on input.
     *
     * @param input the user's input to Bob.
     * @return the string representation of Bob's response.
     */
    public String getResponse(String input) {
        if (parser.isMark(input)) {
            return ui.markTask(tasks, parser.getMarkDigit(input));
        } else if (parser.isDelete(input)) {
            return ui.deleteTask(tasks, parser.getDeleteDigit(input));
        } else if (parser.isFind(input)) {
            return ui.findTask(tasks, parser.findKeyword(input));
        } else if (input.equals("bye")) {
            return ui.printGoodbye();
        } else if (input.equals("list")) {
            return ui.printTasks(tasks);
        } else {
            try {
                return ui.checkAndAddTask(tasks, input);
            } catch (BobException e) {
                //System.out.println(e.toString());
                return e.toString();
            }
        }
    }

    public static void main(String[] args) {
        new Bob("data/tasks.txt").run();
    }

}
