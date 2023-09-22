# **User Guide - Dong**

## **Features**

### **1. User-Specific Data**

Each user can load data from distinct files. Choosing a different filename will load a unique task list specific to that file.

### **2. Versatile Task Creation**

Users can create various task types using specific commands. These include:
- `todo`
- `deadline`
- `event`

## **Usage Instructions**

### **`{Textbox}` - File Selection**

Choose from a list of available files. Entering a name not on the list will create a new file.

- **Example:** `tasks`
- **Expected outcome:**
    - If the file exists: The main chatbot loads with tasks from the selected file stored in memory.
    - If the file doesn't exist: The chatbot loads with no preloaded tasks.

### **`bye` - Exit Program**

Saves current tasks to the filename provided at the start and closes the program.

- **Example:** `bye`

### **`delete` - Remove a Task**

Removes a specified task from the list.

- **Example:** `delete 1`
- **Expected outcome:** Task 1 will be deleted.
- **Outcome description:**


```
Noted. I 've removed this tasks:
[T][] eat
Now you have 10 tasks in list.
```





### **`list` - Display All Tasks**

Displays all the tasks.

- **Example:** `list`
- **Expected outcome:** All tasks will be listed.
- **Outcome description:**


```
Here are the tasks in your list:
1.[T][] eat
2.[T][] sleep
3.[T][] play
```

### **`unmark` - Mark a Task as Incomplete**

Updates the status of a specified task to incomplete.

- **Example:** `unmark 2`
- **Expected outcome:** Task 2 will be marked as incomplete.
- **Outcome description:**


```
OK,I've marked this task as not done yet:
[T][] eat
```



### **`mark` - Mark a Task as Complete**

Updates the status of a specified task to complete.

- **Example:** `mark 2`
- **Expected outcome:** Task 2 will be marked as complete.
- **Outcome description:**


```
Nice! I've marked this task as done:
[T][X] eat
```


### **`todo` - Add a To-Do Task**

Adds a to-do task to the list.

- **Example:** `todo sleep`
- **Expected outcome:** Todo task "sleep" will be created.
- **Outcome description:**


```
Got it.I've added this task:
[T][] eat
```



### **`deadline` - Add a Task with a Deadline**

Creates a task that has a set deadline.

- **Example:** `deadline return book /by 2/12/2019`
- **Expected outcome:** Deadline task "return book" with deadline Feb 12 2019 will be created.
- **Outcome description:**


```
Got it.I've added this task:
[D][] return book (by:Feb 12 2019)
```



### **`event` - Add an Event Task**

Creates a task with specified start and end times.

- **Example:** `event return book /from 2/12/2019 1800 /to 2/12/2019 2000`
- **Expected outcome:** Event task "return book" with start datetime of Feb 12 2019 6:00pm and end datetime of Feb12 
2019 8:00pm will be created.
- **Outcome description:**


```
Got it.I've added this task:
[E][] return book (from: Feb 12 2019 6:00pm to: Feb12 2019 8:00pm)
```


### **`clear` - Delete All Tasks**

Removes all tasks from the list.

- **Example:** `clear`
- **Expected outcome:** All tasks will be deleted.
- **Outcome description:**


```
Noted. I've removed all tasks!
```


### **`find` - Search for Tasks**

Searches and lists tasks containing a specified keyword.

- **Example:** `find sl`
- **Expected outcome:** All tasks containing "sl" will be displayed.
- **Outcome description:**


```
Here are the matching tasks in your list:
2. [T][] sleep
9. [T][] sleep
```