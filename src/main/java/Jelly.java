import java.util.Scanner;

public class Jelly {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Task[] storage = new Task[100];
        int index = 0;
        System.out.println("Hello! I'm Jelly");
        System.out.println("What can I do for you?");
        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            String[] stringArray = str.split(" ");
            if (stringArray[0].equals("mark")) {
                int num = Integer.parseInt(stringArray[1]);
                if (num <= 0 || num > 100) {
                    System.out.println("Invalid input");
                } else if (storage[num - 1] != null) {
                    storage[num - 1].markAsDone();
                    System.out.println("Good job! I've marked this task as done :)");
                } else {
                    System.out.println("Invalid input");
                }
            } else if (stringArray[0].equals("unmark")) {
                int num = Integer.parseInt(stringArray[1]);
                if (num <= 0 || num > 100) {
                    System.out.println("Invalid input");
                } else if (storage[num - 1] != null) {
                    storage[num - 1].markAsUndone();
                    System.out.println("Bad job! I've marked this task as not done :(");
                } else {
                    System.out.println("Invalid input");
                }
            }
            else if (stringArray[0].equals("bye")) {
                System.out.println("Bye, have a nice day!");
                return;
            } else if (stringArray[0].equals("list")) {
                System.out.println("Here are the tasks you listed:");
                for (int i = 0; i < index; i++) {
                    System.out.println((i + 1) + "." + "[" + storage[i].getTaskStatus() + "] " + storage[i].toString());
                }
            } else {
                Task t = new Task(str);
                storage[index] = t;
                index++;
                System.out.println("added: " + t);
            }
        }
    }
}
