public class Parser {

    public Task dataToTask(String data) throws DukeException {
        String taskType = data.substring(0, 1);
        String taskData = data.substring(4);
        Task task = null;
        int firstSplitIndex = -1;
        int secondSplitIndex = -1;
        int thirdSplitIndex = -1;
        String desc = "";
        boolean isDone = false;

        switch (taskType) {
        case "T":
            firstSplitIndex = taskData.indexOf("|");
            isDone = taskData.substring(0, firstSplitIndex - 1).equals("1");
            desc = taskData.substring(firstSplitIndex + 2);
            task = new Todo(desc);
            task.isDone = isDone;
            break;
        case "D":
            firstSplitIndex = taskData.indexOf("|");
            secondSplitIndex = taskData.indexOf("|", firstSplitIndex + 1);
            isDone = taskData.substring(0, firstSplitIndex - 1).equals("1");
            desc = taskData.substring(firstSplitIndex + 2, secondSplitIndex - 1);
            String by = taskData.substring(secondSplitIndex + 2);
            task =  new Deadline(desc, by);
            task.isDone = isDone;
            break;
        case "E":
            firstSplitIndex = taskData.indexOf("|");
            secondSplitIndex = taskData.indexOf("|", firstSplitIndex + 1);
            thirdSplitIndex = taskData.indexOf("|", secondSplitIndex + 1);
            isDone = taskData.substring(0, firstSplitIndex - 1).equals("1");
            desc = taskData.substring(firstSplitIndex + 2, secondSplitIndex - 1);
            String from = taskData.substring(secondSplitIndex + 2, thirdSplitIndex - 1);
            String to = taskData.substring(thirdSplitIndex + 2);
            task = new Event(desc, from, to);
            task.isDone = isDone;
            break;
        }
        return task;
    }
}
