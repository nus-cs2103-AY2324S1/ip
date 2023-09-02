/**
 * The class with the main method where all the functionality begins.
 */

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

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

    /**
     * Method to extract the content of the command
     * @param sent The String that contains the command content after
     *             the specified command.
     * @throws InvalidArgumentException if there is not content in the command
     * @return Sentinel object of type R.
     */
    public String textAfter(String sent) throws InvalidArgumentException {
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
    public String[] extractDeadline(String text) {
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
    public String[] extractEvent(String text) {
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
                    fromFlag = true;
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
    public String getTasks(ArrayList<? extends Task> tasks) {
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
    public String inputText() {
        Scanner sc =  new Scanner(System.in);
        String inp = sc.nextLine();
        return inp;
    }

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
}
