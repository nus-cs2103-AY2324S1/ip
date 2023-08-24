import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        String inData = "";
        Scanner scan = new Scanner(System.in);
        System.out.println("Hello! I'm Nicole");
        System.out.println("What can I do for you?");
        ArrayList<Task> list = new ArrayList<>();
        int count = 0;

        while (!inData.equals("bye")) {
            inData = scan.nextLine();
            try {
                if (inData.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                }

                if (inData.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < count; i++) {
                        System.out.println((i + 1) + "." + list.get(i));
                    }
                    continue;
                }

                if (inData.startsWith("mark ")) {
                    int num = inData.charAt(5) - '0' - 1;
                    if (num >= 0 && num < count) {
                        list.get(num).markAsDone();
                        System.out.println("Nice! I've marked this task done:");
                        System.out.println(list.get(num));
                    } else {
                        System.out.println("Invalid");
                    }
                    continue;
                }

                if (inData.startsWith("unmark ")) {
                    int num = inData.charAt(7) - '0' - 1;
                    if (num >= 0 && num < count) {
                        list.get(num).markAsUndone();
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(list.get(num));
                    } else {
                        System.out.println("Invalid");
                    }
                    continue;
                }

                if (inData.startsWith("todo ")) {
                    String des = inData.substring(5);
                    if (des.isBlank()) {
                        throw new EmptyDescriptionException();
                    }
                    list.add(new Todo(des));
                    System.out.println("Got it. I've added this task:");
                    System.out.println(list.get(count));
                    count++;
                    System.out.println("Now you have " + count + " tasks in the list.");
                    continue;
                }

                if (inData.startsWith("deadline ")) {
                    if (inData.substring(9).isBlank()) {
                        throw new EmptyDescriptionException();
                    }
                    String[] split = inData.substring(9).split(" /by ");
                    String des = split[0];
                    String by = split[1];
                    list.add(new Deadline(des, by));
                    System.out.println("Got it. I've added this task:");
                    System.out.println(list.get(count));
                    count++;
                    System.out.println("Now you have " + count + " tasks in the list.");
                    continue;
                }

                if (inData.startsWith("event ")) {
                    if (inData.substring(6).isBlank()) {
                        throw new EmptyDescriptionException();
                    }
                    String[] split = inData.substring(6).split(" /from ");
                    String des = split[0];
                    String[] fromto = split[1].split(" /to ");
                    String from = fromto[0];
                    String to = fromto[1];

                    list.add(new Event(des, from, to));
                    System.out.println("Got it. I've added this task:");
                    System.out.println(list.get(count));
                    count++;
                    System.out.println("Now you have " + count + " tasks in the list.");
                    continue;
                }

                if(inData.startsWith("delete ")) {
                    int num = Integer.parseInt(inData.substring(7)) - 1;
                    if (num >= 0 && num < list.size()) {
                        list.remove(num);
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(list.get(num));
                        count--;
                        System.out.println("Now you have " + count + " tasks in the list");

                    } else {
                        System.out.println("Invalid.");
                    }
                    continue;
                }
                throw new UnknownCommandException();

            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

