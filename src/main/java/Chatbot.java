import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class Chatbot {

    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        Ui ui = new Ui();

        Storage.load(taskList);
        Ui.startMessage();
        Scanner userInput = new Scanner(System.in);

        while (true) {
            String userMessage = userInput.nextLine();
            Parser.parse(userMessage, taskList);
            Storage.saveTasks(taskList);
        }
    }
}
