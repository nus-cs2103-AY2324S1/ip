import java.util.Scanner;

public class Thea {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        greet();
        String command = input.nextLine();
        while(!command.equals("bye")) {
            System.out.println(command);
            command = input.nextLine();
        }
        exit();
    }
    public static void greet() {
        System.out.println("Hello! I'm Thea •ᴗ•\nHow can I help you?\n");
    }
    public static void exit() {
        System.out.println("I hope I made your day easier with my service. See you again! >ᴗ<");
    }
}
