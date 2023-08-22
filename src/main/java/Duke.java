import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = ">. <\n";
        String name = "your father";
        String line = "_________________________\n";
        System.out.println(logo +
                line +
                "Hello! I'm " + name + "\n" +
                "What can I do for you?\n" +
                line);

        Scanner scanner = new Scanner(System.in);
        String input;

        Task[] tasks = new Task[100];
        int taskIndex = 0;

        while (true) {
            input = scanner.nextLine().toLowerCase();
            String[] words = input.split(" ");

            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println(line + "\nHere are the tasks in your list:");
                for (int i = 0; i < taskIndex; i++) {
                    System.out.println((i + 1) + "." + tasks[i]);
                }
                System.out.println(line);
            } else if (words[0].equals("mark") && words.length > 1) {
                int index = Integer.parseInt(words[1]) - 1;
                tasks[index].markDone();
                System.out.println(line + "\nNice! I've marked this task as done:\n  " +
                        tasks[index] +
                        "\n" +
                        line);
            } else if (words[0].equals("unmark") && words.length > 1) {
                int index = Integer.parseInt(words[1]) - 1;
                tasks[index].unmarkDone();
                System.out.println(line + "\nOK, I've marked this task as not done yet:\n  " +
                        tasks[index] +
                        "\n" +
                        line);
            } else {
                tasks[taskIndex] = new Task(input);
                taskIndex++;
                System.out.println(line + "added: " + input + "\n" + line);
            }
        }

        System.out.println(line +
                "Bye. Hope to see you again soon!\n" +
                line);
        scanner.close();
    }
}
