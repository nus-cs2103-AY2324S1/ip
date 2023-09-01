package chatbot.task;

import chatbot.ChatbotException;
import chatbot.storage.Storage;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class TaskManager {
    private TaskList tasks;
    private Storage storage;

    public TaskManager() {
        this.tasks = new TaskList();
        this.storage = new Storage("./data/chatbot.txt");
        this.tasks = storage.loadFromFile();
    }

    private void addTask(Task task) {
        tasks.add(task);
        storage.saveToFile(tasks);
    }

    public Task getTask(int index) {
        return tasks.getTask(index);
    }


    public void addTodo(String t) throws ChatbotException {
        if (t == null || t.trim().isEmpty()) {
            throw new ChatbotException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        Task task = new Todos(t);
        tasks.add(task);
        storage.saveToFile(tasks);
        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task:");
        System.out.println("     " + task.toString());
        System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }

    public void addDeadlines(String t, String date) throws ChatbotException {
        if (t == null || t.trim().isEmpty()) {
            throw new ChatbotException("☹ OOPS!!! The description of a deadlines cannot be empty.");
        } else if (date == null || date.trim().isEmpty()) {
            throw new ChatbotException("☹ OOPS!!! The date of a deadlines cannot be empty.");
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            LocalDateTime dateTime = LocalDateTime.parse(date, formatter);

            Task task = new Deadlines(t, dateTime);
            tasks.add(task);
            storage.saveToFile(tasks);
            System.out.println("    ____________________________________________________________");
            System.out.println("     Got it. I've added this task:");
            System.out.println("     " + task.toString());
            System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
            System.out.println("    ____________________________________________________________");
        } catch (DateTimeParseException e) {
            System.out.println("    Invalid date/time format. Please enter in dd/MM/yyyy HHmm format.");
        }
    }

    public void addEvents(String t, String start, String end) throws ChatbotException {
        if (t == null || t.trim().isEmpty()) {
            throw new ChatbotException("☹ OOPS!!! The description of a event cannot be empty.");
        } else if (start == null || start.trim().isEmpty()) {
            throw new ChatbotException("☹ OOPS!!! The starting date of a event cannot be empty.");
        } else if (end == null || end.trim().isEmpty()) {
            throw new ChatbotException("☹ OOPS!!! The end time of a event cannot be empty.");
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            LocalDateTime startDateTime = LocalDateTime.parse(start, formatter);
            LocalDateTime endDateTime = LocalDateTime.parse(end, formatter);

            Events newEvent = new Events(t, startDateTime, endDateTime);
            tasks.add(newEvent);
            storage.saveToFile(tasks);
            System.out.println("    ____________________________________________________________");
            System.out.println("    Got it. I've added this event:");
            System.out.println("      " + newEvent);
            System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
            System.out.println("    ____________________________________________________________");
        } catch (DateTimeParseException e) {
            System.out.println("    ____________________________________________________________");
            System.out.println("    Invalid date/time format. Please enter in dd/MM/yyyy HHmm format.");
            System.out.println("    ____________________________________________________________");
        }
    }

    public void taskDone(int index) {
        try {
            tasks.markTaskDone(index);
            storage.saveToFile(tasks);
            System.out.println("    ____________________________________________________________");
            System.out.println("     Nice! I've marked this task as done:");
            System.out.println("       " + tasks.getTask(index));
            System.out.println("    ____________________________________________________________");
        } catch (NumberFormatException e) {
            System.out.println("    ____________________________________________________________");
            System.out.println("     Oops! Please enter a valid task number to mark.");
            System.out.println("    ____________________________________________________________");
        }
    }

    public void unMarktask(int index) {
        try {
            tasks.unMarkTask(index);
            storage.saveToFile(tasks);
            System.out.println("    ____________________________________________________________");
            System.out.println("     OK, I've marked this task as not done yet:");
            System.out.println("       " + tasks.getTask(index));
            System.out.println("    ____________________________________________________________");
        } catch (NumberFormatException e) {
            System.out.println("    ____________________________________________________________");
            System.out.println("     Oops! Please enter a valid task number to unmark.");
            System.out.println("    ____________________________________________________________");
        }
    }

    public void printTasks() {
        tasks.printTasks();
    }

    public void deleteTask(int index) throws ChatbotException {
        try {
            Task removedTask = tasks.removeTask(index); // Subtracting 1 because ArrayList is 0-based.
            storage.saveToFile(tasks);
            System.out.println("    ____________________________________________________________");
            System.out.println("     Noted. I've removed this task:");
            System.out.println("       " + removedTask);
            System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
            System.out.println("    ____________________________________________________________");
        } catch (IndexOutOfBoundsException e) {
            throw new ChatbotException("Please provide a valid task number to delete.");
        }


    }


    public void printTasksOnDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate targetDate = LocalDate.parse(date, formatter);
        for (Task task : tasks.getAllTasks()) {
            if (task instanceof Deadlines) {
                Deadlines deadlineTask = (Deadlines) task;
                if (targetDate.equals(deadlineTask.getDateTime().toLocalDate())) {
                    System.out.println(task);
                }
            }
        }
    }

}
