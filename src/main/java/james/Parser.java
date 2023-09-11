package james;

/**
 * Parser class to parse user input.
 */
public class Parser {

    /**
     * Enum to represent the type of task.
     */
    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    private static String sadFace = "\u2639";
    private static String line = "___________________";

    /**
     * Parses the user input and returns a Task object.
     *
     * @param input User input.
     * @return Task object.
     * @throws JamesException If the user input is invalid.
     */
    public Task parseTask(String input) throws JamesException {
        Parser.TaskType taskType = null;
        if (input.contains("todo")) {
            taskType = Parser.TaskType.TODO;
        } else if (input.contains("deadline")) {
            taskType = Parser.TaskType.DEADLINE;
        } else if (input.contains("event")) {
            taskType = Parser.TaskType.EVENT;
        } else {
            throw new JamesException(sadFace + " OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        Task task;

        if (taskType == Parser.TaskType.TODO) {
            String[] description = input.split("todo ");
            if (description.length == 1) {
                throw new JamesException(sadFace + " OOPS!!! The description of a todo cannot be empty.");
            }
            task = new ToDoTask(description[1]);
        } else if (taskType == Parser.TaskType.DEADLINE) {
            String[] parsed = input.split("deadline ");
            if (parsed.length == 1) {
                throw new JamesException(sadFace + " OOPS!!! The description of a deadline cannot be empty.");
            }
            String[] param = parsed[1].split(" /by ");
            if (param.length == 1) {
                throw new JamesException(sadFace + " OOPS!!! The time of a deadline cannot be empty.");
            }
            String description = param[0];
            String time = param[1];
            task = new DeadlineTask(description, time);
        } else if (taskType == Parser.TaskType.EVENT) {
            String[] parsed = input.split("event ")[1].split(" /from ");
            if (parsed.length == 1) {
                throw new JamesException(sadFace + " OOPS!!! The description of a event cannot be empty.");
            }
            String description = parsed[0];
            String[] params = parsed[1].split(" /to ");
            if (params.length == 1) {
                throw new JamesException(sadFace + " OOPS!!! The time of a event cannot be empty.");
            }
            String startTime = params[0];
            String endTime = params[1];
            task = new EventTask(description, startTime, endTime);
        } else {
            throw new JamesException(sadFace + " OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return task;
    }

    /**
     * Parses the user input and prints the corresponding output.
     *
     * @param tasks TaskList object.
     * @param input User input.
     */
    public String parse(TaskList tasks, String input) {
        String[] inputParts = input.split(" ");
        String command = inputParts[0];
        Integer taskIdx;
        String output = "";

        switch (command) {
        case "bye":
            output += "Bye. Hope to see you again soon!";
            break;
        case "list":
            output += "Here are the tasks in your list:\n" + tasks;
            break;
        case "unmark":
            taskIdx = Integer.parseInt(inputParts[1]);
            tasks.unmarkTask(taskIdx - 1);
            output += "OK! I've marked this task as not done yet:\n" + tasks.getTask(taskIdx - 1);
            break;
        case "mark":
            taskIdx = Integer.parseInt(inputParts[1]);
            tasks.markTask(taskIdx - 1);
            output += "Nice! I've marked this task as done:\n" + tasks.getTask(taskIdx - 1);
            break;
        case "delete":
            taskIdx = Integer.parseInt(inputParts[1]);
            output += "Noted. I've removed this task:\n" + tasks.getTask(taskIdx - 1)
                    + "\nNow you have " + tasks.size() + " tasks in the list.";
            tasks.deleteTask(taskIdx - 1);
            break;
        case "find":
            String keyword = inputParts[1];
            output += "Here are the matching tasks in your list:\n" + tasks.find(keyword);
            break;
        default:
            try {
                Task task = parseTask(input);
                tasks.addTask(task);
                output += "Got it. I've added this task:\n" + task + "\n" + line + "\n"
                        + "Now you have " + tasks.size() + " tasks in the list.";
            } catch (JamesException e) {
                output += e.getMessage();
            }
            break;
        }
        return output;
    }


}
