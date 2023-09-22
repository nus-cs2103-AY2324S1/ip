package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A class that deals with making sense of the input.
 */
public class Parser {

    /**
     * Handles the different inputs by checking their first word.
     *
     * @param input the input that user entered
     * @param taskList an arraylist of tasks
     * @param isLoading a flag to check if the application is restoring
     */
    public static String handleInput(String input, TaskList taskList, Boolean isLoading) {
        boolean isDone = false;
        if (isLoading) {
            String flag = input.substring(input.length() - 1);
            isDone = flag.equals("1");
            input = input.substring(0, input.length() - 1);
        }
        if (input.equals("bye")) {
            return Ui.printBYE();
        }
        if (input.startsWith("mark") || input.startsWith("unmark")) {
            return handleMark(input, taskList);
        }
        if (input.startsWith("todo")) {
            return handleTodo(input, taskList, isLoading, isDone);
        }
        if (input.startsWith("deadline")) {
            return handleDeadline(input, taskList, isLoading, isDone);
        }
        if (input.startsWith("event")) {
            return handleEvent(input, taskList, isLoading, isDone);
        }
        if (input.startsWith("delete")) {
            return handleDelete(input, taskList);
        }
        if (input.equals("list")) {
            return handleList(taskList);
        }
        if (input.startsWith("find")) {
            return handleFind(input, taskList);
        }
        return Ui.printError();
    }

    /**
     * Handles input that starts with find.
     *
     * @param input input that contains the find command word and the keyword
     * @param taskList an array of tasks
     * @return tasks that contains the prefix that the user entered
     */

    private static String handleFind(String input, TaskList taskList) {
        String[] arr = input.split("find ");
        System.out.println("arr: " + arr.length);
        if (arr.length == 1 || arr.length == 0) {
            return Ui.matchExcept();
        } else {
            return findMatching(arr[1], taskList);
        }
    }

    /**
     * Handles the List input.
     *
     * @param taskList an array of tasks
     */
    public static String handleList(TaskList taskList) {
        if (taskList.size() == 0) {
            return Ui.printEmptyList();
        }
        return Ui.printList(taskList);
    }

    /**
     * Handles inputs that start with mark and unmark.
     *
     * @param input the input that user entered
     * @param taskList an array of tasks
     */

    public static String handleMark(String input, TaskList taskList) {
        assert !input.equals("") : "input shouldn't be empty string!";
        String[] parts = input.split(" ");
        if (parts.length == 2) {
            try {
                int index = Integer.parseInt(parts[1]);
                Task thisTask = taskList.getTask(index - 1);
                if (parts[0].equals("mark")) {
                    thisTask.setDone();
                    Storage.save(taskList);
                    return Ui.printDone(thisTask);
                }
                if (parts[0].equals("unmark")) {
                    thisTask.setNotDone();
                    Storage.save(taskList);
                    return Ui.printNotDone(thisTask);
                }
            } catch (IndexOutOfBoundsException ex) {
                Ui.outOfBounds();
            } catch (NumberFormatException e) {
                Ui.numberFormat();
            }
        }
        return Ui.markExcept();
    }

    /**
     * Handles inputs that start with delete.
     *
     * @param input the input that user entered
     * @param taskList an array of tasks
     */

    public static String handleDelete(String input, TaskList taskList) {
        assert !input.equals("") : "inputs shouldn't be empty string!";
        String[] parts1 = input.split(" ");
        if (taskList.size() == 0) {
            return Ui.taskEmpty();
        }
        try {
        int index = Integer.parseInt(parts1[1]);
        if (index < 0 || index > taskList.size()) {
            return Ui.invalidNum();
        }
        String deleted = String.valueOf(taskList.getTask(index - 1));
        taskList.remove(index - 1);
        Storage.save(taskList); // save in file
        return Ui.removeTask(deleted, taskList);
        } catch (NumberFormatException ex) {
            return Ui.numberFormat();
        }
    }

    /**
     * Handles inputs that start with todo.
     *
     * @param input the input that user entered
     * @param taskList an array of tasks
     * @param isLoading a flag to check if the application is restoring
     */
    public static String handleTodo(String input, TaskList taskList, Boolean isLoading, Boolean isDone) {
        assert !input.equals("") : "inputs shouldn't be empty string!";
        String[] arr0 = input.split("todo ");
        if (arr0.length == 1) {
            return Ui.toDoExcept();
        } else {
            Task todo = new Todo(arr0[1], input);
            return AddTask(todo, taskList, isLoading, isDone);
        }
    }

