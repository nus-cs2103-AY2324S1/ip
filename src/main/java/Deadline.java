public class Deadline extends Task {
    String[] inputs;
    public Deadline(String name) throws DukeException {
        super(name.split("/")[0]);
        this.inputs = name.split("/");
        if (this.inputs.length < 2) throw new DukeException("deadline has no end date!");
    }
    @Override
    public String toString() {
        String chunkWithby = this.inputs[1];
        String date = chunkWithby.substring(3);

        return "[D]" + super.toString() + "(by: " + date +")";
    }
}
