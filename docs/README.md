# Bareum
## Features
### Create a to-do: `todo`
Create a task without any date/time attached to it.
Format: `todo <description>`

Example: `todo visit theme park` adds a to-do for visit a theme park

**Expected output**
```
I have added this task:  
[T][ ] visit theme park [Tag: None]
You now have 1 task(s) in your list.
```


### Create a deadline: `deadline`
Create a task that need to be done before a specific date/time
Format: `deadline <description> /by <due date>`
- The due date must be in `YYYY-MM-DD`

Example: `deadline submit report /by 2023-09-30` adds a deadline for submitting a report by 30 September 2023

**Expected output**
```
I have added this task:  
[D][ ] submit report (by: Sep 30 2023) [Tag: None]
You now have 1 task(s) in your list.
```  

### Create an event: `event`
Create a task that start at a specific date/time and end at a specific date/time.
Format: `event <description> /from <start time> /to <end time>`

Example: `event project meeting /from 2pm /to 4pm` adds an event for a project meeting that starts at 2pm and ends at 4pm

**Expected output**
```
I have added this task:  
[E][ ] project meeting (from: 2pm to: 4pm) [Tag: None]
You now have 1 task(s) in your list.
```

### List all tasks: `list`
Get an overview of all tasks currently in the task list.
Format: `list`

### Mark a task as done: `mark`
Mark a task as done after you have completed it.
Format: `mark <index>`
- The index refers to the index number shown in the task list
    - It must be a positive integer 1, 2, 3, etc.
    - It must **not** exceed the total number of tasks in the task list

Example: `mark 2` marks the second task in the task list as done

**Expected output**
If the task list is currently
```
1. [T][ ] visit theme park [Tag: None]
2. [D][ ] submit report (by: Sep 30 2023) [Tag: None]
3. [E][ ] project meeting (from: 2pm to: 4pm) [Tag: None]
```
marking the second task will result in this list
```
1. [T][ ] visit theme park [Tag: None]
2. [D][X] submit report (by: Sep 30 2023) [Tag: None]
3. [E][ ] project meeting (from: 2pm to: 4pm) [Tag: None]
```

### Unmark a task as done: `unmark`
Unmark a task as done if you have not completed it.
Format: `unmark <index>`
- The index refers to the index number shown in the task list
    - It must be a positive integer 1, 2, 3, etc.
    - It must **not** exceed the total number of tasks in the task list

Example: `unmark 2` marks the second task in the task list as not done

**Expected output**
If the task list is currently
```
1. [T][ ] visit theme park [Tag: None]
2. [D][X] submit report (by: Sep 30 2023) [Tag: None]
3. [E][ ] project meeting (from: 2pm to: 4pm) [Tag: None]
```
unmarking the second task will result in this list
```
1. [T][ ] visit theme park [Tag: None]
2. [D][ ] submit report (by: Sep 30 2023) [Tag: None]
3. [E][ ] project meeting (from: 2pm to: 4pm) [Tag: None]
```

### Delete a task: `delete`
Delete the task at the specified index.
Format: `delete <index>`
- The index refers to the index number shown in the task list
    - It must be a positive integer 1, 2, 3, etc.
    - It must **not** exceed the total number of tasks in the task list

Example: `delete 2` deletes the second task in the task list

**Expected output**
If the task list is currently
```
1. [T][ ] visit theme park [Tag: None]
2. [D][ ] submit report (by: Sep 30 2023) [Tag: None]
3. [E][ ] project meeting (from: 2pm to: 4pm) [Tag: None]
```
marking the second task will result in this list
```
1. [T][ ] visit theme park [Tag: None]
2. [E][ ] project meeting (from: 2pm to: 4pm) [Tag: None]
```

### Find a task with a keyword: `find`
Find all tasks containing the specified keyword.
Format: `find <keyword>`

Example: `find park` finds all tasks containing the word book

**Expected output**
If the task list is currently
```
1. [T][ ] visit theme park [Tag: None]
2. [D][ ] submit report (by: Sep 30 2023) [Tag: None]
3. [E][ ] project meeting (from: 2pm to: 4pm) [Tag: None]
```
finding the keyword 'park' result in this list
```
1. [T][ ] visit theme park [Tag: None]
```

### Tag a task: `tag`
Tag a task with the specified keyword.
Format: `tag <index> /tag <keyword>`
- The index refers to the index number shown in the task list
    - It must be a positive integer 1, 2, 3, etc.
    - It must **not** exceed the total number of tasks in the task list

Example: `tag 3 /tag CS2103T` adds the tag `CS2103T` to the third task in the task list

**Expected output**
If the task list is currently
```
1. [T][ ] visit theme park [Tag: None]
2. [D][ ] submit report (by: Sep 30 2023) [Tag: None]
3. [E][ ] project meeting (from: 2pm to: 4pm) [Tag: None]
```
tagging the third task with the tag 'CS2103T' will result in this list
```
1. [T][ ] visit theme park [Tag: None]
2. [D][ ] submit report (by: Sep 30 2023) [Tag: None]
3. [E][ ] project meeting (from: 2pm to: 4pm) [Tag: CS2103T]
```

### Exit the application: `bye`
Exit the application and save your existing task list to your computer.
Format: `bye`