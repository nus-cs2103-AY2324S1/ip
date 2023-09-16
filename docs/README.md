# Milbot User Guide

## Features 

### Adding a new Task

Adds a new task to the task list

#### Adding a new Todo
Format: `todo TASK_NAME`


#### Adding a new Deadline
Format: `deadline DEADLINE_NAME /by DATE`

#### Adding a new Event
Format: `event EVENT_NAME /from DATE_1 /to DATE_2`

Expected outcome:
```
Got it. I've added this task:
  TASK_NAME
Now you have TOTAL_TASKS tasks in the list.
```

![](/event_example.png)

### Deleting a task

Deletes a task from the task list.

Expected outcome:
```
Noted. I've removed this task:
  TASK_NAME
Now you have TOTAL_TASKS tasks in the list.
```

### Viewing task list

Shows the task list.

Format: `list`

Expected outcome:
```
Here are the tasks in your list:
```

### Marking a task as done/undone
Format: 
mark `TASK_INDEX`

Expected outcome:

```
Nice! I've marked this task as done: TASK_NAME
```
![](/mark_example.png)

unmark `TASK_INDEX`

Expected outcome:

```
OK, I've marked this task as not done yet: TASK_NAME
```

### Finding a task
Format: `find TASK_NAME`

Expected outcome:

- If no task found:

```
There is no task matched to your query
```

- If found:
```
Here are the matching tasks in your list:
```
![](/find_example.png)

### Adding a new tag
Format: `add tag #TAG_NAME`

Expected outcome:

```
Got it. I've added this tag:
  TASK_NAME
Now you have TOTAL_TAGS tags in the list.
```

### Deleting tag
Format: `delete tag #TAG_INDEX`

Expected outcome:

```
Noted. I've removed this task:
TASK_NAME
Now you have TOTAL_TASKS tasks in the list.
```

### Tagging a task
Format: `untag TASK_NAME`

Expected outcome:

```
Nice! I've tagged this task: TASK
```

![](/tag_example.png)

### Untagging a task
Format: `tag TASK_NAME TASK_INDEX`

Expected outcome:

```
Nice! I've untagged this task: TASK
```

### Viewing tags list
Format: `tags`
Expected outcome:

```
Here are the tags you have:
```

### Exiting the application
Format: `bye`

Expected outcome:

```
Have a nice day and see you again soon!
```
