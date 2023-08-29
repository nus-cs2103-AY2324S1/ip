import java.io.*;
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
     * @param from Date when the Event task starts
     * @param to Date when the Event tasks end
     */
    public static Event addEvent(String desc, String from, String to) {
        Event curr = new Event(desc, from, to);
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
            System.out.println(i + 1 + "." + taskArr.get(i).toString());
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
                String str = Duke.sc.next();
                if (str.equals("bye")) {
                    writeToFile();
                    save();
                    System.out.println(exit);
                    break;
                } else if (str.equals("list")) {
                    listOut();
                } else if (str.equals("mark")) {
                    if (!Duke.sc.hasNextInt()) {
                        throw new WrongInput();
                    } else {
                        int index = Duke.sc.nextInt() - 1;
                        taskArr.get(index).mark();
                    }
                } else if (str.equals("unmark")) {
                    if (!Duke.sc.hasNextInt()) {
                        throw new WrongInput();
                    } else {
                        int index = Duke.sc.nextInt() - 1;
                        taskArr.get(index).unmark();
                    }
                } else if (str.equals("delete")) {
                    if (!Duke.sc.hasNextInt()) {
                        throw new WrongInput();
                    } else {
                        int index = Duke.sc.nextInt();
                        if (index > taskArr.size()) {
                            throw new WrongInput();
                        } else {
                            delete(index - 1);
                        }
                    }
                } else {
                    // check for task type first
                    if (str.equals("todo")) {
                        String desc = Duke.sc.nextLine();
                        if (desc.equals("") || desc.equals(" ")) {
                            throw new EmptyDescription();
                        } else {
                            Todo curr = addTodo(desc);
                        }
                    } else if (str.equals("deadline")) {
                        String desc = Duke.sc.next();
                        String date = null;
                        while (Duke.sc.hasNext()) {
                            String next = Duke.sc.next();
                            if (!next.equals("by")) {
                                desc = desc + " " + next;
                            } else {
                                date = Duke.sc.nextLine();
                                break;
                            }
                        }
                        Deadline curr = addDeadline(desc, date);
                    } else if (str.equals("event")) {
                        String desc = Duke.sc.next();
                        String from = null;
                        String to = null;
                        while (Duke.sc.hasNext()) {
                            String next = Duke.sc.next();
                            if (!next.equals("from")) {
                                desc = desc + " " + next;
                            } else {
                                from = Duke.sc.next();
                                while (Duke.sc.hasNext()) {
                                    String temp = Duke.sc.next();
                                    if (!temp.equals("to")) {
                                        from = from + " " + temp;
                                    } else {
                                        to = Duke.sc.nextLine();
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                        Event curr = addEvent(desc, from, to);
                    } else {
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
