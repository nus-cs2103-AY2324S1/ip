import java.io.*;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;  // Import the Scanner class
import java.time.LocalDate;

public class Duke {
    static ArrayList<Task> list = new ArrayList<Task>(); // List to be returned when input is "list"
    static int counter = 0; // Items in list
    File saveFile = new File("./data/duke.txt"); // Loads the save file

    public static enum Type {
        LIST,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
    }

    public static String greet() { // Greets user on initialisation
        return "Good day to you, I'm ButlerBot.\n" +
                "How may I be of service to you?\n";
    }

    public static String thank() { // Exits the Bot
        return "Goodbye and have a nice day.";
    }

    public static String command(String input) throws DukeException, IOException { // Checks the input
        if (input.startsWith("list")) { // list command
            return enumCommand(Type.LIST, input);
        } else if (input.startsWith("mark ")) { // mark command
            return enumCommand(Type.MARK, input);
        } else if (input.startsWith("unmark ")) { // unmark command
            return enumCommand(Type.UNMARK, input);
        } else if (input.startsWith("todo ")) { // todo command
            return enumCommand(Type.TODO, input);
        } else if (input.startsWith("deadline ")) { // deadline command
            return enumCommand(Type.DEADLINE, input);
        } else if (input.startsWith("event ")) { // event command
            return enumCommand(Type.EVENT, input);
        } else if (input.startsWith("delete ")) { // delete command
            return enumCommand(Type.DELETE, input);
        } else {
            throw new DukeException("I'm afraid I do not quite understand. Could you kindly repeat it?");
        }
    }

    public static String enumCommand(Type command, String input) throws DukeException, IOException {
        Type type = command;

        switch (type) {
            case LIST:
                return list();
            case MARK:
                return mark(input);
            case UNMARK:
                return unmark(input);
            case TODO:
                return todo(input);
            case DEADLINE:
                return deadline(input);
            case EVENT:
                return event(input);
            case DELETE:
                return delete(input);
            default:
                throw new DukeException("I'm afraid I do not quite understand. Could you kindly repeat it?");
        }
    }

    public static String mark(String input) throws DukeException {
        int index = Integer.valueOf(input.substring(5)) - 1;
        if (index >= 0 && index < counter) {
            list.get(index).setDone(); // Item mark complete
            return "Congratulations on finishing the task. I will now mark it as complete:\n" +
                    list.get(index).toString()+ "\n";
        } else { // Index error
            throw new DukeException("I'm afraid the task does not exist. " +
                    "Perhaps you might want to see your list again?");
        }
    }

    public static String unmark(String input) throws DukeException {
        int index = Integer.valueOf(input.substring(7)) - 1;
        if (index >= 0 && index < counter) {
            list.get(index).setNotDone(); // Item mark complete
            return "No worries. I will now mark it as incomplete:\n" +
                    list.get(index).toString() + "\n";
        } else { // Index error
            throw new DukeException("I'm afraid the task does not exist. " +
                    "Perhaps you might want to see your list again?");
        }
    }

    public static String list() throws DukeException {
        String result = "";
        for (int i = 0; i < list.size(); i++) { // Generates the String representation of the list
            result += i + 1 + ". " + list.get(i) + "\n";
        }
        if (result != "") {
            return result;
        } else { // Empty list
            throw new DukeException("There is nothing on your list currently. " +
                    "Perhaps you might want to add a new task?");
        }
    }

    public static void addTask(Task task) throws DukeException, IOException {
        list.add(task); // Adds task to the list
        counter += 1;
    }

    public static String todo(String input) throws DukeException, IOException {
        String task = input.substring(5);
        if (task != "") {
            Task item = new Todo(task);
            addTask(item);
            String response = "Understood, I will add the following todo to your list:\n" + item.toString();
            String listLength = "Please note that there are " + counter + " tasks in the list.";
            return response + "\n" + listLength + "\n";
        } else { // No task
            throw new DukeException("I am missing some information. " +
                    "I must have not heard you correctly. " +
                    "Perhaps you can say it again?");
        }
    }

    public static String deadline(String input) throws DukeException, IOException {
        try {
            String desc = input.substring(9);
            String task = desc.split(" /by ")[0];
            String by = desc.split(" /by ")[1];
            LocalDate date = parser(by);
            Task item = new Deadline(task, date);
            addTask(item);
            String response = "Understood, I will add the following deadline to your list:\n" + item.toString();
            String listLength = "Please note that there are " + counter + " tasks in the list.";
            return response + "\n" + listLength + "\n";
        } catch (DukeException e) {
            throw new DukeException("I am missing some information. " +
                    "I must have not heard you correctly. " +
                    "Perhaps you can say it again?");
        }
    }

