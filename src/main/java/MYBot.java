public class MYBot {

    private String bot_Name;
    private TaskList task_List;

    public MYBot(String bot_Name){
        this.bot_Name = bot_Name;
        this.task_List = new TaskList();
    }

    public void openGreeting(){
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm " + bot_Name + ":)");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public void closeGreeting(){
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public void addTask(String input){
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");

        if(input.startsWith("todo ")){

            String description = input.substring(5);
            Task task = new Todo(description);
            task_List.addTask(task);
            System.out.println(task.toString());

        } else if (input.startsWith("deadline ")){

            String description = input.substring(9,input.indexOf(" /by "));
            String by = input.substring(input.indexOf(" /by ") + 4);
            Task task = new Deadline(description, by);
            task_List.addTask(task);
            System.out.println(task.toString());

        } else if ((input.startsWith("event "))) {

            String description = input.substring(6,input.indexOf(" /from "));
            String from = input.substring(input.indexOf(" /from ") + 6, input.indexOf(" /to "));
            String to = input.substring(input.indexOf(" /to ") + 4);
            Task task = new Event(description, from, to);
            task_List.addTask(task);
            System.out.println(task.toString());
        }

        System.out.println("Now you have " + task_List.getTask_Count() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public void listTasks(){
        Task[] tasks = task_List.getTask_List();
        int taskCount = task_List.getTask_Count();

        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++){
            System.out.println((i+1) + "." + tasks[i].toString());
        }
        System.out.println("____________________________________________________________");
    }

    public void markTasks(int task_number) {
        System.out.println("____________________________________________________________");

        if (task_number > 0 && task_List.getTask(task_number) != null) {
            Task taskTobeMarked = task_List.getTask(task_number);
            taskTobeMarked.taskDone();

            System.out.println("Good job completing! I've marked these task as done:):");
            System.out.println(taskTobeMarked.toString());
        } else {
            System.out.println("Oops! Task not found:(");
        }

        System.out.println("____________________________________________________________");
    }


    public void unmarkTasks(int task_number){
        System.out.println("____________________________________________________________");

        if (task_number > 0 && task_List.getTask(task_number) != null) {
            Task taskTobeMarked = task_List.getTask(task_number);
            taskTobeMarked.undoTask();

            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(taskTobeMarked.toString());
        } else {
            System.out.println("Oops! Task not found:(");
        }

        System.out.println("____________________________________________________________");
    }
}
