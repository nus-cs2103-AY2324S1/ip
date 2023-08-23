public class ToDo extends Task{
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String showTaskinList() {
        return "[T]" + super.showTaskinList();
    }
}
