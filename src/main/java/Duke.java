import java.util.Scanner;

public class Duke {
//    public static String[] tasks = new String[100];
    private static Task[] tasks = new Task[100];
    private static final String lineSep = "-------------------------------";
    public static void main(String[] args) {
        System.out.println(lineSep);
        System.out.println("Hello! I'm YJ's Chatbot");
        System.out.println("What can I do for you?\n" + lineSep);
        Scanner scanner = new Scanner(System.in);
        String cmd = readCmd(scanner);
        int counter = 0;
        while (!cmd.equals("bye")) {
            if (cmd.equals("list")) {
                listTasks();
                System.out.println(lineSep);
            } else if (cmd.contains("mark ")) {  // works for both mark and unmark
                String[] split = cmd.split(" ");
                if (split.length > 1) {
                    markTask(split);
                }
            } else {
                System.out.println("added: " + cmd + "\n" + lineSep);
                tasks[counter] = new Task(cmd);
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

    private static void listTasks() {
        int tempCounter = 0;
        for (Task task : tasks) {
            // Don't print nulls
            if (task == null) { break; }
            System.out.println(tempCounter + "." + task);
            tempCounter++;
        }
    }

    private static void markTask(String[] split) {
        int idx = Integer.parseInt(split[1]);
        tasks[idx].setCompleted(split[0].equals("mark"));
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks[idx].toString());
        System.out.println(lineSep);
    }
}
