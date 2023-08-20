import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
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

                if (input.equalsIgnoreCase("bye")) {
                    System.out.println(LINE);
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println(LINE);
                    break;
                }

                System.out.println(LINE);
                System.out.println(input);
                System.out.println(LINE);
            }
        } catch (Exception e) {
            System.out.println("Respironix has encountered an issue; exiting");
        }
    }
}
