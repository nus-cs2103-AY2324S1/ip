public class Deadline extends Task{
    char type =  'D';
    String deadline;
    public Deadline(String s) throws InvalidTaskException {
        super("");
        String[] split = s.split(" /by ");
        if (split.length == 1) {
            System.out.println("There's no deadline peko!");
            return;
        } else if (split.length >= 3){
            System.out.println("You can't have two deadlines peko!");
            return;
        }
        this.name = split[0];
        deadline = split[1];
    }

    @Override
    public String toString() {
        return "[" + type + "]" + super.toString() + " (by: "  + deadline + ")";
    }
}
