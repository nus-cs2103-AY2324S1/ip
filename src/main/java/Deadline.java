public class Deadline extends Task{
    char type =  'D';
    String deadline;
    public Deadline(String s) throws InvalidTaskException {
        super(s);
        String[] temp = s.split(" ", 2);
        if (s.isBlank() || temp.length <= 1) {
            throw new InvalidTaskException();
        }
        String[] split = temp[1].split(" /by ");
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
