public class Deadline extends Task {
    String[] inputs;
    public Deadline(String name) {
        super(name.split("/")[0]);
        this.inputs = name.split("/");
    }
    @Override
    public String toString() {
        String chunkWithby = this.inputs[1];
        String date = chunkWithby.substring(3);

        return "[D]" + super.toString() + "(by: " + date +")";
    }
}
