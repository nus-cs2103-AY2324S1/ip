import javax.sound.midi.SysexMessage;
import java.util.Scanner;

public class Duke {
    static String name = "Nichbot";
    // Assuming there will not be more than 100 tasks
    static String[] tasks = new String[100];
    static int count = 0;

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
    public static void echoUserInput(String input) {
        System.out.println(input + "\n____________________________________________________________");
    }

//    Level-2, Add, list
    public static void addList(String input) {
        tasks[count++] = input;
        System.out.print("____________________________________________________________\n" + "added: ");
        echoUserInput(input);
    }

    public static void printList() {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < count; i++) {
            String current = String.format("%d: %s",i + 1,tasks[i]);
            System.out.println(current);
        }
        System.out.print("____________________________________________________________\n");
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
            } else {
                addList(userInput);
            }
        } while (!userInput.toLowerCase().equals("bye"));

        sayGoodBye();
    }
}
