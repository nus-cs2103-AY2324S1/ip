import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Encapsulates the logic of a Chat bot
 *
 * @author Rayson
 */
public class Duke {

    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<>();

        String LINE = "_______________________________________";
        String logo = "         _           _           _            _        _          _           _            _              _     _      _      \n" +
                "        /\\ \\        /\\ \\        / /\\         /\\ \\     /\\ \\       /\\ \\        /\\ \\         /\\ \\     _     /\\ \\ /_/\\    /\\ \\    \n" +
                "       /  \\ \\      /  \\ \\      / /  \\       /  \\ \\    \\ \\ \\     /  \\ \\      /  \\ \\       /  \\ \\   /\\_\\   \\ \\ \\\\ \\ \\   \\ \\_\\   \n" +
                "      / /\\ \\ \\    / /\\ \\ \\    / / /\\ \\__   / /\\ \\ \\   /\\ \\_\\   / /\\ \\ \\    / /\\ \\ \\     / /\\ \\ \\_/ / /   /\\ \\_\\\\ \\ \\__/ / /   \n" +
                "     / / /\\ \\_\\  / / /\\ \\_\\  / / /\\ \\___\\ / / /\\ \\_\\ / /\\/_/  / / /\\ \\_\\  / / /\\ \\ \\   / / /\\ \\___/ /   / /\\/_/ \\ \\__ \\/_/    \n" +
                "    / / /_/ / / / /_/_ \\/_/  \\ \\ \\ \\/___// / /_/ / // / /    / / /_/ / / / / /  \\ \\_\\ / / /  \\/____/   / / /     \\/_/\\__/\\    \n" +
                "   / / /__\\/ / / /____/\\      \\ \\ \\     / / /__\\/ // / /    / / /__\\/ / / / /   / / // / /    / / /   / / /       _/\\/__\\ \\   \n" +
                "  / / /_____/ / /\\____\\/  _    \\ \\ \\   / / /_____// / /    / / /_____/ / / /   / / // / /    / / /   / / /       / _/_/\\ \\ \\  \n" +
                " / / /\\ \\ \\  / / /______ /_/\\__/ / /  / / /   ___/ / /__  / / /\\ \\ \\  / / /___/ / // / /    / / /___/ / /__     / / /   \\ \\ \\ \n" +
                "/ / /  \\ \\ \\/ / /_______\\\\ \\/___/ /  / / /   /\\__\\/_/___\\/ / /  \\ \\ \\/ / /____\\/ // / /    / / //\\__\\/_/___\\   / / /    /_/ / \n" +
                "\\/_/    \\_\\/\\/__________/ \\_____\\/   \\/_/    \\/_________/\\/_/    \\_\\/\\/_________/ \\/_/     \\/_/ \\/_________/   \\/_/     \\_\\/  \n" +
                "                                                                                                                              ";

        Scanner scanner = new Scanner(System.in);
        System.out.println(LINE);
        System.out.println("Hello! I'm your personal AI");
        System.out.println(logo);
        System.out.println("What can I do for you?");
        System.out.println(LINE);

        try {
            while (true) {
                String input = scanner.nextLine();
                System.out.println(LINE);

                if (input.equalsIgnoreCase("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println(LINE);
                    break;
                }

                if (input.equalsIgnoreCase("list")) {
                    for (int i = 0; i < tasks.size(); i++) {
                        Task task = tasks.get(i);
                        System.out.println(String.format("%d. %s", i + 1, task));
                    }
                    System.out.println(LINE);
                    continue;
                }

                if (input.startsWith("mark")) {
                    int index = Integer.parseInt(input.replaceAll("[^0-9]", ""));
                    Task task = tasks.get(index - 1);
                    task.markDone();
                    System.out.println("Great job completing the task! I've marked it as done.");
                    System.out.println(task);
                    System.out.println(LINE);
                    continue;
                }

                if (input.startsWith("unmark")) {
                    int index = Integer.parseInt(input.replaceAll("[^0-9]", ""));
                    Task task = tasks.get(index - 1);
                    task.markUndone();
                    System.out.println("Oops... Did you mark it incorrectly?");
                    System.out.println(task);
                    System.out.println(LINE);
                    continue;
                }

                tasks.add(new Task(input));
                System.out.println(String.format("added: %s", input));
                System.out.println(LINE);

            }
        } catch (Exception e) {
            System.out.println("Respironix has encountered an issue; exiting");
        }
    }
}
