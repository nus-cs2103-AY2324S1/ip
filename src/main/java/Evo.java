import java.util.Objects;
import java.util.Scanner;


public class Evo {
    public static void main(String[] args) {
        String logo = " _____\n"
                + "|  ___| \n"
                + "| |___ __    __  _____ \n"
                + "|  ___|\\ \\  / / |     | \n"
                + "| |___  \\ \\/ /  |     |  \n"
                + "|_____|  \\__/   |_____|  \n";
        System.out.println("Hello from\n" + logo);

        String welcomeMsg = "Hello! I'm Evo.\n" + "What can I do for you?\n";
        String byeMsg = "Bye. Hope to see you again soon!";

        System.out.println(welcomeMsg);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String instruction = scanner.nextLine();
            if (Objects.equals(instruction, "bye")) {
                System.out.println(byeMsg);
                break;
            }
            System.out.println(instruction);
        }
    }
}
