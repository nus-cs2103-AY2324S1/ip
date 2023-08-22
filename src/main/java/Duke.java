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
        String[] list = new String[100];
        int listIndex = 0;

        while (true) {
            input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.print(line + "\n");
                for (int i = 0; i < listIndex; i++) {
                    System.out.println((i + 1) + ". " + list[i]);
                }
                System.out.print(line + "\n");
            } else {
                list[listIndex] = input;
                listIndex++;
                System.out.println(line + "added: " + input + "\n" + line);
            }
        }

        System.out.println(line +
                "Bye. Hope to see you again soon!\n" +
                line);
        scanner.close();
    }
}
