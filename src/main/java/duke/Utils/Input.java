package duke.Utils;

import java.util.Scanner;

public class Input {
    private static final String FILEPATH = "./data/duke.csv";
    private static final String FOLDERPATH = "./data";
    private static Scanner scanner = new Scanner(System.in);
    private Storage storage;
    private TaskList tasks;
    private String input;

    protected Input() {
        this.storage = new Storage(Input.FILEPATH, Input.FOLDERPATH);
        this.tasks = new TaskList(this.storage.load());
    }
    
    protected Response command() {
        this.input = Input.scanner.nextLine();
        return executeCommand();
    }

    protected Response executeCommand() {
        String command = this.input.split(" ")[0];
        if (command.equals("bye")) {
            return Response.TERMINATE;
        }
        try {
            Response response = this.tasks.execute(this.input, command);
            this.storage.save(this.tasks.csvArray());
            return response;
        } catch (DukeException e) {
            return Response.generate(e.toString());
        }
    }
}


