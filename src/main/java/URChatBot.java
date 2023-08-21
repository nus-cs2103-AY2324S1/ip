import java.util.Scanner;

public class URChatBot {
    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        int taskCount = 0;
        Scanner sc = new Scanner(System.in);
        String logo =
                "         _____   _____\n"
                        + "| | | | /  ___| |   ) | \n"
                        + "| | | | | |     | ___ /\n"
                        + "| |_| | | |___  |   ) \\\n"
                        + "\\___,_| \\_____| |_____|\n";
        System.out.println("Hello! I'm URChatBot.\nWhat can I do for you?\n" + logo);
        while (true) {
            String command = sc.nextLine();
            if (command.toUpperCase().contentEquals("BYE")) {
                System.out.println("Bye. Hope to see you again soon!");
                sc.close();
                break;
            } else if (command.toUpperCase().contentEquals("LIST")){
                System.out.println("Here are the tasks in your list:");
                for(int i = 0; i < tasks.length; i ++) {
                    if(tasks[i] != null){
                        System.out.println(i+1 + "." + tasks[i].toString());
                    }
                }
            } else if (command.toUpperCase().startsWith("MARK")){
                int value = Integer.parseInt(command.replaceAll("[^0-9]", ""));
                tasks[value - 1].markAsDone();
                System.out.println("Nice! I've marked this task as done:\n" + tasks[value - 1].toString());

            } else if (command.toUpperCase().startsWith("UNMARK")){
                int value = Integer.parseInt(command.replaceAll("[^0-9]", ""));
                tasks[value - 1].markAsUnDone();
                System.out.println("OK, I've marked this task as not done yet:\n" + tasks[value - 1].toString());
            } else if (command.toUpperCase().startsWith("TODO")){
                String task = command.substring(command.indexOf("todo") + 5);
                Task newTask = new ToDo(task);
                tasks[taskCount] = newTask;
                taskCount++;
                if (taskCount == 1 || taskCount == 0){
                    System.out.println("Got it. I've added this task:\n  " + tasks[taskCount - 1].toString() + "\nNow you have " + taskCount + " task in the list.");
                } else {
                    System.out.println("Got it. I've added this task:\n  " + tasks[taskCount - 1].toString() + "\nNow you have " + taskCount + " tasks in the list.");
                }
            } else if (command.toUpperCase().startsWith("DEADLINE")){
                String task = command.substring(command.indexOf("deadline") + 9, command.indexOf("/by") - 1);
                String by = command.substring(command.indexOf("/by") + 4);
                Task newTask = new Deadline(task, by);
                tasks[taskCount] = newTask;
                taskCount++;
                if (taskCount == 1 || taskCount == 0){
                    System.out.println("Got it. I've added this task:\n  " + tasks[taskCount - 1].toString() + "\nNow you have " + taskCount + " task in the list.");
                } else {
                    System.out.println("Got it. I've added this task:\n  " + tasks[taskCount - 1].toString() + "\nNow you have " + taskCount + " tasks in the list.");
                }
            } else if (command.toUpperCase().startsWith("EVENT")){
                String task = command.substring(command.indexOf("event") + 6, command.indexOf("/from") - 1);
                String from = command.substring(command.indexOf("/from") + 6, command.indexOf("/to") - 1);
                String to = command.substring(command.indexOf("/to") + 4);
                Task newTask = new Event(task, from, to);
                tasks[taskCount] = newTask;
                taskCount++;
                if (taskCount == 1 || taskCount == 0){
                    System.out.println("Got it. I've added this task:\n  " + tasks[taskCount - 1].toString() + "\nNow you have " + taskCount + " task in the list.");
                } else {
                    System.out.println("Got it. I've added this task:\n  " + tasks[taskCount - 1].toString() + "\nNow you have " + taskCount + " tasks in the list.");
                }
            } else {
                    Task newTask = new Task(command);
                    tasks[taskCount] = newTask;
                    taskCount++;
                    System.out.println("added: " + command);
                }
            }

    }
}
