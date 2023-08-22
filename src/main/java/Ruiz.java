import java.util.Scanner;
import java.util.ArrayList;

public class Ruiz {
    ArrayList<Task> tasks = new ArrayList<>();

    public void getTasks() {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + this.tasks.get(i).toString());
        }
        System.out.println("____________________________________________________________");
    }

    public void markTask(String index) {
        int taskIndex = Integer.parseInt(index) - 1;
        if (taskIndex >= 0 && this.tasks.size() > taskIndex) {
            Task task = this.tasks.get(taskIndex);
            task.mark();
            System.out.println("____________________________________________________________\n" +
                    "Nice! I've marked this task as done\n" +
                    task +
                    "\n" +
                    "____________________________________________________________\n");
        } else {
            System.out.println("____________________________________________________________\n" +
                    "This task does not exist\n" +
                    "____________________________________________________________\n");
        }
    }

    public void unmarkTask(String index) {
        int taskIndex = Integer.parseInt(index) - 1;
        if (taskIndex >= 0 && this.tasks.size() > taskIndex) {
            Task task = this.tasks.get(taskIndex);
            task.unmark();
            System.out.println("____________________________________________________________\n" +
                    "OK, I've marked this task as not done yet\n" +
                    task +
                    "\n" +
                    "____________________________________________________________\n");
        } else {
            System.out.println("____________________________________________________________\n" +
                    "This task does not exist\n" +
                    "____________________________________________________________\n");
        }
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void greet() {
        String greet = "____________________________________________________________\n" +
                " Hello! I'm Ruiz\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        System.out.println(greet);
    }

    public void bye() {
        String bye = "____________________________________________________________\n" +
                "Bye! Comeback soon!\n" +
                "____________________________________________________________";
        System.out.println(bye);
    }

    public static void main(String[] args) {
        Scanner inputObj = new Scanner(System.in);
        Ruiz bot = new Ruiz();
        bot.greet();
        String input = "";
        while (!input.equals("bye")) {
            input = inputObj.nextLine();
            String keyWord = input.split(" ")[0];
            switch (keyWord) {
                case "bye":
                    bot.bye();
                    break;
                case "list":
                    bot.getTasks();
                    break;
                case "mark":
                    bot.markTask(input.split(" ")[1]);
                    break;
                case "unmark":
                    bot.unmarkTask(input.split(" ")[1]);
                    break;
                default:
                    Task temp = new Task(input);
                    bot.addTask(temp);
                    break;
            }

        }
    }
}
