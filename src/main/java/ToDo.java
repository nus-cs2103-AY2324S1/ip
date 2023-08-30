public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public  String saveString() {
        return "T" + super.saveString();
<<<<<<< HEAD
    }

    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
=======
>>>>>>> branch-Level-7
    }

    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }
}