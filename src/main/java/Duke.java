import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ██████╗██╗  ██╗ █████╗ ████████╗████████╗██╗   ██╗\n"
                + "██╔════╝██║  ██║██╔══██╗╚══██╔══╝╚══██╔══╝╚██╗ ██╔╝\n"
                + "██║     ███████║███████║   ██║      ██║    ╚████╔╝ \n"
                + "██║     ██╔══██║██╔══██║   ██║      ██║     ╚██╔╝  \n"
                + "╚██████╗██║  ██║██║  ██║   ██║      ██║      ██║   \n"
                + " ╚═════╝╚═╝  ╚═╝╚═╝  ╚═╝   ╚═╝      ╚═╝      ╚═╝   \n";


        System.out.println("------------------------------------------");
        System.out.println("Hi!! I am\n" + logo);
        System.out.println("What brings you here today?");
        System.out.println("------------------------------------------");

        ArrayList<String> tasks = new ArrayList<>();

        Scanner input = new Scanner(System.in);
        while (true) {
            String cmd = input.nextLine();
            if (cmd.equals("bye")) {
                System.out.println("Oh.. bye");
                break;
            } else if (cmd.equals("list")) {
                System.out.println("------------------------------------------");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
                System.out.println("------------------------------------------");
            } else {
                tasks.add(cmd);
                System.out.println("------------------------------------------");
                System.out.println("added: " + cmd);
                System.out.println("------------------------------------------");
            }
        }
    }
}
