

class ToDo extends Task {

    ToDo(String name) {
        this.task_name = name;
        this.isCompleted = false;
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
