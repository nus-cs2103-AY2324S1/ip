import java.util.Scanner;

public class Deadline extends Task {

    private String deadlineBy;

    public Deadline(String description, String deadlineBy) {
        super(description);
        this.deadlineBy = deadlineBy;
    }
    public static Deadline deadlineDescriptor() {
        System.out.print(Duke.indent + "What is the deadline? Give in format Task/Deadline: ");
        String desc = new Scanner(System.in).nextLine();
        String[] arr = desc.split("/");
        Deadline dl = new Deadline(arr[0], arr[1]);
        System.out.println(Duke.indent + "Cool! I have added this new task");
        System.out.println(Duke.indent + "  " + dl.toString());
        return dl;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadlineBy + ")";
    }
}
