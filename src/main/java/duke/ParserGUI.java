package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ParserGUI {
    private final TaskList tasks;
    private final UiGUI UiGUI;
    private final Storage storage;

    /**
     * Creates a Parser
     *
     * @param tasks   the TaskList
     * @param UiGUI   the ui object
     * @param storage the storage object
     */
    ParserGUI(TaskList tasks, UiGUI UiGUI, Storage storage) {
        this.tasks = tasks;
        this.UiGUI = UiGUI;
        this.storage = storage;
    }

    /**
     * Handles the "bye" command by saving tasks to a file and providing a goodbye message.
     *
     * @return A goodbye message.
     */
    private String handleBye() {
        storage.saveTasksToFile(tasks);
        return UiGUI.goodbye();
    }

    /**
     * Handles the "list" command by displaying tasks in the task list.
     *
     * @return A message displaying the tasks in the task list.
     */
    private String handleList() {
        return UiGUI.tasksInList(this.tasks);
    }

    /**
     * Handles the "mark" command by marking a task as done and updating the storage.
     *
     * @param taskDetails The task number to mark as done.
     * @return A message indicating the task has been marked as done.
     */
    private String handleMark(String taskDetails) {
        int number = Integer.parseInt(taskDetails);
        Task item = tasks.get(number);
        item.set();
        storage.saveTasksToFile(tasks);
        return UiGUI.taskDone(item);
    }

    /**
     * Handles the "unmark" command by marking a task as undone and updating the storage.
     *
     * @param taskDetails The task number to mark as undone.
     * @return A message indicating the task has been marked as undone.
     */
    private String handleUnmark(String taskDetails) {
        int numero = Integer.parseInt(taskDetails);
        Task item = tasks.get(numero);
        item.unset();
        storage.saveTasksToFile(tasks);
        return UiGUI.taskUndone(item);
    }

    /**
     * Handles the "delete" command by deleting a task and updating the storage.
     *
     * @param taskDetails The task number to delete.
     * @return A message indicating the task has been deleted.
     */
    private String handleDelete(String taskDetails) {
        int numbero = Integer.parseInt(taskDetails);
        Task item = tasks.get(numbero);
        tasks.delete(numbero);
        storage.saveTasksToFile(tasks);
        return UiGUI.taskDelete(item, tasks);
    }
    /**
     * Handles the "todo" command by deleting a task and updating the storage.
     *
     * @param taskDetails The task number to delete.
     * @return A message indicating the todo task has been added.
     */
    private String handleTodoTask(String taskDetails) {
        if (!taskDetails.isEmpty()) {
            ToDo t = new ToDo(taskDetails);
            tasks.add(t);
            storage.saveTasksToFile(tasks);
            return UiGUI.taskAdd(t, tasks);
        } else {
            throw new DukeException("Invalid ToDo format. Use 'todo <description>'", "invalid_date");
        }
    }

    /**
     * Handles the "deadline" command by creating a new deadline task, adding it to the task list,
     * saving the tasks to storage, and returning an appropriate response message.
     *
     * @param taskDetails The task details including description and deadline.
     * @return A message indicating the task has been added or an error message.
     */
    private String handleDeadline(String taskDetails) {
        String[] toBeSplit = taskDetails.split(" /by ");

        // Check if the input format is valid
        if (toBeSplit.length != 2) {
            throw new DukeException("Invalid deadline format. Use 'deadline <description> /by YYYY/MM/DD HHmm'", "invalid_date");
        }

        String deadlineDescription = toBeSplit[0];
        String deadlineDate = toBeSplit[1];
        Deadline d;

        // Check if the deadlineDate contains time (hours and minutes)
        if (deadlineDate.contains(" ")) {
            d = new Deadline(deadlineDescription, LocalDateTime.parse(deadlineDate, DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm")));
        } else {
            d = new Deadline(deadlineDescription, LocalDate.parse(deadlineDate, DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        }

        tasks.add(d);
        storage.saveTasksToFile(tasks);
        return UiGUI.taskAdd(d, tasks);
    }

    /**
     * Handles the "event" command by creating a new event task, adding it to the task list,
     * saving the tasks to storage, and returning an appropriate response message.
     *
     * @param taskDetails The task details including description, event start date, and end date.
     * @return A message indicating the task has been added or an error message.
     */
    private String handleEvent(String taskDetails) {
        if (!taskDetails.contains(" /from ") || !taskDetails.contains(" /to ")) {
            throw new DukeException("Invalid Event format. Use 'event <description> /from <DateTime> to <Date Time>'", "invalid_date");
        }
        String[] to_Split = taskDetails.split(" /from ");
        String[] second_Split = to_Split[1].split(" /to ");

        // Check if the input format is valid
        if (second_Split[1].length() < 1 || second_Split[0].length() < 1 || second_Split[0].length() < 1) {
            throw new DukeException("Invalid Event format. Use 'event <description> /from <DateTime> to <Date Time>'", "invalid_date");
        }

        String eventDescription = to_Split[0];
        String eventStartDate = second_Split[0];
        String eventEndDate = second_Split[1];

        Event e = new Event(eventDescription, eventStartDate, eventEndDate);
        tasks.add(e);
        storage.saveTasksToFile(tasks);
        return UiGUI.taskAdd(e, tasks);
    }

    /**
     * Handles the "find" command by searching for tasks that match a given keyword.
     *
     * @param taskDetails The keyword to search for within task descriptions.
     * @return A message displaying matching tasks or an error message.
     */
    private String handleFind(String taskDetails) {
        if (!taskDetails.isEmpty()) {
            return UiGUI.printMatchingTasks(tasks, taskDetails);
        } else {
            return UiGUI.showError("find");
        }
    }

    /**
     * Handles the "remind" command by providing reminders for tasks due within a specified number of days.
     *
     * @param taskDetails The number of days for which to provide reminders.
     * @return A message displaying reminders or an error message.
     */
    private String handleRemind(String taskDetails) {
        int numberOfDays;

        try {
            numberOfDays = Integer.parseInt(taskDetails);

            // Check if the number of days is non-negative
            if (numberOfDays < 0) {
                return UiGUI.showError("remind");
            }
        } catch (NumberFormatException x) {
            return UiGUI.showError("remind");
        }

        return UiGUI.remind(tasks, numberOfDays);
    }

    /**
     * Parses the input given by the user
     *
     * @param userInput the full input string provided by the user
     */
    public String parseInput(String userInput) {
        try {


            // Split the userInput into words
            String[] inputParts = userInput.split("\\s+", 2);

            // The first word is the command
            String command = inputParts[0].toLowerCase().trim();

            // The rest of the input (if any) is the task description or arguments
            String taskDetails = (inputParts.length > 1) ? inputParts[1].trim() : "";

            Task item;

            switch (command) {
            case "bye":
                return handleBye();

            case "list":
                return handleList();

            case "mark":
                return handleMark(taskDetails);

            case "unmark":
                return handleUnmark(taskDetails);

            case "delete":
                return handleDelete(taskDetails);

            case "todo":
                return handleTodoTask(taskDetails);

            case "deadline":
                return handleDeadline(taskDetails);

            case "event":
                return handleEvent(taskDetails);

            case "find":
                return handleFind(taskDetails);

            default:
                throw new DukeException("Unrecognized command: " + command, "unrecognized_command");
            }
        } catch (DukeException e) {
            return UiGUI.showExError(e.getMessage());
        } catch (NumberFormatException e) {
            // Handle number format exceptions and return an error message to the GUI.
            return UiGUI.showExError("Invalid input format: Please enter a valid number.");
        }

    }
}