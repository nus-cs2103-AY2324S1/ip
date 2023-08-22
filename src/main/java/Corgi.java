import java.util.Scanner;

public class Corgi {
    public static void main(String[] args) {
        Corgi bot = new Corgi();
        bot.start();
    }

    public void start() {
        String logo = "  ____ ___  ____   ____ ___\n"
                + " / ___/ _ \\|  _ \\ / ___|_ _|\n"
                + "| |  | | | | |_) | |  _ | |\n"
                + "| |__| |_| |  _ <| |_| || |\n"
                + " \\____\\___/|_| \\_\\\\____|___|\n";
        System.out.println(logo);
        System.out.println("Woof! I'm Corgi!");
        System.out.println("What can I do for you, hooman?\n");

        Scanner sc = new Scanner(System.in);

        while(true) {
            String userInput = sc.nextLine();

            if (!userInput.toLowerCase().equals("bye")) {
                System.out.println(userInput);
            } else {
                 System.out.println("Bye, take care and see you soon! *tail wags*");
                 break;
            }

            System.out.println("------------------------------------------------------------");
        }

        sc.close();
    }
}
