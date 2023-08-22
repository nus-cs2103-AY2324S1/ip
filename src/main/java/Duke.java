import javax.sound.midi.SysexMessage;
import java.net.UnknownServiceException;
import java.util.Scanner;

public class Duke {
    static String name = "Nichbot";

//    static String[] tasks = new String[100];
    static int count = 0;
    // Assuming there will not be more than 100 tasks
    static Task[] tasks = new Task[100];

    // Level-0, Function to say introduce the chatbot
    public static void sayHello() {
        String greet = String.format(
                "____________________________________________________________\n" +
                        " Hello! I'm %s\n" +
                        " What can I do for you?\n" +
                        "____________________________________________________________\n", name);
        System.out.println(greet);
    }

    // Level-0, Function to say goodbye
    public static void sayGoodBye() {
        String bye =  ("Bye. Hope to see you again soon!\n" +
                "____________________________________________________________");
        System.out.println(bye);
    }

//  Level-1, Echo user input
    public static void echoUserInput(Task task) {
        System.out.println(task.getDescription() + "\n____________________________________________________________");
    }

//    Level-2, Add, list
    public static void addList(String input) {
        Task newTask = new Task(input);
        tasks[count++] = newTask;
        System.out.print("____________________________________________________________\n" + "added: ");
        echoUserInput(newTask);
    }

    public static void printList() {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < count; i++) {
            String current = String.format("%d:[%s] %s",i + 1, tasks[i].getStatusIcon(),tasks[i].getDescription());
            System.out.println(current);
        }
        System.out.print("____________________________________________________________\n");
    }

    public static void markDoneOrUndone(int count, boolean done) {
        if (count < 1 || count > 100) {
            System.out.println("Invalid Input written.");
            return;
        }
        Task current = tasks[count - 1];
        if (done) {
            current.setDone();
        } else {
            current.setNotDone();
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        sayHello();

        Scanner sc = new Scanner(System.in);
        String userInput = "";
        do {
            userInput = sc.nextLine();
            if (userInput.toLowerCase().equals("list")) {
                printList();
            } else if (userInput.length() > 5 && userInput.substring(0,4).toLowerCase().equals("mark")) {
                markDoneOrUndone(Character.getNumericValue(userInput.charAt(5)), true);
            } else if (userInput.length() > 7 && userInput.substring(0,6).toLowerCase().equals("unmark")) {
                markDoneOrUndone(Character.getNumericValue(userInput.charAt(7)), false);
            } else {
                addList(userInput);
            }
        } while (!userInput.toLowerCase().equals("bye"));

        sayGoodBye();
    }
}
