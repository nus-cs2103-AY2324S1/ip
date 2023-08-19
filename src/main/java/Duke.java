import extensions.tasks.Task;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        boolean hasEndedChat = false;

        System.out.println("Hello! I'm Max!");
        System.out.println("What can I do for you?");

        while (!hasEndedChat) {
            String input = sc.nextLine();
            switch (input) {
                case "bye":
                    hasEndedChat = true;
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println((i + 1) + "." + list.get(i));
                    }
                    break;
                default:
                    // test if it is a command with spacing (e.g. mark 2)
                    String[] inputArr = input.split(" ");
                    if (inputArr.length > 1) {
                        int index;
                        switch (inputArr[0]) {
                            case "mark":
                                index = Integer.parseInt(inputArr[1]) - 1;
                                list.get(index).markAsDone();
                                System.out.println("Nice! I've marked this task as done:");
                                System.out.println(list.get(index));
                                break;

                            case "unmark":
                                index = Integer.parseInt(inputArr[1]) - 1;
                                list.get(index).unmarkAsDone();
                                System.out.println("OK, I've marked this task as not done yet:");
                                System.out.println(list.get(index));
                                break;

                            default:
                                list.add(new Task(input));
                                System.out.println("added: " + input);
                                break;
                        }
                    } else {
                        list.add(new Task(input));
                        System.out.println("added: " + input);
                        break;
                    }
            }
        }
    }
}
