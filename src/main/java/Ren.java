import java.util.Scanner;

public class Ren {
    public static void main(String[] args) {
        String LS_COMMAND = "list";
        String EXIT_COMMAND = "bye";
        String MARK_COMMAND = "mark";
        String UNMARK_COMMAND = "unmark";
        TaskList tasks = new TaskList();

        Scanner input = new Scanner(System.in);
        String welcomeMsg = "____________________________________________________________\n" +
                " Hello! I'm Ren\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        String goodbyeMsg = "____________________________________________________________\n" +
                " Bye! Pleasure speaking with you :) \n" +
                "____________________________________________________________\n";
        System.out.println(welcomeMsg);
        String inputStr = input.nextLine();
        while (!inputStr.equals(EXIT_COMMAND)) {
            String[] commandArr = inputStr.split(" ");
            if (commandArr[0].equals(LS_COMMAND)) {
                System.out.println("____________________________________________________________\n");
                tasks.listTasks();
                System.out.println("____________________________________________________________\n");
            } else if (commandArr[0].equals(MARK_COMMAND) || commandArr[0].equals(UNMARK_COMMAND)) {
                Task task = tasks.toggleTask(commandArr);
                System.out.println("____________________________________________________________\n" +
                        String.format("Marked as %s!\n %s\n",
                                commandArr[0].equals(MARK_COMMAND) ? "done" : "undone",
                                task) +
                        "____________________________________________________________\n");
            } else {

                tasks.addTask(inputStr);

                System.out.println("____________________________________________________________\n" +
                        String.format("Added %s\n", inputStr) +
                        "____________________________________________________________\n");
            }

            inputStr = input.nextLine();
        }

        System.out.println(goodbyeMsg);
    }
}
