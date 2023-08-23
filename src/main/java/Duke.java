import java.util.Scanner;

public class Duke {

    public static final String HORIZONTAL_LINE = "____________________________________________________________";

    public static void main(String[] args) {
        String name = "Ip Bot";
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello I'm " + name + "!");
        System.out.println("While I may not be able to fight like Ip Man, I can assist you in other areas!");
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE);

        Scanner scanner = new Scanner(System.in);

        while(true) {
            String command = scanner.nextLine();
            if(command.strip().equalsIgnoreCase("bye")){
                break;
            }
            System.out.println(HORIZONTAL_LINE);
            System.out.println(command);
            System.out.println(HORIZONTAL_LINE);
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }
}
