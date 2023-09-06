package duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private String filePath;

    /**
     * Returns a Duke Object which contains essential information for the functionality of this chatbot.
     * TaskList Object is instantiated to keep account of current tasks available for user (ArrayList)
     * Ui Object is instantiated to return appropriate UI prompts back to user
     *
     * @param filePath File Path for Duke.txt
     * @throws IOException If file is not located or not available
     */
    public Duke(String filePath) throws IOException {
        storage = new Storage(filePath);
        ui = new Ui();
        this.filePath = filePath;
        try {
            tasks = new TaskList(storage.load(storage.filePath));
        } catch (DukeException e) {
            tasks = new TaskList(new ArrayList<>());
        }
    }

    /**
     * Instantiates a Duke Object which contains essential information for the functionality of the chatbot
     * Generates a TaskList object based on the file located at "src/main/data/duke.txt"
     *
     * @throws IOException If file is not located or not available
     */
    public Duke() throws IOException {
        storage = new Storage("src/main/data/duke.txt");
        this.filePath = storage.filePath;
        try {
            tasks = new TaskList(storage.load(storage.filePath));
        } catch (DukeException e) {
            tasks = new TaskList(new ArrayList<>());
        }
    }

//    public void run() {
//        ui.textGenerator("Hello! I'm War Room.");
//        ui.textGenerator("What can I do for you?");
//        Scanner scanner = new Scanner(System.in);
//        try {
//            while (true) {
//                String user_input = scanner.nextLine();
//                Parser parser = new Parser(user_input);
//                parser.validateParser(tasks);
//                storage.save(filePath, tasks.userData);
//            }
//        } catch (Exception e) {
//            scanner.close();
//        }
//    }

//    public static void main(String[] args) throws IOException {
//        new Duke("src/main/data/duke.txt").run();
//    }

    /**
     * Returns a String object based on user's command to the DUKE chatbot
     * Instantiates a Parser object that is responsible for the handling of user's command
     *
     * @param user_input String Object that is from what the user's types
     * @return String Object AKA DUKE's response
     */
    public String getResponse(String user_input) {
        try {
            Parser parser = new Parser(user_input);
            String answer = parser.validateParser(tasks);
            storage.save(filePath, tasks.userData);
            return answer;
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}