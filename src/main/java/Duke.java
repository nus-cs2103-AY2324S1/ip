import java.util.Scanner;

public class Duke {
    
    private static TaskList tasks = new TaskList();

    private static void greet() {
        System.out.println("Hello! I'm Siyuan");
        System.out.println("What can I do for you?");
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }


    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        while (!input.equals("bye")) {

            if (input.equals("list")) {
                tasks.listAllTasks();  
            } 
            String[] inputSplit = input.split(" ");
            
            switch (inputSplit[0]) {
                case ("todo"):
                    String todoDescription = input.substring(5);
                    TodoTask todoTask = new TodoTask(todoDescription);
                    tasks.addTask(todoTask);
                    break;
                case ("deadline"):
                    String deadlineDescription = input.substring(9);
                    String[] deadlineSplit = deadlineDescription.split(" /by ");
                    DeadlineTask deadlineTask = new DeadlineTask(deadlineSplit[0], deadlineSplit[1]);
                    tasks.addTask(deadlineTask);
                    break;
                case ("event"):
                    String eventDescription = input.substring(6);
                    String[] eventSplit = eventDescription.split(" /from ");
                    String[] eventSplit2 = eventSplit[1].split(" /to ");
                    EventTask eventTask = new EventTask(eventSplit[0], eventSplit2[0], eventSplit2[1]);
                    tasks.addTask(eventTask);
                    break;
                case ("mark"):
                    int taskNumber = Integer.parseInt(inputSplit[1]);
                    tasks.markTaskAsDone(taskNumber);
                    break;
                case ("unmark"):
                    int taskNumber2 = Integer.parseInt(inputSplit[1]);
                    tasks.markTaskAsUndone(taskNumber2);
                    break;  
            }

            input = sc.nextLine();
        }

        sc.close();

        exit();
    }
}
