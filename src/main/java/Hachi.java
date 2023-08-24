import java.util.Scanner;

public class Hachi {

    public static void main(String[] args) {
        String name = "Hachi";
        Task[] tasks = new Task[100];
        int currIndex = 0;
        line();
        System.out.println("Hello! I'm " + name + "\nWhat cam I do for you?");
        line();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine();
            String[] words = command.split(" ");
            line();
            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (command.equals("list")) {
                for (int i = 0; i < currIndex; i++) {
                    int num = i + 1;
                    System.out.println(num + ". " + tasks[i]);
                }
            } else if (words[0].equals("mark")) {
                try {
                    int number = Integer.parseInt(words[1]);
                    int i = number - 1;
                    tasks[i].mark();
                    System.out.println("Nice! I've marked this task as done");
                    System.out.println("   " + tasks[i]);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid argument for command \"mark\"");
                }
            } else if (words[0].equals("unmark")) {
                try {
                    int number = Integer.parseInt(words[1]);
                    int i = number - 1;
                    tasks[i].unmark();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("   " + tasks[i]);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid argument for command \"unmark\"");
                }
            }
            else {
                System.out.println("added: " + command);
                tasks[currIndex++] = new Task(command);
            }
            line();
        }

    }



    public static void line() {
        System.out.println("____________________________________________________________");
    }
}
