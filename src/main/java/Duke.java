import java.util.Scanner;

public class Duke {
    static String name = "Nichbot";


    // Function to say introduce the chatbot
    public static void sayHello() {
        String greet = String.format(
                "____________________________________________________________\n" +
                        " Hello! I'm %s\n" +
                        " What can I do for you?\n" +
                        "____________________________________________________________\n", name);
        System.out.println(greet);
    }

    // Function to say goodbye
    public static void sayGoodBye() {
        String bye =  ("Bye. Hope to see you again soon!\n" +
                "____________________________________________________________");
        System.out.println(bye);
    }

    public static void echoUserInput(String input) {
        String echo = String.format( "____________________________________________________________\n"
                + " %s\n"
                + "____________________________________________________________\n", input);
        System.out.println(echo);
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
            echoUserInput(userInput);
        } while (!userInput.toLowerCase().equals("bye"));
        
        sayGoodBye();
    }
}
