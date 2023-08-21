import java.util.Scanner;

public class URChatBot {
    public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    String logo =
            "         _____   _____\n"
                    + "| | | | /  ___| |   ) | \n"
                    + "| | | | | |     | ___ /\n"
                    + "| |_| | | |___  |   ) \\\n"
                    + "\\___,_| \\_____| |_____|\n";
        System.out.println("Hello! I'm URChatBot.\nWhat can I do for you?\n" + logo);

        sc.nextLine();
        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }
}
