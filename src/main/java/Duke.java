/**
 * The class with the main method where all the functionality begins.
 */

<<<<<<< HEAD
=======
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
>>>>>>> branch-Level-7
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
<<<<<<< HEAD
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        String line = "____________________________________________________________\n";
        System.out.println(line + " Hello! I'm Alcazar\n" +
                " What can I do for you?\n" +
                line);
        String prompt = Duke.inputText();
        try {
            while (!prompt.equals("bye")) {
                if (prompt.equals("list")) {
                    System.out.println(line + "Here are the tasks in your list:\n"
                            + getTasks(tasks) + line);
                } else if (prompt.contains("mark") || prompt.contains("unmark") ||
                prompt.contains("delete")) {
                    int index = Integer.parseInt(prompt.charAt(prompt.length() - 1) + "");
                    if(index > tasks.size()) {
                        throw new InvalidSerialException("☹ OOPS!!! I think you have added" +
                                "an incorrect serial number greater than " + (tasks.size() - 1));
                    }
                    if (prompt.contains("unmark")) {

                        tasks.get(index - 1).unmarkTask();
                        System.out.println(line +
                                "OK, I've marked this task as not done yet:\n" +
                                tasks.get(index - 1).toString() + "\n" + line);
                    } else if (prompt.contains("delete")) {
                        System.out.print(line +
                                "Noted. I've removed this task:\n" + "  " +
                                tasks.get(index - 1).toString() + "\n");
                        tasks.remove(index - 1);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list\n" + line);
                    } else {
                        tasks.get(index - 1).markTask();
                        System.out.println(line +
                                "Nice! I've marked this task as done:\n" +
                                tasks.get(index - 1).toString() + "\n" + line);
                    }
                } else {
                    if (prompt.contains("deadline")) {
                        String deadLine[] = Duke.extractDeadline(Duke.textAfter(prompt));
                        tasks.add(new Deadline(deadLine[0], deadLine[1]));

                    } else if (prompt.contains("event")) {
                        String eventData[] = Duke.extractEvent(Duke.textAfter(prompt));
                        tasks.add(new Event(eventData[2], eventData[0], eventData[1]));
                    } else if (prompt.contains("todo")) {
                        tasks.add(new ToDo(Duke.textAfter(prompt)));
                    } else {
                        throw new InvalidTaskException(
                                "☹ OOPS!!! I'm sorry, but I don't know what that means :-("
                        );
                    }
                    System.out.println(line + "Got it. I've added this task:\n "
                            + tasks.get(tasks.size() - 1) + "\n" +
                            "Now you have " + tasks.size() + " tasks in the list\n"
                            + line);
                }
                prompt = Duke.inputText();
            }
        } catch (InvalidTaskException e) {
            System.out.println(line +
                    e.getMessage() + "\n" +
                    line);
        } catch (InvalidArgumentException e) {
            System.out.println(line +
                    e.getMessage() + "\n" +
                    line);
        } catch(InvalidSerialException e) {
            System.out.println(line +
                    e.getMessage() + "\n" +
                    line);
        }
        System.out.println(line +
                " Bye. Hope to see you again soon!\n" +
                line);
    }
=======

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());

    }
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        Parser p = new Parser();
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                isExit = p.isExit(fullCommand);
                if (isExit) {
                    continue;
                }
                ui.showLine(); // show the divider line ("_______")
                p.parse(fullCommand, tasks, ui, storage);
            } catch (InvalidTaskException e) {
                ui.showLine();
                System.out.println(
                        e.getMessage() + "\n"
                );
            } catch (InvalidArgumentException e) {
                ui.showLine();
                System.out.println(
                        e.getMessage() + "\n"
                );
            } catch(InvalidSerialException e) {
                ui.showLine();
                System.out.println(
                        e.getMessage() + "\n"
                );
            } finally {
                ui.showLine();
            }
        }
        ui.showExitMsg();
    }

    public static void main(String[] args) {
        Duke chatBot = new Duke("./src/main/java/data/tasks.txt");
        chatBot.run();
    }

>>>>>>> branch-Level-7
    /**
     * Method to extract the content of the command
     * @param sent The String that contains the command content after
     *             the specified command.
     * @throws InvalidArgumentException if there is not content in the command
     * @return Sentinel object of type R.
     */
