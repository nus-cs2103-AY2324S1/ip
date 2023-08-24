public class Deadline extends Task{
    char type =  'D';
    DateTimeHandler dateTimeHandler;
    public Deadline(String s) throws InvalidTaskException {
        super(s);
        String[] split = s.split(" /by ");
        if (split.length == 1) {
            System.out.println("There's no deadline peko!");
            return;
        } else if (split.length >= 3){
            System.out.println("You can't have two deadlines peko!");
            return;
        }
        this.name = split[0];
        dateTimeHandler = new DateTimeHandler(split[1]);
    }

    @Override
    public String toString() {
        return "[" + type + "]" + super.toString() + " (by: "  + dateTimeHandler.stringDisplay() + ")";
    }

    public String toStore() {
        String state = this.status ? "0" : "1";
        return "D" + " | " + state + " | " + this.name + " | " + dateTimeHandler.toString();
    }
}
