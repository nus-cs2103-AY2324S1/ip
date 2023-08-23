public class Task {

    String list;

    boolean completed;

    TaskType type;

    public Task(String list, TaskType type) {
        this.list = list;
        this.type = type;
    }

    public String setMarked() throws DukeException{
        if(this.completed) {
            throw new DukeException("This task has already been marked as done!\n");
        }
        this.completed = true;
        return "";
    }

    public String setUnmarked() throws DukeException{
        if(!this.completed) {
            throw new DukeException("This task has already been marked as not done!");
        }
        this.completed = false;
        return "";
    }

    @Override
    public String toString() {
        String typeSymbol = "";
        switch (type) {
            case TODO:
                typeSymbol = "[T]";
                break;
            case EVENT:
                typeSymbol = "[E]";
                break;
            case DEADLINE:
                typeSymbol = "[D]";
                break;
        }
        return typeSymbol + (this.completed ? "[X] " : "[ ] ") + this.list;
    }
}
