import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        String name = "Duke";
        String line = "___________________________________\n";
        String exit = line + "Bye Bye. Hope to see you again soon!\n" + line;

        System.out.println(line + "Hello! I'm " + name + "\nWhat can I do for you?\n" + line);

        Scanner scan = new Scanner(System.in);
        int counter = 0;
        Task[] list = new Task[100];

        while (true) {

            String input = scan.nextLine();
            String[] arr = input.split(" ", 2);

            if ("bye".equals(input)) {
                System.out.println(exit);
                break;

            } else if (arr[0].equals("mark")) {
                int amt = Integer.parseInt(arr[1].strip()) - 1;
                System.out.println(line);
                list[amt].mark();
                System.out.println(line);

            } else if (arr[0].equals("unmark")) {
                int amt = Integer.parseInt(arr[1].strip()) - 1;
                System.out.println(line);
                list[amt].unMark();
                System.out.println(line);

            } else if ("list".equals(input.strip())) {
                System.out.println(line + "Here are the tasks in your list:");
                for (int i = 0; i < counter; i++) {
                    System.out.println(i + 1 + ". " + list[i].toString());
                }
                System.out.println(line);

            } else {
                Task newTask = new Task(input);
                list[counter] = newTask;
                counter++;
                System.out.println(line + "added: " + input + "\n" + line);
            }
        }
    }
}
