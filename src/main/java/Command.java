import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

public class Command {
    private final Backend backend;
    private final SystemText systemText;
    private final TaskList taskList;

    public Command(Backend backend, SystemText systemText, TaskList taskList) {
        this.backend = backend;
        this.systemText = systemText;
        this.taskList = taskList;
    }

    // Add to do task
    public String handleToDo(String input) {
        try {
            String[] keyword = input.split(" ", 2);
            if (keyword.length == 1 || keyword[1].equals("")) {
                throw new EmptyTaskException();
            }
            // Create new To Do task from input
            ToDo task = new ToDo(false, keyword[1]);
            // Add new To Do into task list
            this.taskList.addTask(task);
            // Save new task into backend
            this.backend.saveTask(task);
            // Return system message to inform action
            return this.systemText.printAddTask(task);
        } catch (EmptyTaskException e) {
            return systemText.printError(e);
        }
    }

    // Add deadline task
    public String handleDeadline(String input) {
        try {
            // Break input by "/by"
            String[] firstSplit = input.split("/by");
            // if user did not put input anything/left a space behind '/by'
            if (firstSplit.length == 1 || firstSplit[1].equals(" ")) {
                throw new MissingTimeException();
            }
            String[] secondSplit = firstSplit[0].split(" ", 2);
            if (secondSplit.length == 1 || secondSplit[1].equals("")) {
                throw new EmptyTaskException();
            }
            String name = secondSplit[1];
            String[] thirdSplit = firstSplit[1].split(" ");
            if (thirdSplit.length == 2 || thirdSplit[2].equals("")) {
                throw new InvalidDeadlineException();
            }
            int hour = Integer.parseInt(thirdSplit[2].substring(0, 2));
            int minute = Integer.parseInt(thirdSplit[2].substring(2));
            LocalDate date = LocalDate.parse(thirdSplit[1]);
            LocalTime time = LocalTime.of(hour, minute);
            LocalDateTime deadline = LocalDateTime.of(date, time);
            // Create new Deadline task from variables
            Deadline task = new Deadline(false, name, deadline);
            // Add new Deadline into task list
            this.taskList.addTask(task);
            // Save new task into backend
            this.backend.saveTask(task);
            // Return system message to inform action
            return this.systemText.printAddTask(task);
        } catch (MissingTimeException | EmptyTaskException | InvalidDeadlineException e) {
            return systemText.printError(e);
        } catch (NumberFormatException | DateTimeException e) {
            // when time input is incorrect
            try {
                throw new InvalidDeadlineException();
            } catch (InvalidDeadlineException i) {
                return systemText.printError(i);
            }
        }
    }

    // Add event task
    public String handleEvent(String input) {
        try {
            // Break input down into variables
            String[] firstSplit = input.split("/from");
            if (firstSplit.length == 1 || firstSplit[1].equals(" ")) {
                throw new MissingTimeException();
            }
            String[] secondSplit = firstSplit[0].split(" ", 2);
            if (secondSplit.length == 1 || secondSplit[1].equals("")) {
                throw new EmptyTaskException();
            }
            String name = secondSplit[1];
            String[] thirdSplit = firstSplit[1].split("/to");
            if (thirdSplit.length == 1 || thirdSplit[1].equals(" ") || thirdSplit[0].equals(" ")) {
                throw new MissingTimeException();
            }

            String[] startSplit = thirdSplit[0].split(" ");
            if (startSplit.length == 2 || startSplit[2].equals("")) {
                throw new InvalidEventException();
            }
            LocalDate startDate = LocalDate.parse(startSplit[1]);
            int startHour = Integer.parseInt(startSplit[2].substring(0, 2));
            int startMinute = Integer.parseInt(startSplit[2].substring(2));
            LocalTime startTime = LocalTime.of(startHour, startMinute);
            LocalDateTime start = LocalDateTime.of(startDate, startTime);

            String[] endSplit = thirdSplit[1].split(" ");
            if (endSplit.length == 2 || endSplit[2].equals("")) {
                throw new InvalidEventException();
            }
            LocalDate endDate = LocalDate.parse(endSplit[1]);
            int endHour = Integer.parseInt(endSplit[2].substring(0, 2));
            int endMinute = Integer.parseInt(endSplit[2].substring(2));
            LocalTime endTime = LocalTime.of(endHour, endMinute);
            LocalDateTime end = LocalDateTime.of(endDate, endTime);

            // Create new Event task from variables
            Event task = new Event(false, name, start, end);
            // Add new Event into task list
            this.taskList.addTask(task);
            // Save new task into backend
            this.backend.saveTask(task);
            // Return system message to inform action
            return this.systemText.printAddTask(task);
        } catch (MissingTimeException | EmptyTaskException e) {
            return systemText.printError(e);
        } catch (NumberFormatException | DateTimeException | InvalidEventException e) {
            // when time input is incorrect
            try {
                throw new InvalidEventException();
            } catch (InvalidEventException i) {
                return systemText.printError(i);
            }
        }
    }

    // Mark task
    public String handleMark(String input) {
        try {
            String[] keyword = input.split(" ");
            if (keyword.length == 1 || keyword[1].equals("")) {
                throw new EmptyTaskException();
            }
            int taskNumber = Integer.parseInt(keyword[1]);
            Task task = this.taskList.getTask(taskNumber);
            // Update backend, important to update backend first!
            this.backend.updateTask(task, 1);
            // Mark task as done
            task.mark();
            // Return system message to inform action
            return this.systemText.printUpdateTask(task);
        } catch (EmptyTaskException | InvalidTaskException e) {
            return systemText.printError(e);
        }
    }

    // Un-mark task
    public String handleUnMark(String input) {
        try {
            String[] keyword = input.split(" ");
            if (keyword.length == 1 || keyword[1].equals("")) {
                throw new EmptyTaskException();
            }
            int taskNumber = Integer.parseInt(keyword[1]);
            Task task = this.taskList.getTask(taskNumber);
            // Update backend
            this.backend.updateTask(task, 0);
            // un-mark task
            task.unMark();
            // Return system message to inform action
            return this.systemText.printUpdateTask(task);
        } catch (EmptyTaskException | InvalidTaskException e) {
            return systemText.printError(e);
        }
    }

    public String handleDelete(String input) {
        try {
            // Get task from input
            String[] keyword = input.split(" ");
            if (keyword.length == 1 || keyword[1].equals("")) {
                throw new EmptyTaskException();
            }
            int taskNumber = Integer.parseInt(keyword[1]);
            Task task = this.taskList.getTask(taskNumber);

            // Delete task from backend
            this.backend.deleteTask(task);

            // Delete task from task list
            this.taskList.deleteTask(task);

            // Return system message to inform action
            return this.systemText.printDeleteTask(task);
        } catch (EmptyTaskException | InvalidTaskException e) {
            return systemText.printError(e);
        }
    }
}
