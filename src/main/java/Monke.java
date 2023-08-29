import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Monke {
    private enum Command {
        LIST,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE
    }

    private static String dataPath = "./data/monke.txt";

    private static ArrayList<Task> list = new ArrayList<>();

    public static void speak(String msg) {
        System.out.println("\t" + msg);
    }
    public static void greet() {
        Monke.speak("Hello, I'm Monke. OOGA BOOGA!");
        Monke.speak("What can I do for you?");
        Monke.printHorizontalLine();
    }

    public static void exit() {
        Monke.speak("Bye. Hope to see you again soon! OOGA BOOGA!");
        Monke.printHorizontalLine();
    }

    public static void printHorizontalLine() {
        for (int i = 0; i < 100; i++) {
            System.out.print("_");
        }
        System.out.println();
    }

    public static void execute(String command, String args) throws MonkeException, IOException {
        Command cmd;
        try {
            cmd = Command.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new MonkeException("OOGA??!! I'm sorry, but I don't know what that means :-(");
        }
        switch (cmd) {
            case LIST: {
                Monke.displayList();
                break;
            }
            case MARK: {
                int n = Monke.getListNumber(args);
                Task task = Monke.list.get(n - 1);
                task.mark();
                Monke.saveData(dataPath);
                Monke.speak("Ooga booga! I've marked this task as done:");
                Monke.speak("\t" + task);
                break;
            }
            case UNMARK: {
                int n = Monke.getListNumber(args);
                Task task = Monke.list.get(n - 1);
                task.unmark();
                Monke.saveData(dataPath);
                Monke.speak("Ooga booga! I've marked this task as done:");
                Monke.speak("\t" + task);
                break;
            }
            case TODO: {
                Todo todo = Monke.getTodo(args);
                Monke.addToList(todo);
                break;
            }
            case DEADLINE: {
                Deadline deadline = Monke.getDeadline(args);
                Monke.addToList(deadline);
                break;
            }
            case EVENT: {
                Event event = Monke.getEvent(args);
                Monke.addToList(event);
                break;
            }
            case DELETE: {
                int n = getListNumber(args);
                Monke.deleteFromList(n);
                break;
            }
            default: {
                throw new MonkeException("OOGA??!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }

    public static int getListNumber(String args) throws MonkeException {
        try {
            int n = Integer.parseInt(args);
            if (n > Monke.list.size()) {
                throw new MonkeException("OOGA BOOGA!! Your number is out of range. :(");
            }
            return n;
        } catch (NumberFormatException e) {
            throw new MonkeException("OOGA BOOGA!! You must provide a number from the list. :(");
        }
    }

    public static Todo getTodo(String args) throws MonkeException {
        if (args.isBlank()) {
            throw new MonkeException("OOGA BOOGA!! The description of a todo cannot be empty.");
        }
        return new Todo(args);
    }

    public static Deadline getDeadline(String args) throws MonkeException {
        String[] tmp = args.split(" /by ", 2);
        if (tmp.length < 2 || tmp[0].isBlank() || tmp[1].isBlank()) {
            throw new MonkeException("You must format your deadline like this:\n" +
                    "\t\tdeadline <task> /by <deadline>");
        }
        String description = tmp[0];
        String date = tmp[1];
        return new Deadline(description, date);
    }

    public static Event getEvent(String args) throws MonkeException {
        String[] tmp = args.split(" /from ", 2);
        String description = tmp[0];
        if (tmp.length < 2 || tmp[0].isBlank() || tmp[1].isBlank()) {
            throw new MonkeException("You must format your event like this:\n" +
                    "\t\tdeadline <task> /from <start time> /to <end time>");
        }
        String[] tmp2 = tmp[1].split(" /to ", 2);
        if (tmp2.length < 2 || tmp2[0].isBlank() || tmp2[1].isBlank()) {
            throw new MonkeException("You must format your event like this:\n" +
                    "\t\tdeadline <task> /from <start time> /to <end time>");
        }
        String start = tmp2[0];
        String end = tmp2[1];
        return new Event(description, start, end);
    }

    public static void addToList(Task task) throws MonkeException, IOException {
        Monke.speak("Got it. I've added this task:");
        Monke.speak("\t" + task);
        Monke.list.add(task);
        Monke.saveData(dataPath);
        Monke.speak("Now you have " + Monke.list.size() + " tasks in the list.");
    }
public static void deleteFromList(int n) throws MonkeException, IOException {
        Task task = Monke.list.get(n - 1);
        if (n <= 0 || n > Monke.list.size()) {
            throw new MonkeException("Choose a number from the list!!");
        }
        Monke.speak("Noted. I've removed this task:");
        Monke.speak("\t" + task);
        Monke.list.remove(n - 1);
        Monke.saveData(dataPath);
        Monke.speak("Now you have " + Monke.list.size() + " tasks in the list.");
    }

    public static void displayList() {
        for (int i = 0; i < Monke.list.size(); i++) {
            Monke.speak((i + 1) + ". " + Monke.list.get(i));
        }
    }

    public static <T> void loadFileToList(String filepath, List<T> lst) throws FileNotFoundException {
        File f = new File(filepath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            try {
                Task task = parseTaskData(s.nextLine());
                Monke.list.add(task);
            } catch (MonkeException e) {
                Monke.speak(e.getMessage());
            }
        }
    }

    public static Task parseTaskData(String data) throws MonkeException {
        String[] tmp = data.split(" \\| ");
        String taskType = tmp[0];
        String isDone = tmp[1];
        String description = tmp[2];
        Task task;
        switch (taskType) {
        case "T":
            task = new Todo(description);
            break;
        case "D":
            String date = tmp[3];
            task = new Deadline(description, date);
            break;
        case "E":
            String start = tmp[3];
            String end = tmp[4];
            task = new Event(description, start, end);
            break;
        default:
            throw new MonkeException("Invalid file data");
        }
        if (isDone.equals("1")) {
            task.mark();
        }
        return task;
    }

    public static void saveData(String filepath) throws IOException {
        FileWriter fw = new FileWriter(filepath);
        StringBuilder textToAdd = new StringBuilder();
        for (Task task: Monke.list) {
            textToAdd.append(task.formatData());
        }
        fw.write(textToAdd.toString());
        fw.close();
    }

    public static void main(String[] args) {
        Monke.printHorizontalLine();
        Monke.greet();
        try {
            Monke.loadFileToList(dataPath, Monke.list);
        } catch (IOException e) {
            File f = new File(dataPath);
        }

        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            String[] temp = input.split(" ", 2);
            String command = temp[0];
            String commandArgs = temp.length > 1 ? temp[1] : "";
            Monke.printHorizontalLine();

            if (command.equals("bye")) {
                break;
            }

            try {
                Monke.execute(command, commandArgs);
            } catch (MonkeException e) {
                Monke.speak(e.getMessage());
            } catch (IOException e) {
                Monke.speak(e.getMessage());
                break;
            }

            Monke.printHorizontalLine();
        }
        sc.close();

        Monke.exit();
    }
}
