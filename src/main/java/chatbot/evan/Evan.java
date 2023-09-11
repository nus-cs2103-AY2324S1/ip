package chatbot.evan;

import enums.Command;
import exception.InvalidCommandException;
import process.ComplexProcess;
import process.Deadline;
import process.Delete;
import process.Event;
import process.Find;
import process.Mark;
import process.SimpleProcess;
import process.ToDo;
import process.Unmark;
import task.TaskList;

/**
 * Main class for the chatbot
 */
public class Evan {
    private static TaskList tasks = TaskList.init();
    private ComplexProcess process = null;

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        if (process != null) {
            String response = process.processInput(input);
            if (process.isComplete()) {
                process = null;
            }
            return response;
        }

        if (input.equals(Command.BYE.getCommand())) {
            return "Bye. Hope to see you again soon!";
        } else if (input.equals(Command.LIST.getCommand())) {
            return tasks.printTasks();
        } else if (input.equals(Command.TODO.getCommand())) {
            process = new ToDo();
            return process.start();
        } else if (input.equals(Command.DEADLINE.getCommand())) {
            process = new Deadline();
            return process.start();
        } else if (input.equals(Command.EVENT.getCommand())) {
            process = new Event();
            return process.start();
        } else if (input.startsWith(Command.DELETE.getCommand())) {
            SimpleProcess simpleProcess = new Delete();
            return simpleProcess.processInput(input);
        } else if (input.startsWith(Command.MARK.getCommand())) {
            SimpleProcess simpleProcess = new Mark();
            return simpleProcess.processInput(input);
        } else if (input.startsWith(Command.UNMARK.getCommand())) {
            SimpleProcess simpleProcess = new Unmark();
            return simpleProcess.processInput(input);
        } else if (input.startsWith(Command.FIND.getCommand())) {
            SimpleProcess simpleProcess = new Find();
            return simpleProcess.processInput(input);
        } else {
            InvalidCommandException e = new InvalidCommandException();
            return e.toString();
        }
    }

    public String getIntro() {
        StringBuilder stringBuilder = new StringBuilder("Hello! I'm Evan, your personal task planning assistant\n")
                .append("What can I do for you?\n\n")
                .append("List of available commands:\n")
                .append("todo: create a new todo task\n")
                .append("deadline: create a new deadline task\n")
                .append("event: create a new event task\n")
                .append("mark: mark a task as complete\n")
                .append("unmark: mark a task as incomplete\n")
                .append("delete: delete a task from the list\n");
        return stringBuilder.toString();
    }
}
