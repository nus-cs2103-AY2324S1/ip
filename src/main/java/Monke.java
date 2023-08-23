import java.util.Scanner;

public class Monke {
    private static Task[] list = new Task[100];
    private static int index = 0;

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

    public static void listen() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            String[] temp = input.split(" ", 2);
            String command = temp[0];
            String args = temp.length > 1 ? temp[1] : "";
            Monke.printHorizontalLine();

            if (command.equals("bye")) {
                break;
            }

            try {
                Monke.execute(command, args);
            } catch (MonkeException e) {
                Monke.speak(e.getMessage());
            }
            Monke.printHorizontalLine();
        }
        sc.close();
    }

    public static void execute(String command, String args) throws MonkeException{
        switch (command) {
            case "list": {
                Monke.displayList();
                break;
            }
            case "mark": {
                int n = Monke.getListNumber(args);
                Task task = Monke.list[n - 1];
                task.mark();
                Monke.speak("Ooga booga! I've marked this task as done:");
                Monke.speak("\t" + task);
                break;
            }
            case "unmark": {
                int n = Monke.getListNumber(args);
                Task task = Monke.list[n - 1];
                task.unmark();
                Monke.speak("Ooga booga! I've marked this task as done:");
                Monke.speak("\t" + task);
                break;
            }
            case "todo": {
                Todo todo = Monke.getTodo(args);
                Monke.addToList(todo);
                break;
            }
            case "deadline": {
                Deadline deadline = Monke.getDeadline(args);
                Monke.addToList(deadline);
                break;
            }
            case "event": {
                Event event = Monke.getEvent(args);
                Monke.addToList(event);
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
            if (n > index) {
                throw new MonkeException("OOGA BOOGA!! Your number is out of range. :(");
            }
            return n;
        } catch (NumberFormatException e) {
            throw new MonkeException("OOGA BOOGA!! You must provide a number from the list. :(");
        }
    }

    public static Todo getTodo(String args) throws MonkeException {
        if (args.isEmpty()) {
            throw new MonkeException("OOGA BOOGA!! The description of a todo cannot be empty.");
        }
        return new Todo(args);
    }

    public static Deadline getDeadline(String args) throws MonkeException {
        if (!args.contains(" /by ")) {
            throw new MonkeException("You must format your deadline like this:\n" +
                    "\t\tdeadline <task> /by <deadline>");
        }
        String[] tmp = args.split(" /by ", 2);
        String description = tmp[0];
        String date = tmp[1];
        return new Deadline(description, date);
    }

    public static Event getEvent(String args) throws MonkeException {
        if (!args.contains(" /from ") || !args.contains(" /to ")) {
            throw new MonkeException("You must format your event like this:\n" +
                    "\t\tdeadline <task> /from <start time> /to <end time>");
        }
        String[] tmp = args.split(" /from ", 2);
        String description = tmp[0];
        String[] tmp2 = tmp[1].split(" /to ", 2);
        String start = tmp2[0];
        String end = tmp2[1];
        return new Event(description, start, end);
    }

    public static void addToList(Task task) {
        Monke.speak("Got it. I've added this task:");
        Monke.speak("\t" + task);
        Monke.list[index] = task;
        Monke.index++;
        Monke.speak("Now you have " + index + " tasks in the list.");
    }

    public static void displayList() {
        for (int i = 0; i < Monke.index; i++) {
            Monke.speak((i + 1) + ". " + Monke.list[i]);
        }
    }

    public static void main(String[] args) {
        Monke.printHorizontalLine();
        Monke.greet();
        Monke.listen();
        Monke.exit();
    }
}
