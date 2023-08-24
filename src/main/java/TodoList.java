import java.util.ArrayList;

public class TodoList {



    private class Todo extends Task{
        Todo(String s){
            super(s);
        }
    }
    ArrayList<Task> list;

    TodoList(){
        this.list = new ArrayList<>();
    }
    public void store(Task s){
        list.add(s);
    }


    public void markDone(int index){
        list.get(index).markDone();
    }
    public void markUndone(int index){
        list.get(index).markUndone();
    }

    public String getTask(int index){
        return list.get(index).toString();
    }
    public int size(){ return list.size();}
}