<<<<<<< HEAD
    public static String textAfter(String sent) throws InvalidArgumentException {
=======
    public String textAfter(String sent) throws InvalidArgumentException {
>>>>>>> branch-Level-7
        String reText = "";
        String command = "";
        boolean flag = false;
        for (int i = 0; i < sent.length(); i++) {
            char ch = sent.charAt(i);
            if (flag) {
                reText += ch;
            } else if (ch == ' ') {
                command += ch;
                flag = true;
            }
        }
        if(reText == "") {
            throw new InvalidArgumentException("☹ OOPS!!! The description of a "
                    + command + " cannot be empty.");
        }
        return reText;
    }

    /**
     * Extracts the end timing of the deadline.
     * @param text The input prompt
     * @return An array containing the command content and end timing of
     * the Deadline.
     */
<<<<<<< HEAD
    public static String[] extractDeadline(String text) {
=======
    public String[] extractDeadline(String text) {
>>>>>>> branch-Level-7
        String wrd = "";
        String str = "";
        int i;
        for (i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch == ' ') {
                if (wrd.equals("/by")) {
                    break;
                }
                str += wrd + " ";
                wrd = "";
            } else {
                wrd += ch;
            }
        }
        String deadArray[] = new String[2];
        deadArray[0] = str.trim();
        deadArray[1] = text.substring(i + 1);
        return deadArray;
    }

    /**
     * Method to extract the start and end timings of a deadline
     * @param text The input text
     * @return Returns and array containing the command content,
     * the start and the end times
     */
<<<<<<< HEAD
    public static String[] extractEvent(String text) {
=======
    public String[] extractEvent(String text) {
>>>>>>> branch-Level-7
        String str[] = new String[3];
        str[0] = "";
        str[1] = "";
        str[2] = "";
        String wrd = "";
        text = text + " ";
        boolean fromFlag = false;
        boolean toFlag = false;
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch == ' ') {
                if (wrd.equals("/from")) {
<<<<<<< HEAD
                   fromFlag = true;
=======
                    fromFlag = true;
>>>>>>> branch-Level-7
                } else if (wrd.equals("/to")) {
                    toFlag = true;
                    fromFlag = false;
                } else if (fromFlag) {
                    str[0] += wrd + " ";
                } else if (toFlag) {
                    str[1] += wrd + " ";
                } else {
                    str[2] += wrd + " ";
                }
                wrd = "";
            } else {
                wrd += ch;
            }
        }
        str[2] = str[2].trim();
        return str;
    }

    /**
     * The method evaluates the list of the passed tasks.
     * @param tasks The ArrayList containing all the passed commands
     * @return String of all the passed tasks
     */
<<<<<<< HEAD
    public static String getTasks(ArrayList<? extends Task> tasks) {
=======
    public String getTasks(ArrayList<? extends Task> tasks) {
>>>>>>> branch-Level-7
        String listedTasks = "";
        for(int i = 0; i < tasks.size(); i++) {
            listedTasks += (i + 1) + ". " + tasks.get(i).toString() + "\n";
        }
        return listedTasks;
    }

    /**
     * Takes input of the passed text
     * @return String containing the passed input
     */
<<<<<<< HEAD
    public static String inputText() {
=======
    public String inputText() {
>>>>>>> branch-Level-7
        Scanner sc =  new Scanner(System.in);
        String inp = sc.nextLine();
        return inp;
    }
<<<<<<< HEAD
=======

    /**
     * Prints the contents of the file stored at filePath
     * @param filePath Stores the path to the file in which tasks are stored
     * @throws FileNotFoundException It is thrown in a situation where the file
     * does not exist at the given filePath
     */
    public void printTasks(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    /**
     * Writes any changes in the tasks to the hard disk
     * @param filePath Stores the address to the file with the stored tasks
     * @param tasks The ArrayList containing all the tasks
     */
    public void writeUp(String filePath, ArrayList<? extends Task> tasks) {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(this.getTasks(tasks));
            fw.close();
            this.printTasks(filePath);
        } catch (IOException e) {
            System.out.println("An exception occurred: " + e.getMessage());
        }
    }

    /**
     * Converts the input text into its equivalent ToDo Task
     * @param text String to be converted to a ToDo task
     * @return The ToDo task converted from text
     */
    public ToDo taskToDo(String text) {
        ToDo t = new ToDo(text.substring(10).trim());
        if (text.charAt(7) == 'X') {
            t.markTask();
        }
        return t;
    }

    /**
     * Converts the input text into its equivalent Event Task
     * @param text String to be converted to Event Task
     * @return The Event task converted from text
     */
    public Event taskEvent(String text) {
        boolean isMarked = text.charAt(7) == 'X';
        String[] parts = text.split("[()]");
        String taskDescription = parts[0].trim().substring(10);
        String timeInfo = parts[1].trim();
        String[] timeParts = timeInfo.split("from:|to:");
        String startTime = timeParts[1].trim();
        String endTime = timeParts[2].trim();
        String[] extractedValues = { taskDescription, startTime, endTime };
        Event e = new Event(extractedValues[0], extractedValues[1], extractedValues[2]);
        if (isMarked) {
            e.markTask();
        }
        return e;
    }

    /**
     * Converts the input text into its equivalent Deadline Task
     * @param text The String to be converted to a Deadline Task
     * @return The Deadline Task from the converted String
     */
    public Deadline taskDeadline(String text) {
        boolean isMarked = text.charAt(7) == 'X';
        text = text.substring(10);
        String wrd = "";
        String str = "";
        int i;
        for (i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch == ' ') {
                if (wrd.equals("(by:")) {
                    break;
                }
                str += wrd + " ";
                wrd = "";
            } else {
                wrd += ch;
            }
        }
        String deadArray[] = new String[2];
        deadArray[0] = str.trim();
        deadArray[1] = text.substring(i + 1, text.length() - 1);
        Deadline d = new Deadline(deadArray[0], deadArray[1]);
        if (isMarked) {
            d.markTask();
        }
        return d;
    }

    /**
     * This method reads a file and converts its text into an ArrayList of Task objects
     * @param filePath The path to the destination where the Tasks are stored
     * @return ArrayList of Tasks
     */
    public ArrayList<Task> fileToTasks(String filePath) {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String prompt = s.nextLine();
                char ch = prompt.charAt(4);
                if (ch == 'T') {
                    tasks.add(taskToDo(prompt));
                } else if (ch == 'E') {
                    tasks.add(taskEvent(prompt));
                } else {
                    tasks.add(taskDeadline(prompt));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error! Could not find file!");
        }
        return tasks;
    }

>>>>>>> branch-Level-7
}
