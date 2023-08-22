import java.util.Scanner;

public class Phi {
    // scanner object for user input
    static Scanner sc = new Scanner(System.in);
    // arrayList to store input text given
    static TaskList taskList = new TaskList();

    public static void main(String[] args) {

        greeting();

        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                taskList.printList();
            } else if (input.startsWith("mark")) {
                int number = Integer.parseInt(input.substring(5));
                taskList.doTask(number);
            } else if (input.startsWith("unmark")) {
                int number = Integer.parseInt(input.substring(7));
                taskList.undoTask(number);
            } else {
                taskList.addTask(input);
            }
            input = sc.nextLine();
        }

        goodbye();
    }

    public static void greeting() {
        String logo = "___ _  _ ___ \n"
                + "| _ \\ || |_ _|\n"
                + "|  _/ __ || | \n"
                + "|_| |_||_|___| \n";
        String greetingMsg = "Hellos! I'm PHI (Programmed Human Interaction) \n What can I do for you?";

        System.out.println(logo + greetingMsg);
    }

    public static void goodbye() {
        String exitMsg = "okk THANKS FOR COMING BYE!";
        System.out.println(exitMsg);
    }

}