import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello, it's Spot!");
        String goodbye = "Spot's going to take a nap now. Goodnight!";
        String[] tasks = new String[100];
        int index = 0;
        Scanner myObj = new Scanner(System.in);
        String command = myObj.nextLine();  // Read user input
        while (!command.equals("bye")) {
            if (!command.equals("list")) {
                tasks[index] = command;
                System.out.println("added: " + command);
                index += 1;
            } else {
                for (int i = 0; i < index; i++) {
                    int position = i + 1;
                    System.out.println(position + ". " + tasks[i]);
                }
            }
            command = myObj.nextLine();
        }
        System.out.println(goodbye);
    }
}
