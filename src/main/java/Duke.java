import java.util.*;
public class Duke {
    public static void main(String[] args) {
        String name = "Harry Potter";
        String question = "What can I do for you?";
        System.out.println("Hello! I'm " + name + "\n" + question);

        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        List<Task> tasks = new ArrayList<>();

        while (!str.equals("bye")) {
            if (!str.equals("list")) {
                if (str.startsWith("mark ")) {
                    String num = str.substring(5);
                    int number = Integer.valueOf(num);
                    if (number <= 0 || number > tasks.size()) {
                        System.out.println("Invalid input");
                    }
                    int index = number - 1; //index for task list
                    Task done = tasks.get(index);
                    done.markAsDone();
                    System.out.println("\t" + "Nice! I've marked this task " +
                            "as done:" + "\n" +
                            "\t" + "\t" + done.taskString());
                } else if (str.startsWith("unmark ")) {
                    String num = str.substring(7);
                    int number = Integer.valueOf(num);
                    if (number <= 0 || number > tasks.size()) {
                        System.out.println("Invalid input");
                    }
                    int index = number - 1; //index for task list
                    Task notDone = tasks.get(index);
                    notDone.markAsNotDone();
                    System.out.println("\t" + "OK, I've marked this task " +
                            "as not done yet:" + "\n" + "\t" + "\t" +
                            notDone.taskString());
                } else {
                    Task task = new Task(str);
                    tasks.add(task);
                    System.out.println("\t" + "added: " + task.getTask());
                }
            } else {
                listTasks(tasks);
                System.out.println();
            }
            str = sc.nextLine();
        }
        System.out.println("\t" + "Bye. Hope to see you again soon!");
    }

    public static void listTasks(List<Task> tasks) {
        int i = 1;
        System.out.print("\tHere are the tasks in your list:");
        for (Task task : tasks) {
            System.out.println();
            System.out.print("\t" + i + "." + task.taskString());
            i++;
        }
    }
}
