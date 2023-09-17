package duke;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exceptions.DukeException;
import exceptions.EmptyDescriptionException;
import exceptions.OutOfRangeException;
import exceptions.UnknownCommandException;
import javafx.fxml.FXML;
import javafx.scene.image.Image;

/**
 * Starts the chatbot
 */
public class Duke {

    /** To access file which stores the saved tasks*/
    private final Storage storage;

    /** Stores new instance of UI */
    private final Ui ui;

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
        ui = new Ui();
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
                int num = input.charAt(5) - '0' - 1;
                if (num >= 0 && num < list.count) {
                    list.getTask(num).markAsDone();
                    message = "Nice! I've marked this task done:" + list.getTask(num);
                } else {
                    System.out.println("Invalid");
                }
            } else if (input.startsWith("unmark ")) {
                int num = input.charAt(7) - '0' - 1;
                if (num >= 0 && num < list.count) {
                    list.getTask(num).markAsUndone();
                    message = "OK, I've marked this task as not done yet:" + list.getTask(num);
                } else {
                    message = "Invalid";
                }
            } else if (input.startsWith("todo ")) {
                String des = input.substring(5);
                if (des.isBlank()) {
                    throw new EmptyDescriptionException();
                }
                message = list.addTask(new Todo(des));
            } else if (input.startsWith("deadline ")) {
                if (input.substring(9).isBlank()) {
                    throw new EmptyDescriptionException();
                }
                String[] split = input.substring(9).split(" /by ");
                String des = split[0];
                LocalDate date = LocalDate.parse(split[1]);
                String by = date.format(DateTimeFormatter.ofPattern("MMM d yyy"));
                message = list.addTask(new Deadline(des, by));

            } else if (input.startsWith("event ")) {
                if (input.substring(6).isBlank()) {
                    throw new EmptyDescriptionException();
                }
                String[] split = input.substring(6).split(" /from ");
                String des = split[0];
                String[] fromto = split[1].split(" /to ");
                String from = fromto[0];
                String to = fromto[1];
                message = list.addTask(new Event(des, from, to));
            } else if (input.startsWith("delete ")) {
                int index = Integer.parseInt(input.substring(7)) - 1;
                if (index >= 0 && index <= list.count) {
                    message = list.deleteTask(index);
                } else {
                    throw new OutOfRangeException();
                }
            } else if (input.equals("bye")) {
                message = "slay";
            } else if (input.startsWith("find ")) {
                String description = input.substring(5);
                message = list.findTask(description, list);
            } else {
                throw new UnknownCommandException();
            }
        } catch (DukeException e) {
            message = e.getMessage();
        } catch (DateTimeParseException e) {
            message = "OOps invalid time input";
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


