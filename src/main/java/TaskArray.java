import java.util.ArrayList;

public class TaskArray {
    private static String horiLine = "____________________________________________________________";

    private ArrayList<Task> taskArrayList;

    public TaskArray(){
        taskArrayList = new ArrayList<>();
    }

    public void printTaskArrayList(){
        System.out.println(horiLine);
        for(int i = 0; i < taskArrayList.size(); i++ ){
            int index = i + 1;
            System.out.println(index +": " + taskArrayList.get(i).getStatusText());
        }
        System.out.println(horiLine);

    }

    public void add(Task task){
        taskArrayList.add(task);
        String word = "added:" + task.getText();
        System.out.println(horiLine);
        System.out.println(word);
        System.out.println(horiLine);


    }
    public Task get(int index){
        return taskArrayList.get(index);
    }



}
