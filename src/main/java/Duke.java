import java.util.Scanner;
public class Duke {
    static String indent = "   ";
    static String megaIndent = "     ";
    static String horizontalLines = indent  + "__________________________________________";
    static Task taskArray[] = new Task[100];
    static int count = 0;
    public static void printWithIndent(String string) {
        System.out.println(horizontalLines);
        System.out.println(indent + string);
        System.out.println(horizontalLines);
    }
    public static void displayList() {
        System.out.println(horizontalLines);
        System.out.println(indent + "Here are the tasks in your list:");
        for (int i = 0; i < count; i++) {
            int num = i + 1;
            Task curr = taskArray[i];
            System.out.println(indent + num + "." + curr.toString());
        }
        System.out.println(horizontalLines);
    }
    public static void whichTask(String letter, String string) {
        try {
            if (letter == "T") {
                taskArray[count] = new ToDo(string);
            }
            if (letter == "D") {
                taskArray[count] = new Deadline(getDescription(string), getBy(string));
            }
            if (letter == "E") {
                taskArray[count] = new Event(getDescription(string), getFrom(string), getTo(string));
            }
            System.out.println(horizontalLines);
            System.out.println(indent + "Got it. I've added this task:");
            System.out.println(megaIndent + taskArray[count].toString());
            count++;
            System.out.println(indent + "Now you have " + count + " tasks in the list.");
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
            Task curr = taskArray[pos];

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
        } catch (NullPointerException e) {
            printWithIndent("You are trying to access a Task that does not exist!");
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
                continue;
            } else if (userInput.equals("bye")) {
                printWithIndent("Bye. Hope to see you again soon!");
                break;
            } else if (userInput.contains("unmark")) {
                markDescription(userInput);
                continue;
            } else if (userInput.contains("mark")) {
                markDescription(userInput);
                continue;
            } else if (userInput.contains("todo")) {
                try {
                    whichTask("T", userInput.substring(5));
                } catch (StringIndexOutOfBoundsException e) {
                    printWithIndent("OOPS!!! The description of a todo cannot be empty.");
                } finally {
                    continue;
                }
            } else if (userInput.contains("deadline")) {
                try {
                    whichTask("D", userInput.substring(9));
                } catch (StringIndexOutOfBoundsException e) {
                    printWithIndent("OOPS!!! The description of a deadline cannot be empty.");
                } finally {
                    continue;
                }
            } else if (userInput.contains("event")) {
                try {
                    whichTask("E", userInput.substring(6));
                } catch (StringIndexOutOfBoundsException e) {
                    printWithIndent("OOPS!!! The description of a deadline cannot be empty.");
                } finally {
                    continue;
                }
            } else {
                printWithIndent("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }
}
