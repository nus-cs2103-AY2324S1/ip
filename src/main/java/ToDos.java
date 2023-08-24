import java.util.Arrays;

public class ToDos extends Task{
    char type = 'T';
    public ToDos(String s) throws InvalidTaskException {
        super(s);
        String[] temp = s.split(" ", 2);
        //System.out.println(Arrays.toString(temp));
        if (s.isBlank() || temp.length <= 1) {
            throw new InvalidTaskException();
        }
        this.name = temp[1];
    }
    @Override
    public String toString() {
        return "[" + type + "]" + super.toString();
    }

    public String toStore() {
        String state = status ? "0" : "1";
        return "T" + " | " + state + " | " + this.name;
    }
}
