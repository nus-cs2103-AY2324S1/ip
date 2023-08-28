import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadStringFromFile());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
    public static void main(String[] args) {


        //Add the ability to store whatever text entered by the user and display them back to the user when requested.
        TaskList userTasks = new TaskList();

        Scanner scanner = new Scanner(System.in);
        String input;
        do {
            input = scanner.nextLine();
            try {
                System.out.println("####################");
                System.out.println("Your request is: " + input);
                userTasks.handleAction(input);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } 
        } while (!input.equals("bye"));
    }
}
