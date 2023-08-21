import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String horizontalLines = "_______________________________________";
        String name = "zac";
        Scanner obj = new Scanner(System.in);

        System.out.println(horizontalLines);
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        System.out.println(horizontalLines);


        while (true) {
            String userInput = obj.nextLine();
            if (userInput.equals("bye")) {
                break;
            }
            System.out.println(horizontalLines);
            System.out.println(userInput);
            System.out.println(horizontalLines);
        }

        System.out.println(horizontalLines);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontalLines);
    }
}
