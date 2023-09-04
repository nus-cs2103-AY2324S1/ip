package duke;

import duke.task.TaskList;
import java.util.Scanner;

/**
 * Represents Duke, a Personal Assistant Chatbot that helps a person to keep track of
 * various things. The name Duke was chosen as a placeholder name, in honor of Duke,
 * the Java Mascot. The current name of the Chatbot is John.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructor for a Duke object.
     * Initialises the ui, storage, and taskList.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("./data/duke.txt");
        taskList = new TaskList(storage.retrieveData());
    }

    /**
     * Starts the Chatbot and continuously reads user input, processes commands,
     * and provides responses until the user exits.
     */
    public void run() {
        storage.createFile();
        ui.startMessage();

        Scanner sc = new Scanner(System.in);

        while (true) {
            ui.promptReply();
            String command = sc.nextLine();

            if (command.equals("bye")) {
                ui.endMessage();
                storage.save(taskList.getAllTasks());
                break;
            } else {
                try {
                    new Parser().processCommand(command, taskList);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        sc.close();
    }

    /**
     * Our main method for the Chatbot to work. It starts the Chatbot by calling
     * the run method.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}