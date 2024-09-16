package duke;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import exceptions.DukeException;
import exceptions.UnknownCommandException;
import javafx.fxml.FXML;
import javafx.scene.image.Image;

/**
 * Starts the chatbot
 */
public class Duke {

    /** To access file which stores the saved tasks. */
    private final Storage storage;

    /** List to be updated as tasks are added or removed. */
    private final TaskList list;

    private Exit exit;

    /**
     * To be called when starting up chatbot. Shows welcome
     * message, starts reading user input and load tasks
     * from the file into tasklist.
     *
     * @throws FileNotFoundException when file is missing
     */
    public Duke() throws FileNotFoundException {
        storage = new Storage("./duke.txt");
        list = new TaskList(storage.load());
        exit = new Exit();
    }

    /**
     * ALlows the chatbbot to run.
     */
    public String run(String input, TaskList list) {
        String message = "";
        try {
            if (input.equals("list")) {
                 message = list.printList();
            } else if (input.startsWith("mark ")) {
                message = Parser.parseMark(input, list);
            } else if (input.startsWith("unmark ")) {
                message = Parser.parseUnmark(input, list);
            } else if (input.startsWith("todo ")) {
                message = Parser.parseToDo(input, list);
            } else if (input.startsWith("deadline ")) {
                message = Parser.parseDeadline(input, list);
            } else if (input.startsWith("event ")) {
                message = Parser.parseEvent(input, list);
            } else if (input.startsWith("delete ")) {
                message = Parser.parseDelete(input, list);
            } else if (input.equals("bye")) {
                message = "slay";
                exit.start();
            } else if (input.startsWith("find ")) {
                message = Parser.parseFind(input, list);
            } else if (input.startsWith("edit ")){
                message = Parser.parseEdit(input, list);
            } else {
                throw new UnknownCommandException();
            }
        } catch (DukeException e) {
            message = e.getMessage();
        }
        storage.save(list);
        return message;
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    @FXML
    String getResponse(String input){
        return run(input, list);
    }
}


