public class Event extends Task{
    char type = 'E';
    String start;
    String end;
    public Event(String s) throws InvalidTaskException {
        super(s);
        String[] temp = s.split(" ", 2);
        if (s.isBlank() || temp.length <= 1) {
            throw new InvalidTaskException();
        }
        String[] split = temp[1].split(" /from ");
        if (split.length == 1) {
            System.out.println("There's no start date peko!");
            return;
        } else if (split.length >= 3){
            System.out.println("You can't have two start dates peko!");
            return;
        }
        this.name = split[0];
        split = split[1].split(" /to ");
        if (split.length == 1) {
            System.out.println("There's no end date peko!");
            return;
        } else if (split.length >= 3) {
            System.out.println("You can't have two end dates peko!");
            return;
        }
        start = split[0];
        end = split[1];
    }

    @Override
    public String toString() {
        return "[" + type + "]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }

    public String toStore() {
        String state = this.status ? "0" : "1";
        return "E" + " | " + state + " | " + this.name + " | " + start + " | " + end;
    }
}
