import java.util.Scanner;

public class Duke {
    public static String[] tasks = new String[100];
    public static void main(String[] args) {
        String lineSep = "-------------------------------";
        System.out.println(lineSep);
        System.out.println("Hello! I'm YJ's Chatbot");
        System.out.println("What can I do for you?\n" + lineSep);
        Scanner scanner = new Scanner(System.in);
        String cmd = readCmd(scanner);
        int counter = 0;
        while (!cmd.equals("bye")) {
            if (cmd.equals("list")) {
                int tempCounter = 0;
                for (String task : tasks) {
                    // Don't print nulls
                    if (task == null) { break; }
                    System.out.println(tempCounter + ". " + task);
                    tempCounter++;
                }
                System.out.println(lineSep);
            } else {
                System.out.println("added: " + cmd + "\n" + lineSep);
                tasks[counter] = cmd;
                counter++;
            }
            cmd = readCmd(scanner);
        }

        // Close scanner, must be done here because if I try to close in readCmd it'll close the System.in stream.
        scanner.close();

        System.out.println("Bye. Hope to see you soon!\n" + lineSep);
    }

    public static String readCmd(Scanner scanner) {
        // Read user input
        String cmd = scanner.nextLine();
        return cmd;
    }
}
