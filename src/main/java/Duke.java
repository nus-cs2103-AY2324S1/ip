import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Tasks[] task = new Tasks[100];
        int count = 0;

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
                System.out.println("   ____________________________________________________________________________________");
                break;
            } else if (userInput.equals("list")) {
                System.out.println("   ____________________________________________________________________________________");
                System.out.println("   " + name + ": Here are the tasks in your list :");
                for (int i = 0 ; i < count; i ++) {
                    int j = i + 1;
                    System.out.println("     " + j + ". " +  task[i].toString());
                }
                System.out.println("   ____________________________________________________________________________________");
            } else {
                Tasks newtask = new Tasks(userInput);
                System.out.println("   ____________________________________________________________________________________");
                System.out.println("   " +  name + ": Help you added a new task - " + userInput);
                System.out.println("   ____________________________________________________________________________________");
                task[count] = newtask;
                count++;
            }
        }
    }
}
