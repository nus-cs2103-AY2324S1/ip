public class Deadline extends Task{
    char type =  'D';
    String deadline;
    public Deadline(String s) {
        super("");
        String[] split = s.split(" /by ", 2);
        this.name = split[0];
        deadline = split[1];
    }

    @Override
    public String toString() {
        return "[" + type + "]" + super.toString() + " (by: "  + deadline + ")";
    }
}
