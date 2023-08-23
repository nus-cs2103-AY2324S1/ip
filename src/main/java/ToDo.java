import java.util.Scanner;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }
    public static ToDo taskDescriptor() {
        System.out.print(Duke.indent + "What is the ToDo that you would like to add? : ");
        String desc = new Scanner(System.in).nextLine();
        ToDo td = new ToDo(desc);
        System.out.println(Duke.indent + "Cool! I have added this new task");
        System.out.println(Duke.indent + "  " + td.toString());
        return td;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
