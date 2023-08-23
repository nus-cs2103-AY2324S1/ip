public class Deadline extends Task{

    private String by;
    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    @Override
    public String showTaskinList() {
        return "[D]" + super.showTaskinList() + "(" + "by: " + this.by + ")";
    }

}
