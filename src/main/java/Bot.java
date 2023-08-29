import exception.IllegalExpressionBotException;
import exception.IncompleteBotException;
import task.TaskList;

import java.util.Scanner;

public class Bot {

    private final Ui ui;
    private final TaskList taskLst;

    public Bot() {
        this.taskLst = new TaskList();
        this.ui = new Ui(new Scanner(System.in), this.taskLst);
    }

    public static void main(String[] args) throws IllegalExpressionBotException, IncompleteBotException {
        Bot bot = new Bot();
        bot.start();
//        String str = sc.nextLine();
//        while (!str.equals("bye")) {
//            Task task;
//            String taskRequested = taskName(str);
//            if (str.equals("list")) {
//                System.out.println(TaskList.list());
//            } else if (str.contains("mark")) {
//                int idx = Integer.parseInt(str.substring(str.length() - 1)) - 1;
//                Task update = TaskList.get(idx).complete();
//                TaskList = TaskList.update(idx, update);
//                System.out.println("____________________________________________________________\n" +
//                        "Nice! I've marked this task as done:\n" +
//                        update +
//                        "____________________________________________________________\n");
//            } else if (str.contains("unmark")) {
//                int idx = Integer.parseInt(str.substring(str.length() - 1)) - 1;
//                Task update = TaskList.get(idx).incomplete();
//                TaskList = TaskList.update(idx, update);
//                System.out.println("____________________________________________________________\n" +
//                        "OK, I've marked this task as not done yet:" +
//                        update +
//                        "____________________________________________________________\n");
//            } else {
//                task = new Task(str);
//                System.out.println("____________________________________________________________\n" +
//                        "added: " + str + "\n" +
//                        "____________________________________________________________");
//                TaskList = TaskList.save(task);
//            }
//            str = sc.nextLine();
//        }
//        System.out.println("____________________________________________________________\n" +
//                " Bye. Hope to see you again soon!\n" +
//                "____________________________________________________________");
    }

    public void start() throws IllegalExpressionBotException, IncompleteBotException {
        this.ui.start();
    }
}
