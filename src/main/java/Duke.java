import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Class for the ChatBot
 */
class Duke implements Serializable {
    /**
     * Scanner to read user input.
     */
    public static Scanner sc = new Scanner(System.in).useDelimiter("[\\s,/]+");
    /**
     * An array of Tasks.
     */
    public static ArrayList<Task> taskArr = new ArrayList<>();
    /**
     * Name of the text file.
     */
    public static String filename = "duke.txt";
    public static String delim = " ";
    /**
     * Greeting from the bot when user launches the program.
     */
    public static String greeting = "-------------------------------\n"
            + "Hello! I'm Skog.\n"
            + "What can I do for you?\n"
            + "-------------------------------\n";
    /**
     * Exit message when user exits the bot.
     */
    public static String exit = "-------------------------------\n"
            + "Bye. Hope to see you again soon!\n"
            + "-------------------------------\n";
    /**
     * Creates a Todo task in taskArr.
     * @param desc Description of the Todo task
     */
    public static Todo addTodo(String desc) {
        Todo curr = new Todo(desc);
        taskArr.add(curr);
        System.out.println("-------------------------------\n"
                + "Got it. I've added this task:\n"
                + curr.toString()
                + totalTasks()
                + "\n-------------------------------\n");
        return curr;
    }
    /**
     * Creates a Deadline task in taskArr.
     * @param desc Description of the Deadline task
     * @param date Date to complete the Deadline task by
     */
    public static Deadline addDeadline(String desc, String date) {
        Deadline curr = new Deadline(desc, date);
        taskArr.add(curr);
        System.out.println("-------------------------------\n"
                + "Got it. I've added this task:\n"
                + curr.toString()
                + totalTasks()
                + "\n-------------------------------\n");
        return curr;
    }
    /**
     * Creates an Event task in taskArr.
     * @param desc Description of the Event task
     * @param timeline Start and End date of the Event task
     */
    public static Event addEvent(String desc, String timeline) {
        Event curr = new Event(desc, timeline);
        taskArr.add(curr);
        System.out.println("-------------------------------\n"
                + "Got it. I've added this task:\n"
                + curr.toString()
                + totalTasks()
                + "\n-------------------------------\n");
        return curr;
    }

    /**
     * Remove a task from taskArr.
     * @param num Indicates the task number to be deleted
     */
    public static void delete(int num) {
        Task toRemove = taskArr.get(num);
        taskArr.remove(num);
        System.out.println("-------------------------------\n"
        + "Noted, I've removed this task:\n"
        + toRemove.toString()
        + totalTasks()
        + "\n-------------------------------\n");
    }

