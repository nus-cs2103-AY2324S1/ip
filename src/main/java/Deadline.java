public class Deadline extends Task{
    private final String deadline;

        public Deadline(String descr, String deadline) {
            super(descr);
            this.deadline = deadline;
        }

    @Override
    public String toString() {
        String[] parts = this.deadline.split(" ");
        String by = parts[0]; //first word is command
        String restOfSentence = this.deadline.substring(by.length()).trim();
        return "[D]" + super.toString() + "(" + by + ": " + restOfSentence + ")";
    }
}
