import java.util.Scanner;

public class Dude {

    public static void bye() {
        String greeting = "Bye. Hope to see you again soon!";
        System.out.println(greeting);
    }

    public static void main(String[] args) {
        String greeting = "Hello, I'm Dude! \n" +
                "What can I do for you?";
        System.out.println(greeting);
//        System.out.print( "Type some data for the program: " );

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                greeting = "Bye. Hope to see you again soon!";
                System.out.println(greeting);
                break;
            }

            System.out.println(input);
        }
    }
}