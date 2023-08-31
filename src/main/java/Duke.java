import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static String line = "____________________________________________________________";
    final static String LIST = "list";
    final static String MARK = "mark";
    final static String UNMARK = "unmark";
    final static String TODO = "todo";
    final static String DEADLINE = "deadline";
    final static String EVENT = "event";
    final static String DELETE = "delete";

    public static void main(String[] args) {
        ArrayList<Task> storedList = new ArrayList<>();

        try {
            System.out.println("Checking for data file!");
            findDirectory("./data");
            readFile("./data", "data.txt");
            PrintWriter writer = new PrintWriter("./data/data.txt");
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        String logo = "        ┏┓\n"
                + "        ┃┃\n"
                + "        ┃┗━┳┓╋┏┳━━┓\n"
                + "        ┃┏┓┃┃╋┃┃┃━┫\n"
                + "        ┗━━┻━┓┏┻━━┛\n"
                + "        ╋╋╋┏━┛┃\n"
                + "        ╋╋╋┗━━┛\n";
        System.out.println("\nHello! I'm \n" + logo);
        System.out.println("How can I help you? \n" + line);
        Scanner userInput = new Scanner(System.in);
        String input = userInput.nextLine();
        String[] inputArr = input.split(" ",2);

        while (!input.equals("bye")) {
            startDuke(input, inputArr, storedList);
            input = userInput.nextLine();
            inputArr = input.split(" ",2);
        }

        storedList.forEach((x) -> x.writeToFile("./data/data.txt")); // store inside data.txt
        System.out.println("Bye (actually hehe). Hope to see you again!\n" + line);
    }

    public static void startDuke(String input, String[] inputArr, ArrayList<Task> storedList) {
        try {
            switch (inputArr[0]) {
                case LIST:
                    System.out.print("Here are the tasks in your list: \n");
                    for (int i = 0; i < storedList.size(); i++) {
                        int index = i + 1;
                        Task t = storedList.get(i);
                        System.out.println(index + "." + t.toString());
                    }
                    System.out.print(line + "\n");
                    break;
                case MARK:
                    int mIndex = input.charAt(5) - 49;
                    Task t = storedList.get(mIndex);
                    storedList.get(mIndex).markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(t.toString() + "\n" + line);
                    break;
                case UNMARK:
                    int umIndex = input.charAt(7) - 49;
                    Task umTask = storedList.get(umIndex);
                    storedList.get(umIndex).markAsUndone();
                    System.out.println("Nice! I've marked this task as not done yet:");
                    System.out.println(umTask.toString() + "\n" + line);
                    break;
                case DEADLINE:
                    if (inputArr[1].isEmpty()) {
                        throw new DukeException(String.format(DukeException.NON_EMPTY, DEADLINE));
                    }
                    String[] deadlineArr = inputArr[1].split("/by ", 2);
                    Task deadline = new Deadline(deadlineArr[0], deadlineArr[1]);
                    storedList.add(deadline);
                    System.out.println("added " + deadline.toString() + "\n");
                    System.out.println("Now you have " + storedList.size() + " tasks in the list. \n" + line);
                    break;
                case EVENT:
                    if (inputArr[1].isEmpty()) {
                        throw new DukeException(String.format(DukeException.NON_EMPTY, EVENT));
                    }
                    String[] eventArr1 = inputArr[1].split("/from ", 2);
                    String[] eventArr2 = eventArr1[1].split("/to ", 2);
                    Task event = new Event(eventArr1[0], eventArr2[0], eventArr2[1]);
                    storedList.add(event);
                    System.out.println("added " + event.toString() + "\n");
                    System.out.println("Now you have " + storedList.size() + " tasks in the list. \n" + line);
                    break;
                case TODO:
                    if (inputArr[1].isEmpty()) {
                        throw new DukeException(String.format(DukeException.NON_EMPTY, TODO));
                    }
                    Task todo = new Todo(inputArr[1]);
                    storedList.add(todo);
                    System.out.println("added " + todo.toString() + "\n");
                    System.out.println("Now you have " + storedList.size() + " tasks in the list. \n" + line);
                    break;
                case DELETE:
                    int deleteIndex = input.charAt(7) - 49;
                    Task deleted = storedList.get(deleteIndex);
                    storedList.remove(deleteIndex);
                    System.out.println("Noted. I've removed the task: \n");
                    System.out.println(deleted.toString() + "\n");
                    System.out.println("Now you have " + storedList.size() + " tasks in the list. \n" + line);
                    break;
                default:
                    throw new DukeException(DukeException.UNKNOWN);
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage() + "\n" + line);
        }
    }

    public static void findDirectory(String directory) {
        File dataDirectory = new File(directory);
        if (dataDirectory.exists() && dataDirectory.isDirectory()) {
            System.out.println("Directory exists.");
        } else {
            if (dataDirectory.mkdir()) {
                System.out.println("Directory created.");
            }
        }
    }

    public static void readFile(String directory, String fileName) throws IOException {
        File data = new File(directory, fileName);
        if (data.exists()) {
            System.out.println("Data file exists.");
            Scanner scanner = new Scanner(data);
            scanner.useDelimiter("\\| ");
            while (scanner.hasNextLine()) {
                String taskType = scanner.next();
                System.out.println(taskType);
            }
        } else {
            if (data.createNewFile()) {
                System.out.println("Data file created.");
            }
        }
    }
}