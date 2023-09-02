package duke;

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
        Ui.printError();
    }

    public void handleList(TaskList taskList) {
        if (taskList.size() == 0) {
            Ui.printEmptyList();
            return;
        }
        Ui.printList(taskList);
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
                    Ui.printDone(thisTask);
                } else {
                    Ui.printNotDone(thisTask);
                }
            } catch (IndexOutOfBoundsException ex) {
                Ui.OutofBounds();
            } catch (NumberFormatException e) {
                Ui.NumberFormat();
            }
        }
    }

    public void handleDelete(String input, TaskList taskList) {
        String[] parts1 = input.split(" ");
        int index = Integer.parseInt(parts1[1]);
        String deleted = String.valueOf(taskList.getTask(index - 1));
        taskList.remove(index - 1);
        Storage.save(taskList); // save in file
        Ui.removeTask(deleted, taskList);
    }

    public void handleTodo(String input, TaskList taskList, Boolean isLoading) {
        String[] arr0 = input.split("todo ");
        if (arr0.length == 1) {
            Ui.ToDoExcept();
        } else {
            Task todo = new Todo(arr0[1], input);
            AddTask(todo, taskList, isLoading);
        }
    }

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
            Ui.DateFormatExcept();
        }
    }

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

    public void AddTask(Task task, TaskList taskList, Boolean isLoading) {
        taskList.add(task);
        Storage.save(taskList);
        if (!isLoading) {
            Ui.printAddTask(task, taskList);
        }
    }
}