import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
public class Duke {
    static String indent = "   ";
    static String megaIndent = "     ";
    static String horizontalLines = indent  + "__________________________________________";
    static ArrayList<Task> taskArray = new ArrayList<>();
    static String dataFile = "data/duke.txt";

    public static void printWithIndent(String string) {
        System.out.println(horizontalLines);
        System.out.println(indent + string);
        System.out.println(horizontalLines);
    }

    /**
     * @return the last Task from the taskArray
     */
    public static Task getLastTask() {
        return taskArray.get(taskArray.size() - 1);
    }

    /**
     * displays the list of Tasks
     */
    public static void displayList() {
        System.out.println(horizontalLines);
        System.out.println(indent + "Here are the tasks in your list:");
        for (int i = 0; i < taskArray.size(); i++) {
            int num = i + 1;
            Task curr = taskArray.get(i);
            System.out.println(indent + num + "." + curr.toString());
        }
        System.out.println(horizontalLines);
    }

    /**
     * Everytime a Task is added to taskArray, clear the duke.txt file, then scan the whole
     * taskArray and rewrite the entire txt file
     *
     * Initially, I did a writeToFile method where everytime a Task is added to taskArray, write
     * the new task to duke.txt. However, this may cause some problems when it comes to updating
     * or deleting tasks from the file, so I changed the implementation to rewriting the entire txt
     * file everytime there is a change to the list. This causes a longer run time but since this mod
     * is not about run time, it should be fine.
     *
     * @param filePath hardcoded relative path to duke.txt
     * @throws IOException if the file at the filePath does not exist (I think)
     */
    private static void updateFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        // Clear the existing content by opening in write mode and immediately closing
        fw = new FileWriter(filePath, true);
        for (int i = 0; i < taskArray.size(); i++) {
            fw.write(taskArray.get(i).toFileString() + "\n");
        }
        fw.close();
    }
    /**
     * For an input such as 'todo borrow book', letter is 'T' and string is 'borrow book'
     * After adding the Task to taskArray, duke.txt is updated to reflect the new list
     *
     * @param letter the letter corresponding to the first letter of the Task
     * @param string the string corresponding to the chunk of text after the word todo, deadline, or event
     */
    public static void whichTask(String letter, String string) {
        try {
            if (letter.equals("T")) {
                taskArray.add(new ToDo(string));
            }
            if (letter.equals("D")) {
                taskArray.add(new Deadline(getDescription(string), getBy(string)));
            }
            if (letter.equals("E")) {
                taskArray.add(new Event(getDescription(string), getFrom(string), getTo(string)));
            }
            updateFile(dataFile);

            int arrayLength = taskArray.size();
            System.out.println(horizontalLines);
            System.out.println(indent + "Got it. I've added this task:");
            System.out.println(megaIndent + getLastTask().toString());
            System.out.println(indent + "Now you have " + arrayLength + " tasks in the list.");
            System.out.println(horizontalLines);
        } catch (DukeException | IOException e) {
            printWithIndent(e.getMessage());
        }
    }

    /**
     * For deadline and event Tasks, obtains the description of the Task (before the first slash)
     * For example, the input 'event project meeting /from Mon 2pm /to 4pm' will return 'project meeting'
     *
     * @param string of the Task
     * @return the description of the Task
     */
    public static String getDescription(String string) {
        int len = string.length();
        int count = 0;
        for (int i = 0; i < len; i++) {
            if (string.charAt(i) == '/') {
                break;
            }
            count++;
        }
        return string.substring(0, count);
    }

    /**
     * A method for the Deadline class to obtain the by part of the Task description
     * For example, the input 'deadline return book /by Sunday' will return 'Sunday'
     *
     * @param string the Task description
     * @return the deadline
     * @throws DukeException if the input string is formatted wrongly
     */
    public static String getBy(String string) throws DukeException {
        String slash = "/";
        int first = string.indexOf(slash);
        int second = first + 3;
        if (first == -1 || !string.substring(first, second).equals("/by")) {
            throw new DukeException("You need to add a by timing!");
        }
        return string.substring(first + 4); // returns "Sunday"
    }

    /**
     * A method for the Event class to obtain the from part of the Event description
     * For example, the input 'event project meeting /from Mon 2pm /to 4pm' will return 'Mon 2pm'
     *
     * @param string the Task description
     * @return the from part of the event
     * @throws DukeException
     */
    public static String getFrom(String string) throws DukeException {
        String slash = "/";
        int firstSlash = string.indexOf(slash);
        int secondSlash = string.indexOf(slash, firstSlash + 1);

        if (firstSlash == -1 || secondSlash == -1
                || !string.substring(firstSlash, firstSlash + 5).equals("/from")) {
            throw new DukeException("You need to add a /from and /to for events");
        }

        return string.substring(firstSlash + 6, secondSlash - 1);
    }
    /**
     * A method for the Event class to obtain the to part of the Event description
     * For example, the input 'event project meeting /from Mon 2pm /to 4pm' will return '4pm'
     *
     * @param string the Task description
     * @return the to part of the event
     * @throws DukeException
     */
    public static String getTo(String string) throws DukeException {
        String slash = "/";
        int firstSlash = string.indexOf(slash);
        int secondSlash = string.indexOf(slash, firstSlash + 1);

        if (!string.substring(secondSlash, secondSlash + 3).equals("/to")) {
            throw new DukeException("You need to add a /to for events");
        }
        return string.substring(secondSlash + 4);
    }

    /**
     * This method encapsulates the functionality of marking a task as completed or not
     * For example, the input 'mark 1' will mark the Task at position 0 at the TaskArray as 'marked'
     * After marking description, duke.txt is updated to reflect the new list
     *
     * @param string the input string
     */
    public static void markDescription(String string) {
        try {
            String clean = string.replaceAll("\\D+", ""); //remove non-digits
            int pos = Integer.parseInt(clean) - 1;
            Task curr = taskArray.get(pos);

            if (string.contains("unmark")) {
                curr.markAsUnDone();
                System.out.println(horizontalLines);
                System.out.println(indent + "OK, I've marked this task as not done yet:");
            } else if (string.contains("mark")) {
                curr.markAsDone();
                System.out.println(horizontalLines);
                System.out.println(indent + "Nice! I've marked this task as done:");
            }
            System.out.println(megaIndent + curr.getStatusIconWithBracket() + " " + curr.description);
            System.out.println(horizontalLines);
            updateFile(dataFile);
        } catch (IndexOutOfBoundsException | IOException e) {
            printWithIndent("You are trying to access a Task that does not exist!");
        }
    }

    /**
     * This method encapsulates deleting of a task from TaskArray
     * For example, the input 'delete 3' will delete the Task at position 2 of TaskArray
     * After deleting the Task, duke.txt is updated to reflect the new list
     *
     * @param string the input string
     */
    public static void deleteTask(String string) {
        try {
            String clean = string.replaceAll("\\D+", ""); //remove non-digits
            int pos = Integer.parseInt(clean);
            if (pos > taskArray.size()) {
                printWithIndent("You are trying to delete a Task that does not exist");
            } else {
                System.out.println(horizontalLines);
                System.out.println(indent + "Noted. I've removed this task:");
                System.out.println(megaIndent + taskArray.get(pos - 1).toString());
                taskArray.remove(pos - 1);
                System.out.println(indent + "Now you have " + taskArray.size() + " tasks in the list.");
                System.out.println(horizontalLines);
                updateFile(dataFile);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * This function is called when the main method is run. It will print out all the tasks
     * in the list
     *
     * @param filePath the file where the lists of Tasks are stored. It is hardcoded to be "data/duke.txt"
     * @throws FileNotFoundException if the file at this filePath is not found (though I'm not sure
     * when this will happen)
     */
    private static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    public static void main(String[] args) {
        String name = "zac";
        Scanner obj = new Scanner(System.in);

        System.out.println(horizontalLines);
        System.out.println(indent + "Hello! I'm " + name);
        System.out.println(indent + "What can I do for you?");
        System.out.println(horizontalLines);

        try {
            printFileContents(dataFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        while (true) {
            String userInput = obj.nextLine();
            if (userInput.equals("list")) {
                displayList();
            } else if (userInput.equals("bye")) {
                printWithIndent("Bye. Hope to see you again soon!");
                break;
            } else if (userInput.contains("unmark")) {
                markDescription(userInput);
            } else if (userInput.contains("mark")) {
                markDescription(userInput);
            } else if (userInput.contains("todo")) {
                try {
                    whichTask("T", userInput.substring(5));
                } catch (StringIndexOutOfBoundsException e) {
                    printWithIndent("OOPS!!! The description of a todo cannot be empty.");
                }
            } else if (userInput.contains("deadline")) {
                try {
                    whichTask("D", userInput.substring(9));
                } catch (StringIndexOutOfBoundsException e) {
                    printWithIndent("OOPS!!! The description of a deadline cannot be empty.");
                }
            } else if (userInput.contains("event")) {
                try {
                    whichTask("E", userInput.substring(6));
                } catch (StringIndexOutOfBoundsException e) {
                    printWithIndent("OOPS!!! The description of a deadline cannot be empty.");
                }
            } else if (userInput.contains("delete")) {
                deleteTask(userInput);
            } else {
                printWithIndent("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }
}
