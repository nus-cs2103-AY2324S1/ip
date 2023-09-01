package deterministicparrot;

import java.util.ArrayList;
import java.util.List;

class TaskList {
    public class SearchResult{
        public int index;
        public Task task;
    }
    private List<Task> list;
    public TaskList() {
        this.list = new ArrayList<>();
    }
    public TaskList(List<Task> tasks) {
        this.list = tasks;
    }

    public String formatAsString(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < this.list.size(); i++){
            sb.append("     " + (i+1) + ". " + this.list.get(i) + "\n");
        }
        return sb.toString();
    }

    public String serialize(){
        StringBuilder sb = new StringBuilder();
        for (Task task : this.list) {
            if (task instanceof ToDo) {
                sb.append("T | ").append(task.getIsDone() ? "1" : "0").append(" | ").append(task.getName()).append("\n");
            } else if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                sb.append("D | ").append(task.getIsDone() ? "1" : "0").append(" | ").append(task.getName()).append(" | ").append(DPUtils.saveFormatDateTime(deadline.by)).append("\n");
            } else if (task instanceof Event) {
                Event event = (Event) task;
                sb.append("E | ").append(task.getIsDone() ? "1" : "0").append(" | ").append(task.getName()).append(" | ").append(DPUtils.saveFormatDateTime(event.timeStart)).append(" ").append(DPUtils.saveFormatDateTime(event.timeEnd)).append("\n");
            }
        }
        return sb.toString();
    }
    public static TaskList deserialize(String rawData) throws Exception {
        List<Task> tasks = new ArrayList<>();
        String[] lines = rawData.split("\n");

        for (String line : lines) {
            String[] data = line.split(" \\| ");
            switch (data[0]) {
                case "T":
                    ToDo todo = new ToDo(data[2]);
                    if (data[1].equals("1")) todo.markAsDone();
                    tasks.add(todo);
                    break;
                case "D":
                    Deadline deadline = new Deadline(data[2], data[3]);
                    if (data[1].equals("1")) deadline.markAsDone();
                    tasks.add(deadline);
                    break;
                case "E":
                    String[] time = data[3].split(" ");
                    Event event = new Event(data[2], time[0], time[1]);
                    if (data[1].equals("1")) event.markAsDone();
                    tasks.add(event);
                    break;
            }
        }
        return new TaskList(tasks);
    }

    public int getSize(){
        return this.list.size();
    }

    private void checkIfValidIdx(int idx) throws DeterministicParrotException{
        if (idx <= 0 || idx > list.size()) {
            throw new DeterministicParrotException("Invalid task number.");
        }
    }
    public Task getTask(int idx) throws DeterministicParrotException{
        checkIfValidIdx(idx);
        return this.list.get(idx-1);
    }
    public Task deleteTask(int idx) throws DeterministicParrotException{
        checkIfValidIdx(idx);
        Task t = this.list.remove(idx-1);
        return t;
    }
    public int addTask (Task t){
        this.list.add(t);
        return getSize();
    }

    public Task markAsDone(int idx) throws DeterministicParrotException{
        Task t = getTask(idx);
        t.markAsDone();
        return t;
    }

    public Task markAsUndone(int idx) throws DeterministicParrotException{
        Task t = getTask(idx);
        t.markAsUndone();
        return t;
    }
    public List<SearchResult> findTask(String keyword){
        List<SearchResult> results = new ArrayList<>();
        for(int i = 0; i < this.list.size(); i++){
            Task t = this.list.get(i);
            if(t.getName().contains(keyword)){
                SearchResult sr = new SearchResult();
                sr.index = i+1;
                sr.task = t;
                results.add(sr);
            }
        }
        return results;
    }
}
