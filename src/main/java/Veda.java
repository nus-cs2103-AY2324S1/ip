import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Veda {

    private final static Storage storage = new Storage();
    private final static TaskList tasks = new TaskList(storage);
    private final static Ui ui = new Ui(new Scanner(System.in));

    private static void initialise() {
        ui.welcome();
        tasks.load();
    }

    private static void run() {
        while (true) {
            String input = ui.getInput();

            int method = Parser.parse(input); //Get which commands to perform based on user's input

            switch (method) {
                case -1:
                    //Unrecognised input
                    System.out.println("Unrecognised command.");
                    break;

                case 0:
                    //User wishes to exit the program
                    ui.exit();
                    return;

                case 1:
                    //User wishes to see listed missions
                    tasks.printList();
                    break;

                case 2:
                    //User wishes to mark task as done
                    try {
                        tasks.markAsDone(Parser.getTargetIndex(input));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid index! Please ensure you correctly key in your target index.");
                    }
                    break;

                case 3:
                    //User wishes to mark task as undone
                    try {
                        tasks.markUndone(Parser.getTargetIndex(input));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid index! Please ensure you correctly key in your target index.");
                    }
                    break;

                case 4:
                    //User wishes to delete a task
                    try {
                        tasks.deleteTask(Parser.getTargetIndex(input));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid index! Please ensure you correctly key in your target index.");
                    }
                    break;

                case 5:
                    //User wishes to add a new task
                    try {
                        tasks.addTask(Parser.getTask(input));
                    } catch (IncorrectInputException e) {
                        System.out.println(e);
                    } catch (DateTimeParseException e) {
                        System.out.println("Ensure your deadline is of the format {dd/MM/yyyy HHmm}");
                    }
            }


        }
    }

    public static void main(String[] args) {
        initialise();

        run();
    }
}
