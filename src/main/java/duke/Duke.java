package duke;

import duke.command.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private String filePath;
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke (String filePath){
        this.filePath = filePath;
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try{
            ArrayList<String> taskStringList = this.storage.load();
            this.taskList = new TaskList(taskStringList);
        } catch (DukeException e){
            System.out.println(e.getMessage());
        }

    }

    public void run() throws DukeException {
        Parser parser = new Parser();
        ui.showWelcome();
        taskList.load();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // If user types bye, goodbye message is printed.
            try {
                String userInput = scanner.nextLine().trim();
                Command c = parser.parse(userInput);
                c.execute(taskList, ui);
                if (c instanceof ExitCommand) {
                    break;
                }
            } catch (DukeException e){
                System.out.println(e.getMessage());
            }
        }
        File file = new File(this.filePath);
        boolean isFileDeleted = file.delete();
        if (isFileDeleted){
            storage.update(this.taskList, this.filePath);
        }
        scanner.close();
    }

    public static void main(String[] args) throws DukeException {
        new Duke("data/duke.txt").run();
    }
}
