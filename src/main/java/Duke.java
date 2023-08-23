import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    static String indent = "   ";
    static String megaIndent = "     ";
    static String horizontalLines = indent  + "__________________________________________";
    static ArrayList<Task> taskArray = new ArrayList<>();
    public static void printWithIndent(String string) {
        System.out.println(horizontalLines);
        System.out.println(indent + string);
        System.out.println(horizontalLines);
    }
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
            int arrayLength = taskArray.size();
            System.out.println(horizontalLines);
            System.out.println(indent + "Got it. I've added this task:");
            System.out.println(megaIndent + taskArray.get(arrayLength - 1).toString());
            System.out.println(indent + "Now you have " + arrayLength + " tasks in the list.");
            System.out.println(horizontalLines);
        } catch (DukeException e) {
            printWithIndent(e.getMessage());
        }
    }

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

    public static String getBy(String string) throws DukeException {
        String slash = "/";
        int first = string.indexOf(slash);
        int second = first + 3;
        if (first == -1 || !string.substring(first, second).equals("/by")) {
            throw new DukeException("You need to add a by timing!");
        }
        return string.substring(first + 4); // returns "Sunday"
    }

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
    public static String getTo(String string) throws DukeException {
        String slash = "/";
        int firstSlash = string.indexOf(slash);
        int secondSlash = string.indexOf(slash, firstSlash + 1);

        if (!string.substring(secondSlash, secondSlash + 3).equals("/to")) {
            throw new DukeException("You need to add a /to for events");
        }
        return string.substring(secondSlash + 4);
    }

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
            System.out.println(megaIndent + curr.getStatusIcon() + " " + curr.description);
            System.out.println(horizontalLines);
        } catch (IndexOutOfBoundsException e) {
            printWithIndent("You are trying to access a Task that does not exist!");
        }
    }

    public static void deleteTask(String string) {
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
        }
    }

    public static void main(String[] args) {
        String name = "zac";
        Scanner obj = new Scanner(System.in);

        System.out.println(horizontalLines);
        System.out.println(indent + "Hello! I'm " + name);
        System.out.println(indent + "What can I do for you?");
        System.out.println(horizontalLines);

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
