import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Mil {
    private static Storage storage;
    private static TaskList taskList;
    private static Ui ui;
    private static Parser parser;
    public Mil() {
        taskList = new TaskList();
        ui = new Ui();
        storage = new Storage();
        parser = new Parser(taskList, ui,storage);
    }

    public static void main(String[] args) {
        Mil mil = new Mil();
        Scanner scanner = new Scanner(System.in);
        String input;
        ui.printWelcomeMessage();
        storage.loadTasksFromFile(taskList);

        while (scanner.hasNext()) {
            input = scanner.nextLine();
            if (input.equals("bye")) {
                storage.saveTasksToFile(taskList);
                ui.printGoodbyeMessage();
                break;
            } else {
                parser.parseInput(input);
            }
        }

    }
}
