import java.util.ArrayList;

public class TodoList {
    ArrayList<String> list;

    TodoList(){
        this.list = new ArrayList<>();
    }
    public void store(String s){
        list.add(s);
    }

    public void iterate(){

        for(int i = 0; i < list.size();i++){
            int index = i+1;
            System.out.println(index + ". " + list.get(i));
        }
    }
}