    /**
     * Handles inputs that start with event.
     *
     * @param input the input that user entered
     * @param taskList an array of tasks
     * @param isLoading a flag to check if the application is restoring
     */

    public static String handleEvent(String input, TaskList taskList, Boolean isLoading, Boolean isDone) {
        try {
            String[] arr1 = input.split("/from "); // [0]: event + name, [1]: timeframe
            String[] arr2 = arr1[1].split("/to "); // [0] from:..., [1] to:...
            String[] arr3 = arr1[0].split("event ");
            Task event = new Event(arr3[1], arr2[0], arr2[1], input);
            return AddTask(event, taskList, isLoading, isDone);
        } catch (ArrayIndexOutOfBoundsException ex) {
            return Ui.eventExcept();
        }
    }

    /**
     * Handles inputs that start with deadline.
     *
     * @param input the input that user entered
     * @param taskList an array of tasks
     * @param isLoading a flag to check if the application is restoring
     */

    public static String handleDeadline(String input, TaskList taskList, Boolean isLoading, Boolean isDone) {
        assert !input.equals("") : "inputs shouldn't be empty string!";
        try {
            String[] arr1 = input.split("/by "); // 0: deadline + name , 1: date
            String[] arr2 = arr1[0].split("deadline ");
            String date = arr1[1];
            LocalDateTime formattedDate = dateFormatter(date);
            if (formattedDate == null) {
                return Ui.dateFormatExcept();
            }
            Task deadline = new Deadline(arr2[1], formattedDate, input);
            return AddTask(deadline, taskList, isLoading, isDone);
        } catch (ArrayIndexOutOfBoundsException ex) {
            return Ui.deadlineExcept();
        } catch (DateTimeParseException ex) {
            return Ui.dateFormatExcept();
        }
    }

    /**
     * Converts the string date and time to a LocalDateTime object.
     *
     * @param dateTime a String extracted from the deadline input
     * @return LocalDateTime that allows the reformatting of date and time
     * @throws DateTimeParseException an exception when the format of date is invalid
     */

    public static LocalDateTime dateFormatter(String dateTime) {
        String[] inputs = dateTime.split(" ");
        DateTimeFormatter formatter;
        if (inputs.length == 2) {
            try {
                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                return LocalDateTime.parse(dateTime, formatter);
            } catch (DateTimeParseException ex) {
                System.out.println("Wrong Deadline format");
            }
        }
        return null;
    }

    /**
     * Add the individual task into the taskList and saves them into a file.
     *
     * @param task includes event, todo and deadline
     * @param taskList an array of tasks
     * @param isLoading a flag to check if the application is restoring
     */

    public static String AddTask(Task task, TaskList taskList, Boolean isLoading, Boolean isDone) {
        // detect duplicates
        boolean hasDuplicate = false;
        if (taskList.size() != 0) {
            hasDuplicate = CheckDuplicates(task, taskList, false);
        }
        if (hasDuplicate) {
            return Ui.duplicate();
        }
        if (isDone) {
            task.setDone();
        }
        taskList.add(task);
        Storage.save(taskList);
        if (!isLoading) {
            return Ui.printAddTask(task, taskList);
        }
        return "";
    }

    /**
     * Check for identical task string representation in the task list.
     *
     * @param task includes event, todo and deadline
     * @param taskList an array of tasks
     * @param hasDuplicate a flag to check if there is a duplicate task
     */
    private static boolean CheckDuplicates(Task task, TaskList taskList, boolean hasDuplicate) {
            for (int i = 0; i < taskList.size(); i++) {
                String newTask = task.toString();
                String oldTask = taskList.getTask(i).toString();
                if (newTask.equals(oldTask)) {
                     hasDuplicate = true;
                }
            }
        return hasDuplicate;
    }

    /**
     * Find tasks based on a target string that the user entered.
     *
     * @param keyword the target string followed by "find"
     * @param taskList an array of tasks
     */

    public static String findMatching(String keyword, TaskList taskList) {
        TaskList newList = new TaskList();
        if (taskList.size() == 0) {
            return Ui.taskEmpty();
        }
        for (int i = 0; i < taskList.size(); i ++) {
            Task matchTask = taskList.getTask(i);
            if (matchTask.getName().contains(keyword)) {
                newList.add(matchTask);
            }
        }
        return Ui.printMatching(newList);
    }
}