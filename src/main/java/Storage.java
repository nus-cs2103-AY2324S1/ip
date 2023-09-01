import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Storage {

    private File file;

    public Storage(String fileString){
        this.file = new File(fileString);
    }
    public void save(TodoList list) throws IOException{
        FileWriter writer = null;

        writer = new FileWriter(file);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        for(int i = 0 ; i < list.size();i++){
            StringBuilder taskString = new StringBuilder();
            Task task = list.getTask(i);
            taskString.append(list.getTaskString(i).charAt(1));
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

    public TodoList load(){
        TodoList list = new TodoList();
        Scanner sc = null;
        try{
            sc = new Scanner(file);
        } catch( FileNotFoundException e){
            return list;
        }
        while(sc.hasNext()){
            String taskString = sc.nextLine();
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
                list.store(task);
            } catch (Exception e){
                System.out.println(e);
                System.out.println("something bad when loading");
            }
        }
        return list;
    }


}
