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
                storage.saveTasksToFile(tasks);
                return UiGUI.goodbye();

            case "list":
                return UiGUI.tasksInList(this.tasks);

            case "mark":
                int number = Integer.parseInt(taskDetails);
                item = tasks.get(number);
                item.set();
                storage.saveTasksToFile(tasks);
                return UiGUI.taskDone(item);

            case "unmark":
                int numero = Integer.parseInt(taskDetails);
                item = tasks.get(numero);
                item.unset();
                storage.saveTasksToFile(tasks);
                return UiGUI.taskUndone(item);

            case "delete":
                int numbero = Integer.parseInt(taskDetails);
                item = tasks.get(numbero);
                tasks.delete(numbero);
                storage.saveTasksToFile(tasks);
                return UiGUI.taskDelete(item, tasks);

            case "todo":
                if (!taskDetails.isEmpty()) {
                    ToDo t = new ToDo(taskDetails);
                    tasks.add(t);
                    storage.saveTasksToFile(tasks);
                    return UiGUI.taskAdd(t, tasks);
                } else {

                    return UiGUI.showError("todo");

                }

            case "deadline":
                String[] toBeSplit = taskDetails.split(" /by ");
                if (toBeSplit.length != 2) {
                    throw new DukeException("Invalid deadline format. Use 'deadline <description> /by <date/time>'", "invalid_date");
                }

                String deadlineDescription = toBeSplit[0];
                String deadlineDate = toBeSplit[1];
                Deadline d;

                if (deadlineDate.contains(" ")) {
                    d = new Deadline(deadlineDescription, LocalDateTime.parse(deadlineDate, DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm")));
                } else {
                    d = new Deadline(deadlineDescription, LocalDate.parse(deadlineDate, DateTimeFormatter.ofPattern("yyyy/MM/dd")));
                }

                tasks.add(d);
                storage.saveTasksToFile(tasks);
                return UiGUI.taskAdd(d, tasks);

            case "event":
                String[] to_Split = taskDetails.split(" /from ");
                String[] second_Split = to_Split[1].split(" /to ");
                if (second_Split.length != 2) {
                    return UiGUI.showError("event");
                }

                String eventDescription = to_Split[0];
                String eventStartDate = second_Split[0];
                String eventEndDate = second_Split[1];

                Event e = new Event(eventDescription, eventStartDate, eventEndDate);
                tasks.add(e);
                storage.saveTasksToFile(tasks);
                return UiGUI.taskAdd(e, tasks);

            case "find":
                if (!taskDetails.isEmpty()) {
                    return UiGUI.printMatchingTasks(tasks, taskDetails);
                } else {
                    return UiGUI.showError("find");
                }
            case "remind":
                int numberOfDays;
                try {
                    numberOfDays = Integer.parseInt(taskDetails);
                    if (numberOfDays < 0) {
                        return UiGUI.showError("remind");
                    }
                } catch (NumberFormatException x) {
                    return UiGUI.showError("remind");
                }

                return UiGUI.remind(tasks, numberOfDays);


            default:
                throw new DukeException("Unrecognized command: " + command, "unrecognized_command");
            }
        }
        catch (DukeException e){
            return UiGUI.showExError(e.getMessage());
        }
        catch (NumberFormatException e) {
            // Handle number format exceptions and return an error message to the GUI.
            return UiGUI.showExError("Invalid input format: Please enter a valid number.");
        }

    }
}