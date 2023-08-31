public class ToDo extends Task {
    public ToDo(String description){
        super(description);
    }

    @Override
    public String toCommaString() {
        String[] commaStringValues = {
                "T",
                this.isDone ? "X" : " ",
                this.description,
        };
        String commaString = String.join(",", commaStringValues);
        return commaString;
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
