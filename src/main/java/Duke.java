import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String logo  =   "____    ____  ________   ___    ___   __________    _____\n"
                     + "\\   \\  /   / |  ____  |  |  |   |  |  |  _____  |  / ____|\n"
                     + " \\   \\/   /  | |    | |  |  |   |  |  |  |___|  |  | (___\n"
                      + "  \\      /   | |    | |  |  |   |  |  |   ______|  \\ ___ \\ \n"
                       + "   |    |    | |    | |  |  |   |  |  |  \\  \\           | |\n"
                       + "   |    |    | |____| |  |  |   |  |  |  | \\  \\     ____) |\n"
                       + "   |____|    |________|  \\_________/  |__|   \\__\\  |_____/     \n";

        String name = "Yours";


        System.out.println("Hello! I'm " + name);
        System.out.println(logo);
        System.out.println("____________________________________________________________________________________");
        System.out.println("What can I do for you?");


        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("   ____________________________________________________________________________________");
                System.out.println("   " + name + ": Bye. Hope to see you again soon!");
                break;
            } else {
                System.out.println("   ____________________________________________________________________________________");
                System.out.println("   " +  name + ": " + userInput);
            }
        }


    }
}
