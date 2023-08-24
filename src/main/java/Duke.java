import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line = "────────────────────────────────────────────────────────────────────\n";
        String logo = " ___  _ _                  ___  _          _    ___        _    ___ \n"
                + "|  _|| | | ___  _ _  _ _  |  _>| |_  ___ _| |_ | . > ___ _| |_ |_  |\n"
                + "| |  \\   // . \\| | || '_> | <__| . |<_> | | |  | . \\/ . \\ | |    | |\n"
                + "| |_  |_| \\___/`___||_|   `___/|_|_|<___| |_|  |___/\\___/ |_|   _| |\n"
                + "|___|                                                          |___|\n";

        String greet = line +
                "Hello! I'm\n"
                + logo
                + "What can I do for you?\n"
                + line;
        System.out.println(greet);
        Scanner input = new Scanner(System.in);

        Task[] list = new Task[100];
        int index = 0;
        while (true) {
            String userInput = input.nextLine();
            if (userInput.split(" ", 2)[0].equals("mark")) {
                int i = Integer.parseInt(userInput.split(" ", 2)[1]);
                list[i - 1].markAsDone();
                System.out.println(line
                        + "Nice! I've marked this task as done: \n"
                        + list[i - 1]
                        + "\n"
                        + line);
            } else if (userInput.split(" ", 2)[0].equals("unmark")) {
                int i = Integer.parseInt(userInput.split(" ", 2)[1]);
                list[i - 1].markAsNotDone();
                System.out.println(line
                        + "OK, I've marked this task as not done yet: \n"
                        + list[i - 1]
                        + "\n"
                        + line);
            } else if (userInput.equals("bye")) {
                break;
            } else if (userInput.equals("list")) {
                for (int i = 0; i < index; i++) {
                    System.out.println(Integer.toString(i + 1)
                            + ". "
                            + list[i]);
                }
            } else {
                list[index] = new Task(userInput);
                index++;
                System.out.println(line
                        + "added: "
                        + userInput
                        + "\n" + line);
            }
        }

        String sendOff = line
                + "Bye. Hope to see you again soon!\n"
                + line;
        System.out.println(sendOff);
    }
}