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
                if (str.startsWith("mark")) {
                    char num = str.charAt(5);
                    int number = Character.getNumericValue(num);
                    if (number <= 0 || number > tasks.size()) {
                        System.out.println("Invalid input");
                    }
                    int index = number - 1; //index for task list
                    Task done = tasks.get(index);
                    done.markAsDone();
                    System.out.println("\t" + "Nice! I've marked this task " +
                            "as done:" + "\n" +
                            "\t" + "[" + done.getStatusIcon() + "] " +
                            done.taskString());
                } else if (str.startsWith("unmark")) {
                    char num = str.charAt(7);
                    int number = Character.getNumericValue(num);
                    if (number <= 0 || number > tasks.size()) {
                        System.out.println("Invalid input");
                    }
                    int index = number - 1; //index for task list
                    Task notDone = tasks.get(index);
                    notDone.markAsNotDone();
                    System.out.println("\t" + "OK, I've marked this task " +
                            "as not done yet:" + "\n" + "\t" +
                            "[" + notDone.getStatusIcon() + "] " +
                            notDone.taskString());
                } else {
                    Task task = new Task(str);
                    tasks.add(task);
                    System.out.println("\t" + "added: " + task.taskString());
                }
            }
            else{
                listTasks(tasks);
            }
            str = sc.nextLine();
        }
        System.out.println("\t" + "Bye. Hope to see you again soon!");
    }

    public static void listTasks(List<Task> tasks) {
        int i = 1;
        for (Task task : tasks) {
            System.out.println("\t" + i + ". [" + task.getStatusIcon() + "] " + task.taskString());
            i++;
        }
    }
}
