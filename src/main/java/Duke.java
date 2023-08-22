import java.util.Scanner;
public class Duke {
    static String indent = "   ";
    static String megaIndent = "     ";
    static String horizontalLines = indent  + "__________________________________________";
    static Task taskArray[] = new Task[100];
    static int count = 0;
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
        System.out.println(megaIndent + taskArray[count].toS
                tring());
        count++;

        System.out.println(indent + "Now you have " + count + " tasks in the list.");
        System.out.println(horizontalLines);
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

    public static String getBy(String string) {
        String by = "by";
        int i = string.indexOf(by) + 3;
        return string.substring(i); // returns "Sunday"
    }

    public static String getFrom(String string) {
        String from = "from";
        int first = string.indexOf(from) + 5;
        String to = "to";
        int second = string.indexOf(to) - 2;
        return string.substring(first, second);
    }
    public static String getTo(String string) {
        String to = "to";
        int num = string.indexOf(to) + 3;
        return string.substring(num);
    }

    public static void markDescription(String string) {
        String clean = string.replaceAll("\\D+",""); //remove non-digits
        int pos = Integer.parseInt(clean) - 1;
        Task curr = taskArray[pos];

        System.out.println(horizontalLines);
        if (string.contains("unmark")) {
            curr.markAsUnDone();
            System.out.println(indent + "OK, I've marked this task as not done yet:");
        } else if (string.contains("mark")) {
            curr.markAsDone();
            System.out.println(indent + "Nice! I've marked this task as done:");
        }
        System.out.println(megaIndent + curr.getStatusIcon() + " " + curr.description);
        System.out.println(horizontalLines);
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
            }
            if (userInput.contains("unmark")) {
                markDescription(userInput);
                continue;
            }
            if (userInput.contains("mark")) {
                markDescription(userInput);
                continue;
            }
            if (userInput.contains("todo")) {
                whichTask("T", userInput.substring(5));
                continue;
            }
            if (userInput.contains("deadline")) {
                whichTask("D", userInput.substring(9));
                continue;
            }
            if (userInput.contains("event")) {
                whichTask("E", userInput.substring(6));
                continue;
            }

            if (userInput.equals("bye")) {
                System.out.println(horizontalLines);
                System.out.println(indent + "Bye. Hope to see you again soon!");
                System.out.println(horizontalLines);
                break;
            }
        }
    }
}
