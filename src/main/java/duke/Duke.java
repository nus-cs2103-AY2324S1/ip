package duke;

import java.io.FileNotFoundException;

import exceptions.DukeException;
import exceptions.UnknownCommandException;
import javafx.fxml.FXML;
import javafx.scene.image.Image;

/**
 * Starts the chatbot
 */
public class Duke {

    /** To access file which stores the saved tasks*/
    private final Storage storage;

    /** List to be updated as tasks are added or removed. */
    private final TaskList list;

    /** Images of user and chatbot to be used for GUI*/
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * To be called when starting up chatbot. Shows welcome
     * message, starts reading user input and load tasks
     * from the file into tasklist.
     *
     * @throws FileNotFoundException
     */
    public Duke() throws FileNotFoundException {
        storage = new Storage("duke.txt");
//        try {
        list = new TaskList(storage.load());
//        } catch (Exceptions.DukeException e) {
//            ui.showLoadingError();
//            tasks = new duke.TaskList();
//        }
    }

    /**
     * ALlows the chatbbot to run
     */
    public String run(String input, TaskList list) { //returns string cus DialogBox returns string
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
                //should exit
            } else if (input.startsWith("find ")) {
                message = Parser.parseFind(input, list);
            } else {
                throw new UnknownCommandException();
            }
        } catch (DukeException e) {
            message = e.getMessage();
        }
//        ui.showWelcome();
//        boolean isExit = false;
//        Parser parser = new Parser();
//        while (!isExit) {
//            String input = ui.getUserCommand();
//            parser.parse(input, list);
//            isExit = ui.isExit(input);
//        }
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


