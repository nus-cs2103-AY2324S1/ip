import java.util.Scanner;
public class Duck {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        TaskList list = new TaskList();
        
        Duck.greet();
        String input = in.nextLine();
        while (!input.equals("bye")) {

            if (input.equals("list")) {
                line();
                list.listTasks();
                line();
                input = in.nextLine();
                continue;
            }

            if (input.startsWith("mark")) {
                int index = Integer.parseInt(input.substring(5));
                line();
                list.mark(index - 1);
                line();
                input = in.nextLine();
                continue;
            }

            if (input.startsWith("unmark")) {
                int index = Integer.parseInt(input.substring(7));
                line();
                list.unmark(index - 1);
                line();
                input = in.nextLine();
                continue;
            }

            if (input.startsWith("todo")) {
                String task;
                try {
                    task = input.trim().substring(5);
                } catch (StringIndexOutOfBoundsException e) {
                    line();
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    line();
                    input = in.nextLine();
                    continue;
                }
                line();
                list.addTodo(task);
                line();
                input = in.nextLine();
                continue;
            }

            if (input.startsWith("deadline")) {
                String task;
                try {
                    task = input.trim().substring(9);
                } catch (StringIndexOutOfBoundsException e) {
                    line();
                    System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                    line();
                    input = in.nextLine();
                    continue;
                }
                line();
                list.addDeadline(task);
                line();
                input = in.nextLine();
                continue;
            }

            if (input.startsWith("event")) {
                String task;
                try {
                    task = input.trim().substring(6);
                } catch (StringIndexOutOfBoundsException e) {
                    line();
                    System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
                    line();
                    input = in.nextLine();
                    continue;
                }
                line();
                list.addEvent(task);
                line();
                input = in.nextLine();
                continue;
            }

            if (input.startsWith("delete")) {
                int index = Integer.parseInt(input.substring(7));
                line();
                list.delete(index - 1);
                line();
                input = in.nextLine();
                continue;
            }
            line();
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            line();
            input = in.nextLine();
        }
        Duck.bye();
        in.close();
    }

    private static void line() {
        System.out.println("____________________________________________________________");
    }

    private static void greet() {
        String greeting = "Hello! I'm Duck\n" + "What can I do for you?";

        line();
        System.out.println(greeting);
        line();
    }

    private static void bye() {
        String bye = "Bye. Hope to see you again soon!";

        line();
        System.out.println(bye);
        line();
    }
}

class TaskList {
    private String[] list;
    private int currentIndex;
    private boolean[] doneList;
    private char[] typeList;
    private String[] infoList;

    public TaskList() {
        this.list = new String[100];
        currentIndex = 0;
        this.doneList = new boolean[100];
        this.typeList = new char[100];
        this.infoList = new String[100];
    }

    public void add(String input) {
        list[currentIndex] = input;
        currentIndex++;
        System.out.println("added: " + input);
    }

    public void addTodo(String input) {
        list[currentIndex] = input;
        typeList[currentIndex] = 'T';
        System.out.println("Got it. I've added this task:");
        System.out.println("[T][ ] " + input);

        currentIndex++;
        System.out.println("Now you have " + currentIndex + " tasks in the list.");
    }

    public void addDeadline(String input) {
        String name = input.substring(0, input.indexOf("/"));
        list[currentIndex] = name;

        String datetime = input.substring(input.indexOf("/by") + 4);
        infoList[currentIndex] = " (by: " + datetime + ")";

        typeList[currentIndex] = 'D';

        System.out.println("Got it. I've added this task:");
        System.out.println("[D][ ] " + name + infoList[currentIndex]);
        
        currentIndex++;
        System.out.println("Now you have " + currentIndex + " tasks in the list.");
    }

    public void addEvent(String input) {
        String name = input.substring(0, input.indexOf("/"));
        list[currentIndex] = name;

        String start = input.substring(input.indexOf("/from") + 6, input.indexOf("/to") - 1);
        String end = input.substring(input.indexOf("/to") + 4);
        infoList[currentIndex] = " (from: " + start + " to: " + end + ")";

        typeList[currentIndex] = 'E';

        System.out.println("Got it. I've added this task:");
        System.out.println("[E][ ] " + name + infoList[currentIndex]);
        
        currentIndex++;
        System.out.println("Now you have " + currentIndex + " tasks in the list.");
    }

    public void listTasks() {
        for (int i = 0; i < currentIndex; i++) {
            char type = typeList[i] == 0 ? ' ' : typeList[i];
            String output = i + 1 + "." + "[" + type + "]";
            if (doneList[i]) {
                output += "[X] ";
            } else {
                output += "[ ] ";
            }
            output += list[i];
            String info = infoList[i] == null ? "" : infoList[i];
            System.out.println(output + info);
        }
    }

    public void mark(int index) {
        doneList[index] = true;
        String prefix = typeList[index] == 0 ? " " : typeList[index] + "";
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[" + prefix + "][X] " + list[index]);
    }

    public void unmark(int index) {
        doneList[index] = false;
        String prefix = typeList[index] == 0 ? " " : typeList[index] + "";
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("[" + prefix + "][ ] " + list[index]);
    }

    public void delete(int index) {
        String prefix = typeList[index] == 0 ? " " : typeList[index] + "";

        System.out.println("Noted. I've removed this task:");
        System.out.println("[" + prefix + "][ ] " + list[index]);

        for (int i = index; i < currentIndex - 1; i++) {
            list[i] = list[i + 1];
            doneList[i] = doneList[i + 1];
            typeList[i] = typeList[i + 1];
            infoList[i] = infoList[i + 1];
        }
        currentIndex--;

        System.out.println("Now you have " + currentIndex + " tasks in the list.");
    }
}