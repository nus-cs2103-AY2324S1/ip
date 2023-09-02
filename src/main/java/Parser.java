import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;


public class Parser {
    private String str;
    private String arr[];
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    List<Task> list;
    public Parser(List<Task> list) throws Exception {
        this.list = list;
        this.str = reader.readLine();
        this.arr = str.split(" ", 2);
    }

    public String getStr() {
        return this.str;
    }

    public String[] getArr() {
        return this.arr;
    }




}
