import java.util.Scanner;
public class Eva {
    public static void main(String[] args) {
        String logo = "  ______          \n"
                + " |  ____|         \n"
                + " | |____   ____ _ \n"
                + " |  __\\ \\ / / _` |\n"
                + " | |___\\ V / (_| |\n"
                + " |______\\_/ \\__,_|\n";
        System.out.println("Hello from\n" + logo);
        Scanner scanner = new Scanner(System.in);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Eva");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
        scanner.close();
    }
}
