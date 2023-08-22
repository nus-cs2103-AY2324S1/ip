import java.util.ArrayList;
import java.util.Scanner;  // Import the Scanner class
public class Duke {
    static ArrayList<Task> list = new ArrayList<Task>(); // List to be returned when input is "list"
    static int counter = 0; // Items in list

    public static String greet() { // Greets user on initialisation
        return "Good day to you, I'm ButlerBot.\n" +
                "How may I be of service to you?\n";
    }

    public static String thank() { // Exits the Bot
        return "Goodbye and have a nice day.";
    }

    public static String command(String input) throws DukeException {
        if (input.startsWith("list")) {
            return list();
        } else
        if (input.startsWith("mark ")) {
            int index = Integer.valueOf(input.substring(5)) - 1;
            return mark(index);
        } else
        if (input.startsWith("unmark ")) {
            int index = Integer.valueOf(input.substring(7)) - 1;
            return unmark(index);
        } else
        if (input.startsWith("todo ")) {
            String task = input.substring(5);
            return todo(task);
        } else
        if (input.startsWith("deadline ")) {
            String task = input.substring(9);
            String name = task.split(" /by ")[0];
            String by = task.split(" /by ")[1];
            return deadline(name, by);
        } else
        if (input.startsWith("event ")) {
            String task = input.substring(6);
            String[] description = task.split(" /from ");
            String name = description[0];
            String[] startEnd = description[1].split(" /to ");
            String from = startEnd[0];
            String to = startEnd[1];
            return event(name, from, to);
        } else
        if (input.startsWith("delete ")) {
            int index = Integer.valueOf(input.substring(7)) - 1;
            return delete(index);
        }
        {
            throw new DukeException("I'm afraid I do not quite understand. Could you kindly repeat it?");
        }
    }

    public static String mark(int index) throws DukeException {
        if (index >= 0 && index < counter) {
            list.get(index).setDone(); // Item mark complete
            return "Congratulations on finishing the task. I will now mark it as complete:\n" +
                    list.get(index).toString()+ "\n";
        } else {
            throw new DukeException("I'm afraid the task does not exist. " +
                    "Perhaps you might want to see your list again?");
        }
    }

    public static String unmark(int index) throws DukeException {
        if (index >= 0 && index < counter) {
            list.get(index).setNotDone(); // Item mark complete
            return "No worries. I will now mark it as incomplete:\n" +
                    list.get(index).toString() + "\n";
        } else {
            throw new DukeException("I'm afraid the task does not exist. " +
                    "Perhaps you might want to see your list again?");
        }
    }

    public static String list() throws DukeException {
        String result = "";
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null) {
                result += i + 1 + ". " + list.get(i) + "\n";
            } else {
                break;
            }
        }
        if (result != "") {
            return result;
        } else {
            throw new DukeException("There is nothing on your list currently. " +
                    "Perhaps you might want to add a new task?");
        }
    }

    public static void addTask(Task task) throws DukeException {
        list.add(task);
    }

    public static String todo(String task) throws DukeException {
        if (task != "") {
            Task item = new Todo(task);
            addTask(item);
            counter += 1;
            String response = "Understood, I will add the following todo to your list:\n" + item.toString();
            String listLength = "Please note that there are " + counter + " tasks in the list.";
            return response + "\n" + listLength + "\n";
        } else {
            throw new DukeException("I am missing some information. " +
                    "I must have not heard you correctly. " +
                    "Perhaps you can say it again?");
        }
    }

    public static String deadline(String task, String by) throws DukeException {
        if (task != "" && by != "") {
            Task item = new Deadline(task, by);
            addTask(item);
            counter += 1;
            String response = "Understood, I will add the following deadline to your list:\n" + item.toString();
            String listLength = "Please note that there are " + counter + " tasks in the list.";
            return response + "\n" + listLength + "\n";
        } else {
            throw new DukeException("I am missing some information. " +
                    "I must have not heard you correctly. " +
                    "Perhaps you can say it again?");
        }
    }

    public static String event(String task, String from, String to) throws DukeException {
        if (task != "" && from != "" && to != "") {
            Task item = new Event(task, from, to);
            addTask(item);
            counter += 1;
            String response = "Understood, I will add the following event to your list:\n" + item.toString();
            String listLength = "Please note that there are " + counter + " tasks in the list.";
            return response + "\n" + listLength + "\n";
        } else {
            throw new DukeException("I am missing some information. " +
                    "I must have not heard you correctly. " +
                    "Perhaps you can say it again?");
        }
    }

    public static String delete(int index) throws DukeException {
        if (index >= 0 && index < counter) {
            Task task = list.get(index);
            list.remove(index);
            counter -= 1;
            String response = "Understood, I will remove the following task from your list:\n" + task.toString();
            String listLength = "Please note that there are " + counter + " tasks in the list.";
            return response + "\n" + listLength + "\n";
        } else {
            throw new DukeException("I'm afraid the task does not exist. " +
                    "Perhaps you might want to see your list again?");
        }
    }

    public static void main(String[] args) throws DukeException {

        Scanner myObj = new Scanner(System.in);  // Create a Scanner object

        System.out.println(greet());

        String echo = myObj.nextLine(); // Reads user input

        while (!echo.equals("bye")) {
            System.out.println(command(echo));
            echo = myObj.nextLine();
        }
        System.out.println(thank()); // Exits the bot
    }
}
