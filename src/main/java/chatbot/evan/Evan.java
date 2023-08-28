package chatbot.evan;

import java.util.Scanner;

import command.*;
import enums.Command;
import exception.InvalidInputException;
import exception.InvalidCommandException;
import exception.MissingArgumentException;
import task.TaskList;
import ui.Reply;

/**
 * Main class for the chatbot
 */
public class Evan {

    private static TaskList tasks = TaskList.init();
    private static Reply reply = Reply.init();

    /**
     * main method of the Main class Evan chatbot
     * Continuously takes in user input and gives an appropriate response until the user enters "bye
     * @param args unused
     */
    public static void main(String[] args) {
        //Start user interaction
        Scanner scanner = new Scanner(System.in);
        while(true) {
            try {
                String input = scanner.nextLine();
                if (input.toLowerCase().equals(Command.BYE.getCommand())) {
                    reply.printDialog("Bye. Hope to see you again soon!");
                    return;
                } else if (input.toLowerCase().equals(Command.LIST.getCommand())) {
                    tasks.printTasks();
                } else if (input.startsWith(Command.MARK.getCommand())) {
                    MarkCommand.start(input);
                } else if (input.startsWith(Command.UNMARK.getCommand())) {
                    UnmarkCommand.start(input);
                } else if (input.toLowerCase().equals(Command.TODO.getCommand())) {
                    ToDoCommand.start();
                } else if (input.toLowerCase().equals(Command.DEADLINE.getCommand())) {
                    DeadlineCommand.start();
                } else if (input.toLowerCase().equals(Command.EVENT.getCommand())) {
                    EventCommand.start();
                } else if (input.startsWith(Command.DELETE.getCommand())) {
                    DeleteCommand.start(input);
                } else {
                    throw new InvalidCommandException();
                }
            } catch (InvalidInputException e) {
                reply.printDialog(e.toString());
            } catch (MissingArgumentException e) {
                reply.printDialog(e.toString());
            } catch (InvalidCommandException e) {
                reply.printDialog(e.toString());
            }
        }
    }


}