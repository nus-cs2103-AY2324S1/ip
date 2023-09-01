import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    /**
     * Parses user input for add method. If input is appropriate, returns an array containing the type of task,
     * description, and other parameters, in that order.
     *
     * @param input The command inputted by the user.
     * @returns An array containing the type and details of the task.
     * @throws InvalidTodoException If format of Todo entered is invalid.
     * @throws InvalidDeadlineException If format of Deadline entered is invalid.
     * @throws InvalidEventException If format of Event entered is invalid.
     * @throws InvalidCommandException If format of command entered is invalid.
     */
    public List<String> parseAdd(String input) throws InvalidTodoException,
            InvalidDeadlineException, InvalidEventException, InvalidCommandException {
        List<String> arr = new ArrayList<>();
        String nextInput;

        try {
            nextInput = input.substring(4);

            if (nextInput.isBlank()) {
                throw new InvalidCommandException();
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidCommandException();
        }

        if (nextInput.startsWith("todo")) {
            try {
                String description = nextInput.substring(5);

                if (description.isBlank()) {
                    throw new InvalidTodoException();
                } else {
                    arr.add(0, "todo");
                    arr.add(1, description);
                }
            } catch (StringIndexOutOfBoundsException e) {
                throw new InvalidTodoException();
            }
        } else if (nextInput.startsWith("deadline")) {
            try {
                int by = nextInput.indexOf("/by");
                String description = nextInput.substring(9, by - 1);
                String deadline = nextInput.substring(by + 4);

                if (description.isBlank()) {
                    throw new InvalidDeadlineException();
                } else {
                    arr.add(0,"deadline");
                    arr.add(1, description);
                    arr.add(2, deadline);
                }
            } catch (StringIndexOutOfBoundsException e) {
                throw new InvalidDeadlineException();
            }
        } else if (nextInput.startsWith("event")) {
            try {
                int from = nextInput.indexOf("/from");
                int to = nextInput.indexOf("/to");
                String description = nextInput.substring(6, from - 1);
                String start = nextInput.substring(from + 6, to - 1);
                String end = nextInput.substring(to + 4);

                if (description.isBlank()) {
                    throw new InvalidEventException();
                } else {
                    arr.add(0, "event");
                    arr.add(1, description);
                    arr.add(2, start);
                    arr.add(3, end);
                }
            } catch (StringIndexOutOfBoundsException e) {
                throw new InvalidEventException();
            }
        } else {
            throw new InvalidCommandException();
        }
        return arr;
    }

    /**
     * Parses input for markTaskStatusTrue method. If input is appropriate, returns an integer
     * representing the number of the task to be marked.
     *
     * @param input The user input.
     * @returns The number of the task.
     * @throws InvalidTaskNumberException If there is no task with the task number in the taskList.
     * @throws InvalidCommandException If format of command entered is invalid.
     */
    public int parseMarkTaskStatusTrue(String input) throws InvalidTaskNumberException, InvalidCommandException {
        int taskIndex;
        try {
            String nextInput = input.substring(5);
            if (nextInput.isBlank()) {
                throw new InvalidCommandException();
            } else {
                taskIndex = Integer.parseInt(nextInput);
            }
        } catch (StringIndexOutOfBoundsException e) {
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
     * @throws InvalidTaskNumberException If there is no task with the task number in the taskList.
     * @throws InvalidCommandException If format of command entered is invalid.
     */
    public int parseMarkTaskStatusFalse(String input) throws InvalidTaskNumberException, InvalidCommandException {
        int taskIndex;
        try {
            String nextInput = input.substring(7);
            if (nextInput.isBlank()) {
                throw new InvalidCommandException();
            } else {
                taskIndex = Integer.parseInt(nextInput);
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidCommandException();
        }
        return taskIndex;
    }

    /**
     * Parses input for the delete method. If input is appropriate, returns an integer
     * representing the number of the task to be deleted.
     *
     * @param input The user input.
     * @returns The number of the task.
     * @throws InvalidTaskNumberException If there is no task with the task number in the taskList.
     * @throws InvalidCommandException If format of command entered is invalid.
     */
    public int parseDelete(String input) throws InvalidTaskNumberException, InvalidCommandException {
        int taskIndex;
        try {
            String nextInput = input.substring(7);
            if (nextInput.isBlank()) {
                throw new InvalidCommandException();
            } else {
                taskIndex = Integer.parseInt(nextInput);
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidCommandException();
        }
        return taskIndex;
    }
}
