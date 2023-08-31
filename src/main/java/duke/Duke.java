package duke;

// fixing DukeException based on my understanding of exceptions 27/8/23
import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.tasks.TaskList;

public class Duke {
    public UI helper;
    public TaskList tasks;
    public Storage storage;

    public Duke(String filePath) {
        this.helper = new UI("DukeKing");
        try {
            this.storage = new Storage(filePath);
            this.tasks = new TaskList(storage.load());
        } catch (Exception e) {
            this.tasks = new TaskList();
            helper.noFile();
        }
    }

    public static void main(String[] args) {
        new Duke("./dataTasks.txt").run();
    }

    public void run() {
        helper.welcome();
        // setting up
        Scanner sc = new Scanner(System.in);
        String string = sc.nextLine();
        boolean isDone = false;
        String commandType = "";

        // looping in the program
        while (true) {
            // end the program
            try {
                isDone = Parser.parse(string, tasks, helper, storage);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } finally {
                if (isDone) {
                    commandType = "bye";
                }
                if (commandType.equals("bye")) {
                    break;
                }
                helper.printLine();
                string = sc.nextLine();
            }
        }
        // end the program
        sc.close();
        if (commandType.equals("bye")) {
            helper.bye();
        }
    }
}
