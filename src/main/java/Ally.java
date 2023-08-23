import java.util.Scanner;
public class Ally {

    private static final String line = "____________________________________________________________";
    private static final String greeting = "Hello! I'm ALLY\nWhat can I do for you?\n";
    private static final String bye = "Bye. Hope to see you again soon!";
    public static void start() {
        System.out.println(line);
        System.out.println(greeting);
        System.out.println(line);
    }

    public static void bye() {
        System.out.println(line);
        System.out.println(bye);
        System.out.println(line);
    }
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        

        Scanner scanner = new Scanner(System.in);
        AllyList ally = new AllyList();
        start();
        String ipt;
        while(true) {
            ipt = scanner.nextLine();
            String[] split = ipt.split(" ", 2);

            if (split[0].equals("bye")) {
                bye();
                break;
            } else if (split[0].equals("list")) {
                System.out.println(line);
                ally.printElements();
                System.out.println(line);
            } else if (split[0].equals("mark")) {
                int index = Integer.parseInt(split[1]) - 1;
                System.out.println(line);
                ally.markAsDone(index);
                System.out.println(line);
            } else if (split[0].equals("unmark")) {
                int index = Integer.parseInt(split[1]) - 1;
                System.out.println(line);
                ally.unMarkDone(index);
                System.out.println(line);
            } else if (split[0].equals("todo")) {
                System.out.println(line);
                if (split.length == 1) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                } else {
                    ally.addTodo(split[1]);
                }
                System.out.println(line);
            } else if (split[0].equals("deadline")) {
                System.out.println(line);
                if (split.length == 1) {
                    System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                } else {
                    String[] deadline = split[1].split(" /");
                    ally.addDeadline(deadline[0], deadline[1]);
                }
                System.out.println(line);
            } else if (split[0].equals("event")) {
                System.out.println(line);
                if (split.length == 1) {
                    System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
                } else {
                    String[] event = split[1].split(" /");
                    ally.addEvent(event[0], event[1], event[2]);
                }
                System.out.println(line);
            }  else if (split[0].equals("delete")) {
                System.out.println(line);
                if (split.length == 1) {
                    System.out.println("☹ OOPS!!! The description of a delete cannot be empty.");
                } else {
                    int index = Integer.parseInt(split[1]) - 1;
                    ally.deleteElement(index);
                }
                System.out.println(line);
            } else {
                ally.addElements(ipt);
                System.out.println(line);
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println(line);
            }
        }
        scanner.close();


    }
}
