## War Room
> "Your mind is for having ideas, not holding them." - David Allen [(Source)](https://dansilvestre.com/productivity-quotes)

War Room frees your mind of having to remember things you need to do. It's,
- text-based
- easy to learn
- ~~FAST~~ SUPER FAST to use

All you need to do is,
1. download it from [here]().
2. double-click it.
3. add your tasks.
4. let it manage your tasks for you 😄 

And it is **FREE**!

If you are a Java programmer, you can use it to practice Java too. Here's the `main` method:

```java
public class Main {
    public static void main(String[] args) {
        Application.launch(MainApp.class, args);
    }
}
```

## Features & User Guide
> "The term task signifies either a Todo or Deadline or Event." - Sher Yew.

### Overview
- [x] Managing Todos
- [x] Managing Deadlines
- [x] Managing Events
- [x] Searching Tasks
- [x] Searching Tasks' Deadlines
- [x] Beautiful GUI

### Viewing Feature (UG)
1. List
   - To view the user's current tasks, try out this command ```list```.

### Managing Features (UG)
1. Todo
   - To add a new todo that you would like to track, try this command ```todo task_name```.
     
2. Deadlines
   - To add a new deadline that you would like to track, try this command ```deadline deadline_name /by YYYY-MM-DD```.
   - Deadlines can also be tracked in hours and minutes, try out this command ```deadline deadline_name /by YYYY-MM-DD HHMM```.
     
3. Events
   - To add a new event that you would like to track, try out this command ```event event_name /from xxx /to yyy```.
     
4. Deletion
   - To delete an entry, try out this command ```delete index_in_list```.
     
5. Mark
   - To signify the completion of a task, try out this command ```mark index_in_list```.
     
6. Unmark
   - To "reverse" the completion of a task, try out this command ```unmark index_in_list```.
     
### Searching Features (UG)
1. Filtering via Task Description
   - To view all tasks that possess a particular task description, try out this command ```find task_description```.
     
2. Filtering via Deadlines
   - This feature only works for **deadlines**.
   - To view all "deadlines" that have a deadline within X days, try out this command ```reminder x_number_of_days```.

### UI (UG)
A task is generally represented as **"[][] task_description"** in the UI.

For the first bracket, the character would correspond to: 
- **T** for todo
- **D** for deadline
- **E** for event.

For the second bracket, the character would correspond to:
- **X** for marked (Completed Task)
- **""** for unmarked (Uncompleted Task)






