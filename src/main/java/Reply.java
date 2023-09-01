import java.util.ArrayList;
public class Reply {
    private String line = "____________________________________________________________";
    private ArrayList<String> lines;

    Reply(){
        this.lines = new ArrayList<>();
    }



    public void echo(String content){
        System.out.println(line);
        System.out.println(content);
        System.out.println(line);
    }

    public void echo(){
        System.out.println(line);
        for(int i = 0;i< lines.size();i++){
            System.out.println(lines.get(i));
        }
        System.out.println(line);
        lines.removeAll(lines);
    }
    public void add(String s){
        lines.add(s);
    }

    public void iterate(TodoList list){

        this.add("Here are the tasks in your list:");
        for(int i = 0 ; i< list.size(); i++){
            this.add((i+1) + ". " + list.getTaskString(i));
        }
        echo();
    }
}
