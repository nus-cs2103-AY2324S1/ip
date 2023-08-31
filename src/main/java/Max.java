import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeParseException;

import static java.lang.Integer.parseInt;

public class Max {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    public Max(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (MaxException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.greet();

        Scanner input = new Scanner(System.in);
        boolean scannerOpen = true;

        while (scannerOpen) {

        // User is interacting with chatbot

            String userInput = input.nextLine();

//            Parser parser = new Parser(userInput);

//            String command = userInput.split(" ")[0];

            // Initialise enum type for Command
//            Command commandEnum = Parser.parse(userInput);

            try {
                Parser.parse(userInput);
            } catch (MaxException e) {
                System.out.println(e.getMessage());
                ui.showLine();
            } catch (DateTimeParseException e) {
                System.out.println("Please use yyyy-mm-dd format!");
            } finally {
                ui.showLine();
            }
        }

        // User has exited the chatbot
        input.close();
        ui.exit();

    }






    public static void main(String[] args) {
        new Max("data/tasks.txt").run();
    }
}
