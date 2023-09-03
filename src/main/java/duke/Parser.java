package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A class that deals with making sense of the input.
 */
public class Parser {

    public boolean isExit = false;

    /**
     * Handles the different inputs by checking their first word.
     *
     * @param input the input that user entered
     * @param taskList an arraylist of tasks
     * @param isLoading a flag to check if the application is restoring
     */
    public void handleInput(String input, TaskList taskList, Boolean isLoading) {
        if (input.equals("bye")) {
            isExit = true;
            return;
        }
        if (input.startsWith("mark") || input.startsWith("unmark ")) {
            this.handleMark(input, taskList, isLoading);
            return;
        }
        if (input.startsWith("todo")) {
            this.handleTodo(input, taskList, isLoading);
            return;
        }
        if (input.startsWith("deadline")) {
            this.handleDeadline(input, taskList, isLoading);
            return;
        }
        if (input.startsWith("event")) {
            this.handleEvent(input, taskList, isLoading);
            return;
        }
        if (input.startsWith("delete")) {
            this.handleDelete(input, taskList);
            return;
        }
        if (input.equals("list")) {
            this.handleList(taskList);
            return;
        }
        if (input.startsWith("find")) {
            String[] arr = input.split("find ");
            System.out.println("arr: " + arr.length);
            if (arr.length == 1 || arr.length == 0) {
                Ui.matchExcept();
            } else {
                this.findMatching(arr[1], taskList);
            }
            return;
        }
        if (!isLoading) {
            Ui.printError();
        }
    }

    /**
     * Handles the List input.
     *
     * @param taskList an array of tasks
     */
    public void handleList(TaskList taskList) {
        if (taskList.size() == 0) {
            Ui.printEmptyList();
            return;
        }
        Ui.printList(taskList);
    }

    /**
     * Handles inputs that start with mark and unmark.
     *
     * @param input the input that user entered
     * @param taskList an array of tasks
     * @param isLoading a flag to check if the application is restoring
     */

    public void handleMark(String input, TaskList taskList, Boolean isLoading) {
        String[] parts = input.split(" ");
        if (parts.length == 2) {
            try {
                int index = Integer.parseInt(parts[1]);
                Task thisTask = taskList.getTask(index);
                taskList.getTask(index).toggleDone();
                Storage.save(taskList); // save in file
                if (thisTask.getDone()) {
                    Ui.printDone(thisTask);
                } else {
                    Ui.printNotDone(thisTask);
                }
            } catch (IndexOutOfBoundsException ex) {
                Ui.outOfBounds();
            } catch (NumberFormatException e) {
                Ui.numberFormat();
            }
        }
    }

    /**
     * Handles inputs that start with delete.
     *
     * @param input the input that user entered
     * @param taskList an array of tasks
     */

    public void handleDelete(String input, TaskList taskList) {
        String[] parts1 = input.split(" ");
        int index = Integer.parseInt(parts1[1]);
        String deleted = String.valueOf(taskList.getTask(index - 1));
        taskList.remove(index - 1);
        Storage.save(taskList); // save in file
        Ui.removeTask(deleted, taskList);
    }

    /**
     * Handles inputs that start with todo.
     *
     * @param input the input that user entered
     * @param taskList an array of tasks
     * @param isLoading a flag to check if the application is restoring
     */
    public void handleTodo(String input, TaskList taskList, Boolean isLoading) {
        String[] arr0 = input.split("todo ");
        if (arr0.length == 1) {
            Ui.toDoExcept();
        } else {
            Task todo = new Todo(arr0[1], input);
            AddTask(todo, taskList, isLoading);
        }
    }

    /**
     * Handles inputs that start with event.
     *
     * @param input the input that user entered
     * @param taskList an array of tasks
     * @param isLoading a flag to check if the application is restoring
     */

    public void handleEvent(String input, TaskList taskList, Boolean isLoading) {
        try {
            String[] arr1 = input.split("/from "); // [0]: event + name, [1]: timeframe
            String[] arr2 = arr1[1].split("/to "); // [0] from:..., [1] to:...
            String[] arr3 = arr1[0].split("event ");
            Task event = new Event(arr3[1], arr2[0], arr2[1], input);
            AddTask(event, taskList, isLoading);
        } catch (ArrayIndexOutOfBoundsException ex) {
            Ui.eventExcept();
        }
    }

    /**
     * Handles inputs that start with deadline.
     *
     * @param input the input that user entered
     * @param taskList an array of tasks
     * @param isLoading a flag to check if the application is restoring
     */

    public void handleDeadline(String input, TaskList taskList, Boolean isLoading) {
        try {
            String[] arr1 = input.split("/by "); // 0: deadline + name , 1: date
            String[] arr2 = arr1[0].split("deadline ");
            String date = arr1[1];
            LocalDateTime formattedDate = dateFormatter(date);
            Task deadline = new Deadline(arr2[1], formattedDate, input);
            AddTask(deadline, taskList, isLoading);
        } catch (ArrayIndexOutOfBoundsException ex) {
            Ui.deadlineExcept();
        } catch (DateTimeParseException ex) {
            Ui.dateFormatExcept();
        }
    }

    /**
     * Converts the string date and time to a LocalDateTime object.
     *
     * @param dateTime a String extracted from the deadline input
     * @return LocalDateTime that allows the reformatting of date and time
     * @throws DateTimeParseException an exception when the format of date is invalid
     */

    public static LocalDateTime dateFormatter(String dateTime) throws DateTimeParseException {
            String[] inputs = dateTime.split(" ");
            DateTimeFormatter formatter;
            if (inputs.length == 2) {
                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                return LocalDateTime.parse(dateTime, formatter);
            } else {
                return null;
            }
        }

    /**
     * Add the individual task into the taskList and saves them into a file.
     *
     * @param task includes event, todo and deadline
     * @param taskList an array of tasks
     * @param isLoading a flag to check if the application is restoring
     */

    public void AddTask(Task task, TaskList taskList, Boolean isLoading) {
        taskList.add(task);
        Storage.save(taskList);
        if (!isLoading) {
            Ui.printAddTask(task, taskList);
        }
    }

    /**
     * Find tasks based on a target string that the user entered.
     *
     * @param keyword the target string followed by "find"
     * @param taskList an array of tasks
     */

    public void findMatching(String keyword, TaskList taskList) {
        TaskList newList = new TaskList();
        if (taskList.size() == 0) {
            System.out.println("list is empty!");
            return;
        }
        for (int i = 1; i <= taskList.size(); i ++) {
            Task matchTask = taskList.getTask(i);
            System.out.println("name: " + matchTask.getName());
            System.out.println("keyword: " + keyword);
            System.out.println("contains: " + matchTask.getName().contains(keyword));
            if (matchTask.getName().contains(keyword)) {
                newList.add(matchTask);
            }
        }
        Ui.printMatching(newList);
    }
}