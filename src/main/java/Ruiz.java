import java.util.Scanner;
import java.util.ArrayList;

public class Ruiz {
    ArrayList<String> tasks = new ArrayList<>();

    public void getTasks() {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + this.tasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    public void addTask(String task) {
        this.tasks.add(task);
        System.out.println("____________________________________________________________\n" +
                "added: " + task + "\n" +
                "____________________________________________________________");
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
            switch (input) {
                case "bye":
                    bot.bye();
                    break;
                case "list":
                    bot.getTasks();
                    break;
                default:
                    bot.addTask(input);
                    break;
            }

        }
    }
}
