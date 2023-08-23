
public class ListHandler implements ICommandHandler{

    // I think here along the array, also using a HashMap<String, Task> to store the task could be better because it can more easily get a task by name
    // But since the question requires me to use the array to store tasks, I have to do so
    private Task[] taskList;
    private int taskCount;

    public ListHandler(){
        this.taskList = new Task[100];
        this.taskCount = 0;
    }

    @Override
    public void execute(String command) {
        if (command.equals("list")) {
            int number = 0;
            for(int i = 0;i < this.taskCount; i++){
                Main.getInstance().say((i + 1) + "." + this.taskList[i].toString(), i == 0, i == taskCount - 1);
            }
        }
        else {
            String[] splitedCommand = command.split(" ");
            if(splitedCommand[0].equals("mark") || splitedCommand[0].equals("unmark")){
                if(splitedCommand.length < 2){
                    Main.getInstance().say("Error: The task name for marking is not given.");
                    return;
                }
                Task task = this.findTaskByName(splitedCommand[1]);
                if(task == null){
                    Main.getInstance().say("Error: Can not find any task with name '" + splitedCommand[1] + "'.");
                    return;
                }
                boolean mark = splitedCommand[0].equals("mark");
                task.setIsDone(mark);
                Main.getInstance().say("Nice! I've marked this task as " + (mark ? "done" : "not done yet") + ":", true, false);
                Main.getInstance().say("  " + this.findTaskByName(splitedCommand[1]).toString(),false, true);
            }
            else{
                if(this.taskCount >= 100){
                    Main.getInstance().say("list is full");
                }
                if(this.findTaskByName(command) != null){
                    Main.getInstance().say("Error: A task with name '" + command + "' already exists.");
                    return;
                }
                this.taskList[taskCount] = new Task(command);
                taskCount++;
                Main.getInstance().say("added: " + command);
            }
        }

    }

    private Task findTaskByName(String name){
        for(int i = 0; i < this.taskCount; i++){
            if(this.taskList[i].getName().equals(name)){
                return this.taskList[i];
            }
        }
        return null;
    }
}
