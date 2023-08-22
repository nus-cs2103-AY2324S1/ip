import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Storage {
    private Task[] tasks;
    private int taskCount;

    public Storage() {
        this.tasks = new Task[100];
        this.taskCount = 0;
    }

    public String addTask(String taskType, String description) {
        description=description.trim();
        if (taskType.equals("todo") && description.length()>0){
            this.tasks[this.taskCount]= new ToDo(description);
        } else if (taskType.equals("deadline")){
            Pattern pattern = Pattern.compile("(.+) /by (.+)");
            Matcher matcher = pattern.matcher(description);
            if (matcher.matches() && (matcher.group(1).length()>0)){
                this.tasks[this.taskCount]= new Deadline(matcher.group(1),matcher.group(2));
            }
            else return "Oops! Invalid task input.";
        } else if (taskType.equals("event")){
            Pattern pattern = Pattern.compile("(.+) /from (.+) /to (.+)");
            Matcher matcher = pattern.matcher(description);
            if (matcher.matches() && (matcher.group(1).length()>0)){
                this.tasks[this.taskCount]= new Event(matcher.group(1),matcher.group(2),matcher.group(3));
            }
            else return "Oops! Invalid task input.";
        } else {
            return "Oops! Invalid task input.";
        }
        this.taskCount++;
        String taskInTotal = this.taskCount > 1 ? " tasks in total." : " task in total.";
        return "Task added:\n"+this.tasks[this.taskCount-1]+"\nNow you have "+this.taskCount+taskInTotal+"\n\"Be here now.\"";
    }

    public String getTasks(){
        String result =  "Here are your tasks:\n";
        for (int i = 0; i < taskCount; i++) {
            result+=(i+1)+" "+tasks[i]+"\n";
        }
        return result+"\"One thing at a time.\"";
    }

    public String markTask(int taskIndex, boolean isDone){
        if (taskIndex > taskCount || taskIndex <=0){
            return "Oops! Cannot find the task.";
        }
        else {
            tasks[taskIndex - 1].markAsDone(isDone);
            return "Here's your modified task:\n"+tasks[taskIndex - 1]+"\n\"Keep moving forward.\"";
        }
    }
}
