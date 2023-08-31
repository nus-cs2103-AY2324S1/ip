package chatbot;

import chatbot.exceptions.*;
import chatbot.tasks.DeadlineTask;
import chatbot.tasks.EventTask;
import chatbot.tasks.Task;
import chatbot.tasks.ToDoTask;

public class Parser {
    public static String parseFindCommand(String command) throws FindMissingFieldException {
        try {
            String name = command.substring(5);
            if (name.isEmpty()) {
                throw new FindMissingFieldException();
            }
            return name;
        } catch (IndexOutOfBoundsException e) {
            throw new FindMissingFieldException();
        }
    }

    public static int parseMarkCommand(String[] commandWords) throws MarkMissingFieldException, InvalidTaskIndexException {
        if (commandWords.length != 2) {
            throw new MarkMissingFieldException();
        }
        try {
            return Integer.parseInt(commandWords[1]);
        } catch (NumberFormatException e) {
            throw new InvalidTaskIndexException();
        }
    }

    public static int parseDeleteCommand(String[] commandWords) throws DeleteMissingFieldException, InvalidTaskIndexException {
        if (commandWords.length != 2) {
            throw new DeleteMissingFieldException();
        }
        try {
            return Integer.parseInt(commandWords[1]);
        } catch (NumberFormatException e) {
            throw new InvalidTaskIndexException();
        }
    }

    public static Task parseTodoTaskCommand(String command) throws TodoMissingFieldException {
        try {
            return new ToDoTask(command.substring(5));
        } catch (IndexOutOfBoundsException e) {
            throw new TodoMissingFieldException();
        }
    }

    public static Task parseDeadlineTaskCommand(String command) throws DeadlineMissingFieldException {
        int idOfBy = command.indexOf("/by");
        if (idOfBy == -1) {
            throw new DeadlineMissingFieldException();
        }
        try {
            String name = command.substring(9, idOfBy - 1);
            String deadline = command.substring(idOfBy + 4);
            if (name.isEmpty() || deadline.isEmpty()) {
                throw new DeadlineMissingFieldException();
            }
            return new DeadlineTask(name, deadline);
        } catch (IndexOutOfBoundsException e) {
            throw new DeadlineMissingFieldException();
        }
    }

    public static Task parseEventTaskCommand(String command) throws EventMissingFieldException {
        int idOfFrom = command.indexOf("/from");
        int idOfTo = command.indexOf("/to");
        if (idOfFrom == -1 || idOfTo == -1) {
            throw new EventMissingFieldException();
        }
        try {
            String name = command.substring(6, idOfFrom - 1);
            String from = command.substring(idOfFrom + 6, idOfTo - 1);
            String to = command.substring(idOfTo + 4);
            if (name.isEmpty() || from.isEmpty() || to.isEmpty()) {
                throw new EventMissingFieldException();
            }
            return new EventTask(name, from, to);
        } catch (IndexOutOfBoundsException e) {
            throw new EventMissingFieldException();
        }
    }
}
