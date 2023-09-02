import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.time.format.DateTimeFormatter;
public class Parser {
    public static String parseCommand(String command, TaskList taskList) throws DukeException {
        String[] tokens = command.split(" ", 2);
        String action = tokens[0].trim().toLowerCase();

        switch (action) {
            case "list":
                return generateListResponse(taskList);
            case "delete":
                return generateDeleteResponse(tokens, taskList);
            case "mark":
                return generateMarkResponse(tokens, taskList);
            case "unmark":
                return generateUnmarkResponse(tokens, taskList);
            case "todo":
                return generateTodoResponse(tokens, taskList);
            case "deadline":
                return generateDeadlineResponse(tokens, taskList);
            case "event":
                return generateEventResponse(tokens, taskList);
            default:
                throw new DukeException("Sorry, I don't understand what that means.");
        }
    }

    private static String generateListResponse(TaskList taskList) {
        List<Task> tasks = taskList.getTasks();
        if (tasks.isEmpty()) {
            return "You currently have no tasks in your list.";
        }

        StringBuilder response = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            response.append((i + 1)).append(". ").append(tasks.get(i).toString()).append("\n");
        }
        return response.toString();
    }

    private static String generateDeleteResponse(String[] tokens, TaskList taskList) throws DukeException {
        if (tokens.length < 2) {
            throw new DukeException("Please specify which task you wish to delete.");
        }

        int taskIndex;
        try {
            taskIndex = Integer.parseInt(tokens[1].trim()) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Please insert a valid integer.");
        }

        if (taskIndex < 0 || taskIndex >= taskList.getTasks().size()) {
            throw new DukeException("The given task does not exist.");
        }

        Task deletedTask = taskList.getTasks().get(taskIndex);
        taskList.removeTask(taskIndex);

        return generateTaskDeletedResponse(deletedTask, taskList);
    }

    private static String generateTaskDeletedResponse(Task task, TaskList taskList) {
        StringBuilder response = new StringBuilder("Noted. I've removed this task:\n\t");
        response.append(task.toString()).append("\n");
        response.append("Now you have ").append(taskList.getTasks().size()).append(" tasks in the list.");
        return response.toString();
    }

    private static String generateMarkResponse(String[] tokens, TaskList taskList) throws DukeException {
        if (tokens.length < 2) {
            throw new DukeException("Please specify which task you wish to mark.");
        }

        int taskIndex;
        try {
            taskIndex = Integer.parseInt(tokens[1].trim()) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Please insert a valid integer.");
        }

        if (taskIndex < 0 || taskIndex >= taskList.getTasks().size()) {
            throw new DukeException("The given task does not exist.");
        }

        Task task = taskList.getTasks().get(taskIndex);
        taskList.markTaskAsDone(taskIndex);

        return generateTaskMarkedResponse(task, true);
    }

    private static String generateUnmarkResponse(String[] tokens, TaskList taskList) throws DukeException {
        if (tokens.length < 2) {
            throw new DukeException("Please specify which task you wish to unmark.");
        }

        int taskIndex;
        try {
            taskIndex = Integer.parseInt(tokens[1].trim()) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Please insert a valid integer.");
        }

        if (taskIndex < 0 || taskIndex >= taskList.getTasks().size()) {
            throw new DukeException("The given task does not exist.");
        }

        Task task = taskList.getTasks().get(taskIndex);
        taskList.unmarkTaskAsDone(taskIndex);

        return generateTaskMarkedResponse(task, false);
    }

    private static String generateTaskMarkedResponse(Task task, boolean isDone) {
        String status = isDone ? "done" : "not done yet";
        return "OK, I've marked this task as " + status + ":\n\t" + task.toString();
    }

    private static String generateTodoResponse(String[] tokens, TaskList taskList) throws DukeException {
        if (tokens.length < 2) {
            throw new DukeException("The description of todo cannot be empty.");
        }

        String taskDescription = tokens[1].trim();
        Task newTask = new ToDo(taskDescription, "0");
        taskList.addTask(newTask);

        return generateTaskAddedResponse(newTask, taskList);
    }

    private static String generateDeadlineResponse(String[] tokens, TaskList taskList) throws DukeException {
        if (tokens.length < 2) {
            throw new DukeException("The description of deadline cannot be empty.");
        }

        String[] parts = tokens[1].split("/by", 2);
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new DukeException("Please provide a task description and deadline.");
        }

        String taskDescription = parts[0].trim();
        String dueByString = parts[1].trim();
        LocalDate dueBy = LocalDate.parse(dueByString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        Task newTask = new Deadline(taskDescription, dueBy, "0");
        taskList.addTask(newTask);

        return generateTaskAddedResponse(newTask, taskList);
    }

    private static String generateEventResponse(String[] tokens, TaskList taskList) throws DukeException {
        if (tokens.length < 2) {
            throw new DukeException("The description of event cannot be empty.");
        }

        String[] parts = tokens[1].split("/from", 2);
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new DukeException("Please provide a task description and event timing.");
        }

        String[] timingParts = parts[1].split("/to", 2);
        if (timingParts.length < 2 || timingParts[0].trim().isEmpty() || timingParts[1].trim().isEmpty()) {
            throw new DukeException("Please provide both the start and end timings for the event.");
        }

        String taskDescription = parts[0].trim();
        String startTimingString = timingParts[0].trim();
        String endTimingString = timingParts[1].trim();

        try {
            LocalDate startTiming = LocalDate.parse(startTimingString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            LocalDate endTiming = LocalDate.parse(endTimingString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            Task newTask = new Event(taskDescription, startTiming, endTiming, "0");
            taskList.addTask(newTask);

            return generateTaskAddedResponse(newTask, taskList);

        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter date in dd/MM/yyyy format.");
        }

    }

    private static LocalDate parseDate(String dateString) throws DukeException {
        DateTimeFormatter[] formatters = {
                DateTimeFormatter.ofPattern("yyyy-MM-dd"),
                DateTimeFormatter.ofPattern("dd/MM/yyyy")
        };

        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalDate.parse(dateString, formatter);
            } catch (DateTimeParseException ignored) {
                throw new DukeException("Invalid date format. Please have dates in dd/MM/yyyy format.");
            }
        }

        return null; // Return null if date couldn't be parsed
    }

    private static String generateTaskAddedResponse(Task task, TaskList taskList) {
        StringBuilder response = new StringBuilder("Got it. I've added this task:\n\t");
        response.append(task.toString()).append("\n");
        response.append("Now you have ").append(taskList.getTasks().size()).append(" tasks in the list.");
        return response.toString();
    }


}

