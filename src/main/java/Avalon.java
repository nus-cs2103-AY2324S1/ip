import java.util.Scanner;
public class Avalon {
    public static void main(String[] args) {
        String[] tasks = new String[100];
        int taskCount = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println(
                "   ____________________________________________________________\n" +
                "    Hello! I'm Arthur Pendragon.\n" +
                "    What can I do for you?\n" +
                "   ____________________________________________________________\n"
        );

        while (true){
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println(
                        "   ____________________________________________________________\n" +
                                "    Farewell. We will meet again!\n" +
                                "   ____________________________________________________________"
                );
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                if (taskCount == 0) {
                    System.out.println(
                            "   ____________________________________________________________\n" +
                                    "    You haven't added anything, my sire.\n" +
                                    "   ____________________________________________________________"
                    );
                } else {
                    System.out.println("   ____________________________________________________________list");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println("    " + (i + 1) + ". " + tasks[i]);
                    }
                    System.out.println("   ____________________________________________________________");
                }
            } else {
                tasks[taskCount] = userInput;
                taskCount++;
                System.out.println(
                        "   ____________________________________________________________\n" +
                                "    added: " +
                                userInput +
                                "\n   ____________________________________________________________\n"
                );
            }
        }
    }
}