    /**
     * Lists out all the tasks in taskArr.
     */
    public static void listOut() {
        int size = taskArr.size();
        System.out.println("-------------------------------\n"
        + "Here are the tasks in your list:");
        for (int i = 0; i < size; i++) {
            System.out.println(i + 1 + ". " + taskArr.get(i).toString());
        }
        System.out.println("-------------------------------\n");
    }
    /**
     * Number of tasks in taskArr currently.
     * @return String containing the number of tasks added to taskArr
     */
    public static String totalTasks() {
        int size = taskArr.size();
        return "\nNow you have " + size + " tasks in the list.";
    }
    public static void initiateArr() {
        try {
            FileInputStream fileIn = new FileInputStream("data.txt");
            ObjectInputStream objIn = new ObjectInputStream(fileIn);
            // can safely cast because all the methods to modify the array
            // guarantee that the elements in the array are all sub-classes
            // of Task, the array is type-safe
            taskArr = (ArrayList<Task>) objIn.readObject();
            objIn.close();
        } catch (IOException ignored) {
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static void save() {
        String dataFile = "data.txt";

        try {
            FileOutputStream fos = new FileOutputStream(dataFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(taskArr);
            oos.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
    /**
     * Saves all the tasks' information in a text file.
     */
    public static void writeToFile() {
        try {
            FileWriter fw = new FileWriter(filename);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < taskArr.size(); i++) {
                bw.write(taskArr.get(i).toString());
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public static String getDescription(String[] arr) {
        String result = null;

        // can safely ignore the first element as we have already
        // checked for the task type in main logic
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].equals("/by")) {
                break;
            } else if (arr[i].equals("/from")) {
                break;
            } else {
                if (result == null) {
                    result = arr[i];
                } else {
                    result += " " + arr[i];
                }
            }
        }

        return result;
    }
    /**
     * Extracts the deadline from the String.
     * @param arr Array of Strings after using delimiter
     * @return Final String to be passed in to parse
     */
    public static String getDeadline(String[] arr) {
        String input = null;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals("/by")) {
                input = arr[i + 1];
            }
        }

        String result = LocalDate.parse(input)
                .format(DateTimeFormatter.ofPattern("MMM d yyyy"));

        return result;
    }
    public static String getEventTimeline(String[] arr) {
        String fromInput = null;
        String toInput = null;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals("/from")) {
                fromInput = arr[i + 1];
                toInput = arr[i + 3];
            }
        }
        String from = LocalDate.parse(fromInput).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String to = LocalDate.parse(toInput).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return from + " - " + to;
    }

    public static void main(String[] args) {
        try {
            File myFile = new File(filename);
            if (myFile.createNewFile()) {
                System.out.println("-------------------------------\n"
                        + "Welcome! New text file created.");
            } else {
                // go straight into task control
            }
        } catch (IOException e) {
            System.out.println("An error has occurred!");
        }

        System.out.println(greeting);

        new File("data.txt");

        initiateArr();

        while (Duke.sc.hasNext()) {
            try {

                String[] arr = Duke.sc.nextLine().split(delim);
                String type = arr[0];

                if (type.equals("bye")) {
                    writeToFile();
                    save();
                    System.out.println(exit);
                    break;
                } else if (type.equals("list")) {
                    listOut();
                } else if (type.equals("mark")) {
                    if (arr.length == 1) {
                        throw new WrongInput();
                    } else  {
                        try {
                            int index = Integer.parseInt(arr[1]);
                            if (index > taskArr.size()) {
                                throw new WrongInput();
                            } else {
                                taskArr.get(index - 1).mark();
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("An error occurred in the mark portion.");
                        }
                    }
                } else if (type.equals("unmark")) {
                    if (arr.length == 1) {
                        throw new WrongInput();
                    } else  {
                        try {
                            int index = Integer.parseInt(arr[1]);
                            if (index > taskArr.size()) {
                                throw new WrongInput();
                            } else {
                                taskArr.get(index - 1).unmark();
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("An error occurred in the unmark portion.");
                        }
                    }
                } else if (type.equals("delete")) {
                    if (arr.length == 1) {
                        throw new WrongInput();
                    } else {
                        try {
                            int index = Integer.parseInt(arr[1]);
                            if (index > taskArr.size()) {
                                throw new WrongInput();
                            } else {
                                delete(index - 1);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("An error occurred in the delete portion.");
                        }
                    }
                }
                else {
                    // check for task type first
                    if (type.equals("todo")) {
                        if (arr.length == 1) {
                            throw new EmptyDescription();
                        } else {
                            String desc = getDescription(arr);
                            addTodo(desc);
                        }
                    } else if (type.equals("deadline")) {
                        String desc = getDescription(arr);
                        String date = getDeadline(arr);
                        addDeadline(desc, date);
                    }
                    else if (type.equals("event")) {
                        String desc = getDescription(arr);
                        String timeline = getEventTimeline(arr);
                        addEvent(desc, timeline);
                    }
                else {
                        throw new WrongInput();
                    }
                }
            } catch (EmptyDescription e) {
                System.out.println(e.getMessage());
            } catch (WrongInput e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
