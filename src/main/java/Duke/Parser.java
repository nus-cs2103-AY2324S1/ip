package Duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    public boolean isExit = false;

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
            System.out.println("firstload: " + isLoading);
            this.handleTodo(input, taskList, isLoading);
            return;
        }
        if (input.startsWith("deadline")) {
            System.out.println("a");
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
        Ui.printError();
    }

    public void handleList(TaskList taskList) {
        if (taskList.size() == 0) {
            System.out.println("There is currently no items in the list");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= taskList.size(); i++) {
            String task = String.valueOf(taskList.getTask(i));
            System.out.println(i + ". " + task);
        }
    }

    public void handleMark(String input, TaskList taskList, Boolean isLoading) {
        String[] parts = input.split(" ");
        if (parts.length == 2) {
            try {
                int index = Integer.parseInt(parts[1]);
                Task thisTask = taskList.getTask(index);
                taskList.getTask(index).toggleDone();
                Storage.save(taskList); // save in file
                if (thisTask.getDone()) {
                    System.out.println("Nice! I've marked this task as done:" + "\n" + thisTask);
                } else {
                    System.out.println("OK, I've marked this task as not done yet:" + "\n" + thisTask);
                }
            } catch (IndexOutOfBoundsException ex) {
                System.out.println("IndexOutOfBounds");
            } catch (NumberFormatException e) {
                System.out.println("NumberFormatException");
            }
        }
    }

    public void handleDelete(String input, TaskList taskList) {
        String[] parts1 = input.split(" ");
        int index = Integer.parseInt(parts1[1]);
        String deleted = String.valueOf(taskList.getTask(index - 1));
        taskList.remove(index - 1);
        Storage.save(taskList); // save in file
        System.out.println("Noted. I've removed this task:\n" + deleted + "\n"
                + "Now you have " + taskList.size() + " tasks in the list.");
    }

    public void handleTodo(String input, TaskList taskList, Boolean isLoading) {
        String[] arr0 = input.split("todo ");
        if (arr0.length == 1) {
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
        } else {
            Task todo = new Todo(arr0[1], input);
            AddTask(todo, taskList, isLoading);
        }
    }

    public void handleEvent(String input, TaskList taskList, Boolean isLoading) {
        String[] arr1 = input.split("/from "); // [0]: event + name, [1]: timeframe
        String[] arr2 = arr1[1].split("/to "); // [0] from:..., [1] to:...
        String[] arr3 = arr1[0].split("event ");
        Task event = new Event(arr3[1], arr2[0], arr2[1], input);
        AddTask(event, taskList, isLoading);
    }

    public void handleDeadline(String input, TaskList taskList, Boolean isLoading) {
        String[] arr1 = input.split("/by "); // 0: deadline + name , 1: date
        String[] arr2 = arr1[0].split("deadline ");
        String date = arr1[1];
        LocalDateTime formattedDate = dateFormatter(date);
        if (formattedDate == null) {
            System.out.println("incorrect date format");
            return;
        }
        Task deadline = new Deadline(arr2[1], formattedDate, input);
        AddTask(deadline, taskList, isLoading);
    }

    ;

    public static LocalDateTime dateFormatter(String dateTime) {
        try {
            String[] inputs = dateTime.split(" ");
            DateTimeFormatter formatter;
            if (inputs.length == 2) {
                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                return LocalDateTime.parse(dateTime, formatter);
            } else {
                return null;
            }
        } catch (DateTimeParseException ex) {
            System.out.println("Invalid Date Format");
            return null;
        }
    }

    public void AddTask(Task task, TaskList taskList, Boolean isLoading) {
        taskList.add(task);
        Storage.save(taskList);
        System.out.println("isloading: " + isLoading);
        if (!isLoading) {
            System.out.println("a");
            Ui.printAddTask(task, taskList);
        }
    }
}

    // for deadline, event and todo, create a task and pass it in AddTask(Duke.Task task, isLoading) function
    // which handles both isRestoring and adding task
    // hence a flag is necessary to indicate whether the msg gets printed out
    // for the loading of task, simply do handleInput(input, tasks, true)
    // in the function for addtask, do get it from the Duke.TaskList class for add function (eg taskList.add(Duke.Task)
    // for list, can just use ui to print it out
