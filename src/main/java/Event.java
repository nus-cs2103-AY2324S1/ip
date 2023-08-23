public class Event extends Task{
    char type = 'E';
    String start;
    String end;
    public Event(String s) throws InvalidTaskException {
        super("");

        String[] split = s.split(" /from ");
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
        return "[" + type + "]" + super.toString();
    }
}
