import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello, it's Spot!");
        String goodbye = "Spot's going to take a nap now. Goodnight!";
        Scanner myObj = new Scanner(System.in);
        String command = myObj.nextLine();  // Read user input
        while (!command.equals("bye")) {
            System.out.println(command);
            command = myObj.nextLine();
        }
        System.out.println(goodbye);
    }
}
