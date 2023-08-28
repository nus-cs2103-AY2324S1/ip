import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tasks;

    public TaskManager(){
        this.tasks = new ArrayList<Task>();
    }
    public void addTodo(String t) throws ChatbotException {
        if (t == null || t.trim().isEmpty()) {
            throw new ChatbotException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        Task task = new Todos(t);
        tasks.add(task);
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
        Task task = new Deadlines(t, date);
        tasks.add(task);
        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task:");
        System.out.println("     " + task.toString());
        System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
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
        Task task = new Events(t, start, end);
        tasks.add(task);
        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task:");
        System.out.println("     " + task.toString());
        System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
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
        }
    }

    public void unMarktask(int index){
        Task task = getTask(index);
        if (task != null) {
            task.unMark();
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
            System.out.println("    ____________________________________________________________");
            System.out.println("     Noted. I've removed this task:");
            System.out.println("       " + removedTask);
            System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
            System.out.println("    ____________________________________________________________");
        } catch (IndexOutOfBoundsException e) {
            throw new ChatbotException("Please provide a valid task number to delete.");
        }
    }
}
