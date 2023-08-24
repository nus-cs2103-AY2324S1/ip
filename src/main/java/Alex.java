import java.util.Scanner;

public class Alex {
    public static void main(String[] args) {
        String greeting = "_____________________________________________________________\n"
                + "Hello! I'm your personal task assistant, Alex\n"
                + "What can I do for you today?\n\n"
                + "_____________________________________________________________\n";

        String bye = "_____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "_____________________________________________________________\n";

        System.out.println(greeting);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String userInput = scanner.nextLine();
            int inputLength = userInput.length();
            if (userInput.equals("bye")) {
                System.out.println(bye);
                break;
            } else if(userInput.equals("list")) {
                System.out.println("_____________________________________________________________\n"
                                 + UserInputStorage.printAllContent()
                                 + "_____________________________________________________________\n"
                );
            } else if (inputLength >= 6 && userInput.substring(0, 4).equals("mark")) {
                int index = Integer.parseInt(userInput.substring(5, 6));
                Task targetedTask = UserInputStorage.getTaskByIndex(index);
                targetedTask.mark();
            } else if (inputLength >= 8 && userInput.substring(0, 6).equals("unmark")) {
                int index = Integer.parseInt(userInput.substring(7, 8));
                Task targetedTask = UserInputStorage.getTaskByIndex(index);
                targetedTask.unmark();
            } else {
                Task newTask = new Task(userInput);
                UserInputStorage.store(newTask);
                System.out.println("_____________________________________________________________\n"
                        + "added: "
                        + userInput
                        + "\n"
                        + "_____________________________________________________________\n"
                );
            }
        }
    }
}
