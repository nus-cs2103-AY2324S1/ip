import java.util.Scanner;

public class Max {
    private static String line = "____________________________________________________________";
    public static void greet() {
        String logo = ".___  ___.      ___      ___   ___ \n" +
                "|   \\/   |     /   \\     \\  \\ /  / \n" +
                "|  \\  /  |    /  ^  \\     \\  V  /  \n" +
                "|  |\\/|  |   /  /_\\  \\     >   <   \n" +
                "|  |  |  |  /  _____  \\   /  .  \\  \n" +
                "|__|  |__| /__/     \\__\\ /__/ \\__\\ \n" +
                "                                   ";
        System.out.println("     Hello from\n" + logo);
        System.out.println(Max.line);
        System.out.println("     Hello! I'm Max! \n" + "How may I assist you?");
        System.out.println(Max.line);
    }

    public static void echo(String string) {
        System.out.println("     " + string);
        System.out.println(Max.line);
    }

    public static void exit() {
        System.out.println("     Bye! Please come again!");
        System.out.println(Max.line);
    }
    public static void main(String[] args) {
        Max.greet();

        Scanner input = new Scanner(System.in);
        boolean scannerOpen = true;

        // User is interacting with chatbot
        while (scannerOpen) {
            String command = input.nextLine();

            if (command.equals("bye")) {
                scannerOpen = false;
            } else {
                Max.echo(command);
            }
        }

        // User has exited the chatbot
        input.close();
        Max.exit();
    }
}
