import java.util.ArrayList;

public class TodoList {

    private class Task{
        private boolean isDone;
        private String description;

        Task(String s){
            this.description = s;
            this.isDone = false;
        }
        public void markDone(){
            this.isDone = true;
        }
        public void markUndone(){
            this.isDone = false;
        }
        public String toString(){
            return "[" + (isDone?"X":" ") + "] " + description;
        }
    }
    ArrayList<Task> list;

    TodoList(){
        this.list = new ArrayList<>();
    }
    public void store(String s){
        list.add(new Task(s));
    }

    public void iterate(){

        for(int i = 0; i < list.size();i++){
            int index = i+1;
            System.out.println(index + ". " + list.get(i).toString());
        }
    }
    public void markDone(int index){
        list.get(index).markDone();
    }
    public void markunDone(int index){
        list.get(index).markUndone();
    }

    public String getTask(int index){
        return list.get(index).toString();
    }
}
