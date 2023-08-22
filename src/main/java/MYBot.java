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

    public void addTask(String task){
        task_List.addTask(task);
        System.out.println("____________________________________________________________");
        System.out.println("added: " + task);
        System.out.println("____________________________________________________________");
    }

    public void listTasks(){
        Task[] tasks = task_List.getTask_List();
        int taskCount = task_List.getTask_Count();

        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++){
            System.out.println((i+1) + "." + "["+ tasks[i].getStatusIcon() + "]" + tasks[i].getDescription());
        }
        System.out.println("____________________________________________________________");
    }

    public void markTasks(int task_number) {
        System.out.println("____________________________________________________________");

        if (task_number > 0 && task_List.getTask(task_number) != null) {
            Task taskTobeMarked = task_List.getTask(task_number);
            taskTobeMarked.taskDone();

            System.out.println("Good job completing! I've marked these task as done:):");
            System.out.println("[" + taskTobeMarked.getStatusIcon() + "]" + taskTobeMarked.getDescription());
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
            System.out.println("[" + taskTobeMarked.getStatusIcon() + "]" + taskTobeMarked.getDescription());
        } else {
            System.out.println("Oops! Task not found:(");
        }

        System.out.println("____________________________________________________________");
    }
}
