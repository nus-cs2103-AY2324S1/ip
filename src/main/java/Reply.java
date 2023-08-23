
public class Reply {
    private String line = "____________________________________________________________";



    public void echo(String content){
        System.out.println(line);
        System.out.println(content);
        System.out.println(line);
    }

    public void iterate(TodoList list){
        System.out.println(line);
        list.iterate();
        System.out.println(line);
    }
}
