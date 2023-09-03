import Database.Dbops;
import Handlers.CentralCommandHandler;
import Models.Deadline;
import Models.Event;
import Models.ToDo;
import Exceptions.DukeInvalidFormatException;
import Models.TaskArray;

import java.util.Objects;
import java.util.Scanner;

import static Printers.BasicOutputPrinter.printBasicOutput;
import static Printers.ErrorOutputPrinter.printErrorOutput;

public class Duke {
    private static TaskArray tasks;
    private static CentralCommandHandler commandHandler;

    private static void initializeBot() {
        Duke.tasks = Dbops.initializeDatabase();
        Duke.commandHandler = CentralCommandHandler.initializeCommandHandler(tasks);
    }
    public static void main(String[] args) {
        Duke.initializeBot();

        String introduction = " Hello! I'm EGGBOT!\n" +
                " Please add a task!\n\n" +
                " To add a ToDo task, type 'todo [Task]'\n" +
                " To add a Deadline task, type 'deadline [Task /Deadline]'\n" +
                " To add an Event task, type 'event [Task /Start Date/End Date]'\n" +
                " To view tasks, type 'list' \n" +
                " To mark a task as 'done', type 'mark [index]' \n" +
                " To mark a task as 'undone', type 'unmark [index]' \n" +
                " To delete a task, type 'delete [index]' \n" +
                " To exit, type 'bye'";

        printBasicOutput(introduction);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Please type a command, or type 'help' to show available commands:");

            String input = scanner.nextLine().strip();  // Read input
            Duke.commandHandler.parseInput(input);
        }

    }
}
