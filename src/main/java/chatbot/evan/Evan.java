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
import task.TaskManager;

/**
 * Main class for the chatbot
 */
public class Evan {
    private static TaskManager tasks = TaskManager.init();
    private ComplexProcess process = null;

    public String getResponse(String input) {
        if (process != null) {
            return getExistingProcessResponse(input);
        }

        return startProcessThenGetResponse(input);
    }

    private String getExistingProcessResponse(String input) {
        String response = process.processInput(input);
        if (process.isComplete()) {
            process = null;
        }
        return response;
    }

    private String startProcessThenGetResponse(String input) {
        if (input.equals(Command.BYE.toString())) {
            return "Bye. Hope to see you again soon!";
        } else if (input.equals(Command.LIST.toString())) {
            return tasks.printTasks();
        } else if (input.equals(Command.TODO.toString())) {
            process = new ToDo();
            return process.firstInstruction();
        } else if (input.equals(Command.DEADLINE.toString())) {
            process = new Deadline();
            return process.firstInstruction();
        } else if (input.equals(Command.EVENT.toString())) {
            process = new Event();
            return process.firstInstruction();
        } else if (input.startsWith(Command.DELETE.toString())) {
            SimpleProcess simpleProcess = new Delete();
            return simpleProcess.processInput(input);
        } else if (input.startsWith(Command.MARK.toString())) {
            SimpleProcess simpleProcess = new Mark();
            return simpleProcess.processInput(input);
        } else if (input.startsWith(Command.UNMARK.toString())) {
            SimpleProcess simpleProcess = new Unmark();
            return simpleProcess.processInput(input);
        } else if (input.startsWith(Command.FIND.toString())) {
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
                .append("delete: delete a task from the list\n")
                .append("find: find a task from the list\n");
        return stringBuilder.toString();
    }
}
