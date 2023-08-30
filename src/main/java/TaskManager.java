import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tasks;
    private Storage storage;

    public TaskManager(){
        this.tasks = new ArrayList<Task>();
        this.storage = new Storage( "./data/chatbot.txt");
        this.tasks = storage.loadFromFile();

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
        }
        else if (date == null || date.trim().isEmpty()) {
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
        }
        else if (start == null || start.trim().isEmpty()) {
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

            System.out.println("    Got it. I've added this event:");
            System.out.println("      " + newEvent);
            System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
        } catch (DateTimeParseException e) {
            System.out.println("    Invalid date/time format. Please enter in dd/MM/yyyy HHmm format.");
        }
    }





    public Task getTask(int index) {
        if (index >= 1 && index <= tasks.size()) {
            return tasks.get(index - 1);
        }
        return null;
    }

    public void taskDone(int index) {
        Task task = getTask(index);
        if (task != null) {
            task.markAsDone();
            storage.saveToFile(tasks);
        }
    }

    public void unMarktask(int index){
        Task task = getTask(index);
        if (task != null) {
            task.unMark();
            storage.saveToFile(tasks);
        }
    }
    public void printTasks() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("     %d.%s\n", i + 1, tasks.get(i).toString());
        }
        System.out.println("    ____________________________________________________________");
    }

    public void deleteTask(int index) throws ChatbotException {
        try {
            Task removedTask = tasks.remove(index - 1); // Subtracting 1 because ArrayList is 0-based.
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
        for (Task task : tasks) {
            if (task instanceof Deadlines) {
                Deadlines deadlineTask = (Deadlines) task;
                if (targetDate.equals(deadlineTask.getDateTime().toLocalDate())) {
                    System.out.println(task);
                }
            }
        }
    }

}
