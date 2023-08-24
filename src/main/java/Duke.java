import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskList taskList = new TaskList();
        int total = 0;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String name = "Remi";
        String line = "______________________";
        System.out.println(line);
        System.out.println("Hey! I'm " + name + "!");
        System.out.println("What can I do for you?");
        System.out.println(line);

        boolean running = true;
        while (running) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                System.out.println(line);
                System.out.println("Bye!");
                System.out.println(line);
                running = false;
            } else if (command.equals("list")) {
                System.out.println(line);
                System.out.println("Here are your tasks:");
                for (int count = 1; count <= total; count++) {
                    System.out.println(count + ". " + taskList.getTask(count - 1).toString());
                }
                System.out.println(line);
            } else if (command.startsWith("mark")) {
                String[] temp = command.split(" ", 2);
                int ind = Integer.parseInt(temp[1]);
                taskList.mark(ind-1);
                System.out.println(line);
                System.out.println("It is accomplished:");
                System.out.println(taskList.getTask(ind-1).toString());
                System.out.println(line);
            } else if (command.startsWith("unmark")) {
                String[] temp = command.split(" ", 2);
                int ind = Integer.parseInt(temp[1]);
                taskList.unmark(ind-1);
                System.out.println(line);
                System.out.println("It is unfinished:");
                System.out.println(taskList.getTask(ind-1).toString());
                System.out.println(line);
            } else {
                System.out.println(line);
                System.out.println("added: " + command);
                System.out.println(line);
                Task newTask = new Task(command);
                taskList.addTask(newTask);
                total++;
            }
        }
    }
}
