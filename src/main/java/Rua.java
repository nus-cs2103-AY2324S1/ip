import java.util.Scanner;

public class Rua {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String greeting = "____________________________________________________________\n" +
                " Hello! I'm Rua, your ChatBot\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        String goodbye= "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n";
        System.out.println(greeting);

        String command = sc.nextLine();
        while (!command.equals("bye")) {
            String message =  "____________________________________________________________\n" +
                    command + "\n" +
                    "____________________________________________________________\n";
            System.out.println(message);
            command = sc.nextLine();
        }
        System.out.println(goodbye);

    }
}
