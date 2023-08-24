import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "Raman");*/

        String greeting = "Hello, I'm Capt. Price! What can I do for you today, Sergeant?";
        String horizontalBar = "-------------------------------------------------";
        String exitGreeting = "Over and out. See you next mission!";

        System.out.println(botMessage(greeting));

        ArrayList<Task> list = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        while (!userInput.equalsIgnoreCase("bye")) {

            String botOutput = "";

            if (userInput.equalsIgnoreCase("list")) {
                botOutput = botOutput + "Here are the tasks in your list: \n    ";
                for (int i = 1; i <= list.size(); i++) {
                    botOutput = botOutput + i + "." + " " + list.get(i-1) + "\n    ";
                }
            } else if (userInput.startsWith("mark ")) {
                botOutput = botOutput + "Nice! I've marked this task as done: \n    ";

                try {

                    int taskNo = Integer.parseInt(userInput.substring(5));
                    if (taskNo > list.size() | taskNo < 1) {
                        throw new InvalidTaskException("Please enter valid Task No. to mark!");
                    }
                    Task x = list.get(taskNo - 1);
                    x.markAsDone();
                    botOutput = botOutput + x;

                } catch (NumberFormatException e) {

                    botOutput = "Invalid Input String!!";

                } catch (InvalidTaskException d) {

                    botOutput = d.getMessage();

                }

            } else if (userInput.startsWith("unmark ")) {
                botOutput = botOutput + "Ok, I've marked this task as not done yet: \n    ";

                try {

                    int taskNo = Integer.parseInt(userInput.substring(7));
                    if (taskNo > list.size() | taskNo < 1) {
                        throw new InvalidTaskException("Please enter valid Task No. to unmark!");
                    }
                    Task x = list.get(taskNo - 1);
                    x.markAsUndone();
                    botOutput = botOutput + x;

                } catch (NumberFormatException e) {

                    botOutput = "Invalid Input String!!";

                } catch (InvalidTaskException d) {
                    botOutput = d.getMessage();
                }

            } else if (userInput.startsWith("delete ")) {
                botOutput = botOutput + "Noted. I've removed this task: \n    ";

                try {

                    int taskNo = Integer.parseInt(userInput.substring(7));
                    if (taskNo > list.size() | taskNo < 1) {
                        throw new InvalidTaskException("Please enter valid Task No. to delete!");
                    }
                    Task x = list.get(taskNo - 1);
                    list.remove(x);
                    botOutput = botOutput + x;

                } catch (NumberFormatException e) {

                    botOutput = "Invalid Input String!!";

                } catch (InvalidTaskException d) {
                    botOutput = d.getMessage();
                }
            } else {

                try {
                    Task t = Task.taskCon(userInput);
                    list.add(t);
                    botOutput = botOutput + "added: " + t + "\n    Now you have " + list.size() + " tasks in the list.";
                } catch (InvalidCommandException e) {
                    botOutput = "OOPS!!! I'm sorry, but I'm afraid I don't comprehend Sergeant!";
                } catch (InvalidTaskCreationException t) {
                    botOutput = t.getMessage();
                }

            }

            System.out.println(botMessage(botOutput));
            userInput = scanner.nextLine();
        }

        System.out.println(botMessage(exitGreeting));

    }

    public static String botMessage(String message) {
        String space = "    ";
        String horizontalBar = "-------------------------------------------------";
        return space + horizontalBar + "\n" + space + message + "\n" + space + horizontalBar;
    }
}
