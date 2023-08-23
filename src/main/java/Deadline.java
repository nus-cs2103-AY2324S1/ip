public class Deadline extends Task{
    char type =  'D';
    String deadline;
    public Deadline(String s) {
        super("");
        String[] split = s.split(" /by ", 2);
        if (split.length == 1) {
            System.out.println("There's no deadline peko!");
            return;
        } else if (split.length >= 3){
            System.out.println("You can't have two deadlines peko!");
        }
        this.name = split[0];
        deadline = split[1];
    }

    @Override
    public String toString() {
        return "[" + type + "]" + super.toString() + " (by: "  + deadline + ")";
    }
}
