import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public static Path pathOfDirectory = Paths.get("./data/duke.txt");


    ;       //enum that stores all important constants

    public Duke(String filePath) {
        ui = new Ui ();

        try {
            Storage.readFromDisk(pathOfDirectory, TaskList.storeTask);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("An error occurred...");
        }

    }

    public void run() {
        ui.showGreeting();
        Scanner userInputObject = new Scanner(System.in);
        while (true) {
            ui.userInputUsher();
            String userInput = userInputObject.nextLine();

            try {
                boolean isBreak = Parser.parse(userInput);
                if (!isBreak) {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println(Ui.indent + "Invalid character input");
            } catch (DukeException e) {
                System.out.println(Ui.indent + "Error: " + e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                System.out.println(Ui.indent + "Invalid entry / Task not in list... Please try again...");
            } catch (IllegalArgumentException e) {
                System.out.println("☹ OOPS!!! Sorry, but i do not know what that means :-(");
            }
        }
    }
    public static void main(String[] args) {
        new Duke("/data/duke.txt").run();
    }




}