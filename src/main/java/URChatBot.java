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
                System.out.println("OK, I've marked this task as not done yet:\n" + tasks[value - 1].toString());}
            else {
                    Task newTask = new Task(command);
                    tasks[taskCount] = newTask;
                    taskCount++;
                    System.out.println("added: " + command);
                }
            }

    }
}