    public static String event(String input) throws DukeException, IOException {
        try {
            String desc = input.substring(6);
            String[] eventTime = desc.split(" /from ");
            String task = eventTime[0];
            String[] time = eventTime[1].split(" /to ");
            String from = time[0];
            String to = time[1];
            LocalDate fromDate = parser(from);
            LocalDate toDate = parser(to);
            Task item = new Event(task, fromDate, toDate);
            addTask(item);
            String response = "Understood, I will add the following deadline to your list:\n" + item.toString();
            String listLength = "Please note that there are " + counter + " tasks in the list.";
            return response + "\n" + listLength + "\n";
        } catch (DukeException e) {
            throw new DukeException("I am missing some information. " +
                    "I must have not heard you correctly. " +
                    "Perhaps you can say it again?");
        }
    }

    public static String delete(String input) throws DukeException, IOException {
        int index = Integer.valueOf(input.substring(7)) - 1;
        if (index >= 0 && index < counter) {
            Task task = list.get(index);
            list.remove(index);
            counter -= 1;
            save();
            String response = "Understood, I will remove the following task from your list:\n" + task.toString();
            String listLength = "Please note that there are " + counter + " tasks in the list.";
            return response + "\n" + listLength + "\n";
        } else { // Index error
            throw new DukeException("I'm afraid the task does not exist. " +
                    "Perhaps you might want to see your list again?");
        }
    }

    public static void load() throws DukeException, IOException {
        try {
            File save = new File("./data/duke.txt");
            boolean saveExist = save.exists();
            if (!saveExist) {
                File path = new File("./data");
                boolean pathExist = path.exists();
                if (!pathExist) {
                    path.mkdir();
                }
                save.createNewFile();
            } else {
                Scanner input = new Scanner(save);
                while (input.hasNextLine()) {
                    String details = input.nextLine();
                    String[] data = details.split(" \\| ");
                    String type = data[0];
                    String isCompleted = data[1];
                    String task = data[2];
                    if (type.equals("T")) {
                        addTask(new Todo(task));
                        if (isCompleted == "1") {
                            list.get(counter - 1).setDone();
                        }
                    }
                    if (type.equals("D")) {
                        LocalDate date = parser(data[3]);
                        addTask(new Deadline(task, date));
                        if (isCompleted == "1") {
                            list.get(counter - 1).setDone();
                        }
                    }
                    if (type.equals("E")) {
                        LocalDate from = parser(data[3]);
                        LocalDate to = parser(data[3]);
                        addTask(new Event(task, from, to));
                        if (isCompleted == "1") {
                            list.get(counter - 1).setDone();
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void save() throws DukeException, IOException {
        try (FileWriter save = new FileWriter("./data/duke.txt")) {
            String str = "";
            for (int i = 0; i < counter; i++) {
                str += list.get(i).write() + "\n";
            }
            save.write(str);
        }
    }

    public static LocalDate parser(String str) throws DateTimeParseException {
        try {
            LocalDate date = LocalDate.parse(str);
            return date;
        } catch (DateTimeParseException ex) {
            throw new DateTimeParseException("I'm afraid I do not quite understand. " +
                    "Please input the date in the following format:\n" +
                    "yyyy-mm-dd", ex.getParsedString(), ex.getErrorIndex());
        }
    }

    public static void main(String[] args) throws DukeException, FileNotFoundException {
        try (Scanner myObj = new Scanner(System.in)) {
            try {
                load();
                System.out.println(greet()); // Greets user

                String echo = myObj.nextLine(); // Reads user input

                while (!echo.equals("bye")) {
                    try {
                        System.out.println(command(echo)); // Checks input
                    } catch (DukeException ex) {
                        System.err.println(ex); // Prints error
                    } catch (DateTimeParseException ex) {
                        System.err.println(ex); // Prints error
                    } finally {
                        save();
                        echo = myObj.nextLine(); // Scan for next input
                    }
                }

                System.out.println(thank()); // Exits the bot
            
            } catch (IOException ex) {
                System.err.println(ex);
            }
        }

  
    }
}
