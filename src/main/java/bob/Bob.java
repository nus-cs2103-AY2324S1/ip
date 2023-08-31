package bob;

import java.util.Scanner;

/**
 * Represents a bot that can record three types of tasks: todo, deadline and event, as well as
 * mark those tasks as done and delete
 */
public class Bob {

    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;
    private static Parser parser;

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

    public void run(){
        Scanner obj = new Scanner(System.in);

        while (true) {
            String input = obj.nextLine();

            if (parser.isMark(input)) {
                ui.markTask(tasks, parser.getMarkDigit(input));
            } else if (parser.isDelete(input)) {
                ui.deleteTask(tasks, parser.getDeleteDigit(input));
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

    public static void main(String[] args) {
        new Bob("data/tasks.txt").run();
    }

}
