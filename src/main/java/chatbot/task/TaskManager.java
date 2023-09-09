package chatbot.task;

import chatbot.ChatbotException;
import chatbot.storage.Storage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * The TaskManager class manages tasks, including adding, marking, unmarking, printing, and deleting tasks.
 */
public class TaskManager {
    private TaskList tasks;
    private Storage storage;


    /**
     * Constructor for this class.
     */
    public TaskManager() {
        this.tasks = new TaskList();
        this.storage = new Storage("./data/chatbot.txt");
        this.tasks = storage.loadFromFile();
    }

    /**
     * Add todo task into the list.
     *
     * @param t description of todo
     * @throws ChatbotException when the description is empty
     */
    public String addTodo(String t) throws ChatbotException {
        if (t == null || t.trim().isEmpty()) {
            throw new ChatbotException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        Task task = new Todos(t);
        tasks.add(task);
        storage.saveToFile(tasks);
        return "Got it. I've added this task:\n" + task.toString() + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Add deadline to the list and print out.
     *
     * @param t description
     * @param date date
     * @throws ChatbotException when one of the param is empty
     */
    public String addDeadlines(String t, String date) throws ChatbotException {
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
            return "Got it. I've added this task:\n" + task.toString() + "\nNow you have " + tasks.size() + " tasks in the list.";
        } catch (DateTimeParseException e) {
           return "Invalid date/time format. Please enter in dd/MM/yyyy HHmm format.";
        }
    }

    /**
     * Add event to the list and print out.
     *
     * @param t description
     * @param start starting date
     * @param end end date
     * @throws ChatbotException when one of the param is empty
     */
    public String addEvents(String t, String start, String end) throws ChatbotException {
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

            return "Got it. I've added this task:\n" + newEvent.toString() + "\nNow you have " + tasks.size() + " tasks in the list.";

        } catch (DateTimeParseException e) {
            return "Invalid date/time format. Please enter in dd/MM/yyyy HHmm format.";
        }
    }

    /**
     * mark the task done and print.
     *
     * @param index index of the task
     */
    public String taskDone(int index) {
        try {
            tasks.markTaskDone(index);
            storage.saveToFile(tasks);
           return "Nice! I've marked this task as done:\n" + tasks.getTask(index);
        } catch (NumberFormatException e) {
            return "Oops! Please enter a valid task number to mark.";
        }
    }

    /**
     * unmark the task and print
     *
     * @param index index of the task
     */
    public String unMarktask(int index) {
        try {
            tasks.unMarkTask(index);
            storage.saveToFile(tasks);
           return "OK, I've marked this task as not done yet:\n" + tasks.getTask(index);
        } catch (NumberFormatException e) {
            return "Oops! Please enter a valid task number to unmark.";
        }
    }

    public void printTasks() {
        tasks.printTasks();
    }

    /**
     * delete the task in the list and print.
     *
     * @param index index of the task
     * @throws ChatbotException when invalid index is given
     */
    public String deleteTask(int index) throws ChatbotException {
        try {
            Task removedTask = tasks.removeTask(index); // Subtracting 1 because ArrayList is 0-based.
            storage.saveToFile(tasks);

            return "Noted. I've removed this task:\n" + removedTask + "Now you have " + tasks.size() + " tasks in the list.";
        } catch (IndexOutOfBoundsException e) {
            throw new ChatbotException("Please provide a valid task number to delete.");
        }
    }

    public String showFarewell() {
        return "Bye. Hope to see you again soon!\n";
    }

    /**
     * Print the date.
     *
     * @param date date
     */
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

    public List<Task> findTaskByKeyboard(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks.getAllTasks()) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    public List<Task> getAllTasks() {
        return tasks.getAllTasks();
    }
}
