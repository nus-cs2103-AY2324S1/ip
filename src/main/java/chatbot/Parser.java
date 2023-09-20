package chatbot;

import chatbot.exception.InvalidTodoException;
import chatbot.exception.InvalidEventException;
import chatbot.exception.InvalidDeadlineException;
import chatbot.exception.InvalidCommandException;
import chatbot.exception.InvalidTaskNumberException;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    /**
     * Parses user input for add method. If input is appropriate, returns an array containing the type of task,
     * description, and other parameters, in that order.
     *
     * @param input The command inputted by the user.
     * @returns An array containing the type and details of the task.
     * @throws InvalidTodoException If format of todo entered is invalid.
     * @throws InvalidDeadlineException If format of deadline entered is invalid.
     * @throws InvalidEventException If format of event entered is invalid.
     * @throws InvalidCommandException If format of command entered is invalid.
     */
    public List<String> parseAdd(String input) throws InvalidTodoException,
            InvalidDeadlineException, InvalidEventException, InvalidCommandException {
        List<String> arr = new ArrayList<>();
        String nextInput;

        try {
            nextInput = input.substring(4);
            checkInputIsNotEmpty(nextInput);
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidCommandException();
        }

        if (nextInput.startsWith("todo")) {
            parseTodoInput(arr, nextInput);
        } else if (nextInput.startsWith("deadline")) {
            parseDeadlineInput(arr, nextInput);
        } else if (nextInput.startsWith("event")) {
            parseEventInput(arr, nextInput);
        } else {
            throw new InvalidCommandException();
        }
        return arr;
    }

    /**
     * Parses user input. If input is appropriate, modifies the array to contain the type of task, description,
     * start and end time, in that order.
     *
     * @param arr The array to modify.
     * @param nextInput The user input.
     * @throws InvalidEventException If format of event entered is invalid.
     * @throws InvalidCommandException If format of command entered is invalid.
     */
    private void parseEventInput(List<String> arr, String nextInput) throws InvalidCommandException, InvalidEventException {
        try {
            int from = nextInput.indexOf("/from");
            int to = nextInput.indexOf("/to");
            String description = nextInput.substring(6, from - 1);
            String start = nextInput.substring(from + 6, to - 1);
            String end = nextInput.substring(to + 4);

            checkInputIsNotEmpty(description, start, end);

            arr.add(0, "event");
            arr.add(1, description);
            arr.add(2, start);
            arr.add(3, end);
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidEventException();
        }
    }

    /**
     *  Parses user input. If input is appropriate, modifies the array to contain the type of task, description,
     *  and deadline, in that order.
     *
     * @param arr The array to modify.
     * @param nextInput The user input.
     * @throws InvalidDeadlineException If format of deadline entered is invalid.
     * @throws InvalidCommandException If format of command entered is invalid.
     */
    private void parseDeadlineInput(List<String> arr, String nextInput) throws InvalidCommandException, InvalidDeadlineException {
        try {
            int by = nextInput.indexOf("/by");
            String description = nextInput.substring(9, by - 1);
            String deadline = nextInput.substring(by + 4);

            checkInputIsNotEmpty(description, deadline);

            arr.add(0,"deadline");
            arr.add(1, description);
            arr.add(2, deadline);
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidDeadlineException();
        }
    }

    /**
     *  Parses user input. If input is appropriate, modifies the array to contain the type of task and description
     *  in that order.
     *
     * @param arr The array to modify.
     * @param nextInput The user input.
     * @throws InvalidTodoException If format of todo entered is invalid.
     * @throws InvalidCommandException If format of command entered is invalid.
     */
    private void parseTodoInput(List<String> arr, String nextInput) throws InvalidCommandException, InvalidTodoException {
        try {
            String description = nextInput.substring(5);

            checkInputIsNotEmpty(description);

            arr.add(0, "todo");
            arr.add(1, description);
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidTodoException();
        }
    }

    /**
     * Parses input for markTaskStatusTrue method. If input is appropriate, returns an integer
     * representing the number of the task to be marked.
     *
     * @param input The user input.
     * @returns The number of the task.
     * @throws InvalidCommandException If format of command entered is invalid.
     */
    public int parseMarkTaskStatusTrue(String input) throws InvalidCommandException {
        int taskIndex;
        try {
            String nextInput = input.substring(5);
            checkInputIsNotEmpty(nextInput);
            taskIndex = Integer.parseInt(nextInput);
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidCommandException();
        } catch (NumberFormatException e) {
            throw new InvalidCommandException();
        }
        return taskIndex;
    }

    /**
     * Parses input for markTaskStatusFalse method. If input is appropriate, returns an integer
     * representing the number of the task to be unmarked.
     *
     * @param input The user input.
     * @returns The number of the task.
     * @throws InvalidCommandException If format of command entered is invalid.
     */
    public int parseMarkTaskStatusFalse(String input) throws InvalidCommandException {
        int taskIndex;
        try {
            String nextInput = input.substring(7);
            checkInputIsNotEmpty(nextInput);
            taskIndex = Integer.parseInt(nextInput);
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidCommandException();
        } catch (NumberFormatException e) {
            throw new InvalidCommandException();
        }
        return taskIndex;
    }

    /**
     * Parses input for the find method. If input is appropriate, returns a String of the keyword to match with.
     *
     * @param input The user input.
     * @returns The String keyword to search for.
     * @throws InvalidCommandException If format of command entered is invalid.
     */
    public String parseFind(String input) throws InvalidCommandException {
        String keyword;
        try {
            keyword = input.substring(5);
            checkInputIsNotEmpty(keyword);
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidCommandException();
        }
        return keyword;
    }

    /**
     * Parses input for addTaskTag method. If input is appropriate, returns a list
     * containing the number of the task to be tagged and the description of the tag.
     *
     * @param input The user input.
     * @returns A list containing the number of the task and the tag description.
     * @throws InvalidCommandException If format of command entered is invalid.
     */
    public List<String> parseAddTaskTag(String input) throws InvalidCommandException {
        List<String> arr = new ArrayList<>();

        try {
            String taskNum = input.substring(4, 5);
            checkInputIsNotEmpty(taskNum);

            //conduct check that input includes number
            int taskNumAsInt = Integer.parseInt(taskNum);

            String desc = input.substring(6);
            checkInputIsNotEmpty(desc);

            arr.add(taskNum);
            arr.add(desc);
            return arr;
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidCommandException();
        } catch (NumberFormatException e) {
            throw new InvalidCommandException();
        }
    }

    /**
     * Parses input for the delete method. If input is appropriate, returns an integer
     * representing the number of the task to be deleted.
     *
     * @param input The user input.
     * @returns The number of the task.
     * @throws InvalidCommandException If format of command entered is invalid.
     */
    public int parseDelete(String input) throws InvalidCommandException {
        int taskIndex;
        try {
            String nextInput = input.substring(7);
            checkInputIsNotEmpty(nextInput);
            taskIndex = Integer.parseInt(nextInput);
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidCommandException();
        }
        return taskIndex;
    }

    /**
     * Checks that given String inputs are not blank. Throws InvalidCommandException if they are.
     *
     * @param inputs The String inputs to check.
     * @throws InvalidCommandException If any of the inputs are blank.
     */
    private void checkInputIsNotEmpty(String... inputs) throws InvalidCommandException {
        for (String input : inputs) {
            if (input.isBlank()) {
                throw new InvalidCommandException();
            }
        }
    }
}
