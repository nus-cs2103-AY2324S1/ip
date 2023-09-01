import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TodoList {



    FileWriter writer;
    ArrayList<Task> list;

    TodoList(FileWriter f){

        this.list = new ArrayList<>();
        this.writer = f;
    }
    public void store(Task s){
        list.add(s);
    }

    public void load(String taskString){
        String[] arr = taskString.split("\\|");

        try{
            Task task = null;
            switch (taskString.charAt(0)){
                case 'T' :
                    task = new Todo(arr[2]);
                    break;
                case 'E':
                    task = new Event(arr[2], arr[3], arr[4]);
                    break;
                case 'D':
                    task =new Deadline(arr[2], arr[3]) ;
                    break;
                default :
                    System.out.println("Corrupt file detected");
            }
            if(Boolean.parseBoolean(arr[1])){
                task.markDone();
            }
            store(task);
        } catch (Exception e){
            System.out.println(e);
            System.out.println("something bad when loading");
        }


    }


    public void markDone(int index){
        list.get(index).markDone();
    }
    public void markUndone(int index){
        list.get(index).markUndone();
    }
    public void deleteTask(int index) {list.remove(index);}

    public String getTask(int index){
        return list.get(index).toString();
    }
    public int size(){ return list.size();}

    public void saveToFile() throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        for(int i = 0 ; i < list.size();i++){
            StringBuilder taskString = new StringBuilder();
            Task task = list.get(i);
            taskString.append(task.toString().charAt(1));
            taskString.append('|');
            taskString.append(task.getStatus());
            taskString.append('|');
            taskString.append(task.getDesc());
            taskString.append('|');
            if(task instanceof Event){
                taskString.append(LocalDate.parse(((Event) task).getStart(), formatter));
                taskString.append('|');
                taskString.append(LocalDate.parse(((Event) task).getEnd(), formatter));
                taskString.append('|');
            } else if (task instanceof Deadline){
                taskString.append(LocalDate.parse(((Deadline) task).getDeadline(), formatter));
                taskString.append('|');
            }
            writer.write(taskString + System.lineSeparator());
        }
        writer.close();
    }

}
