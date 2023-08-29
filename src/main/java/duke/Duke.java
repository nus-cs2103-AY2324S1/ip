package duke;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        ArrayList<Task> taskList = new ArrayList<>();

        Storage.loadTasksFromFile(taskList);

        Ui ui = new Ui();
        ui.printIntroMsg();

        input = scanner.nextLine();

        while (!input.equals("bye")) {
            ui.printSeparator();
            Parser.parse(input, taskList);
            Storage.updateTasksFile(taskList);
            ui.printSeparator();
            input = scanner.nextLine();
        }

        scanner.close();
        ui.printOutroMsg();
    }

}