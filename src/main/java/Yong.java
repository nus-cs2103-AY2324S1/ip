import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;




/**
 * Chatbot named YONG that responds to user input using CLI
 */
public class Yong {

    public static void main(String[] args) {
        Yong.run();
    }

    public static void run() {
        UI ui = new UI();
        ui.showWelcome();
        TaskList taskList = new TaskList(new ArrayList<Task>());
        Storage storage = new Storage(taskList);
        storage.createFile();
        storage.readFile();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Parser parser = new Parser(taskList);
                Command c = parser.parse(fullCommand);
                c.execute();
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        storage.saveFile();
    }
}
