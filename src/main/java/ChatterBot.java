import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ChatterBot {
    public static ArrayList<Task> list = new ArrayList<>();
    public static String file = "../data/ChatterBot.txt";
    public static void main(String[] args) {

        Ui ui = new Ui(list);
        Storage storage = new Storage(file, list);
        TaskList taskList = new TaskList(list);

        ui.showWelcomeMessage();

        File f = new File(file);
        File folder = f.getParentFile();
        if (!folder.exists()) {
            System.out.println("Error! No data folder found");
        }

        taskList.initiateTaskList(storage);

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String userMessage = scanner.nextLine();
            Parser.evaluateCommand(userMessage, ui, list, storage, file, taskList);
        }
    }
}