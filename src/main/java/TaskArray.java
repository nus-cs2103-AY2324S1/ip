
import java.util.ArrayList;

public class TaskArray {
    private static String horiLine = "____________________________________________________________";

    private ArrayList<Task> taskArrayList;

    public TaskArray(){
        taskArrayList = new ArrayList<>();
    }

    public TaskArray(ArrayList<Task> array){
        taskArrayList = array;
    }

    public void printTaskArrayList(){
        System.out.println(horiLine);
        for(int i = 0; i < taskArrayList.size(); i++ ){
            int index = i + 1;
            System.out.println(index +": " + taskArrayList.get(i).getTypeCheckedText());
        }
        System.out.println(horiLine);

    }

    public void add(Task task){
        taskArrayList.add(task);
        String word = "added:" + task.getText();
        System.out.println(horiLine);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.getTypeCheckedText());
        System.out.println("Now you have "+ getTaskCount()+" tasks in the list.");
        System.out.println(horiLine);
    }
    public Task get(int index){
        return taskArrayList.get(index);
    }
    public int getTaskCount(){
        return taskArrayList.size();
    }

    public void removeTask(int index){
        if(index < 0 && index >=taskArrayList.size()){
            System.out.println(horiLine);
            System.out.println("Invalid Index");
            System.out.println(horiLine);
            return;
        }
        Task removingTask = taskArrayList.get(index);
        System.out.println(horiLine);
        System.out.println("Noted. I've removed this task:");
        System.out.println(removingTask.getTypeCheckedText());
        taskArrayList.remove(index);
        System.out.println("Now you have "+ getTaskCount()+" tasks in the list.");
        System.out.println(horiLine);
    }

    public ArrayList<Task> getTaskArrayList(){
        return taskArrayList;
    }



}
