# Milbot

## About the application
Looking to optimize task management? Meet Mil, your trusted desktop application chatbot, ready to assist you every step of the way. :100:

Get the latest version of the app [here](https://github.com/songgthu/ip).

## Features 

### Adding a new Task

Adds a new task to the task list

#### Adding a new Todo

A Todo is a task with only task name.

Format: `todo TASK_NAME`

#### Adding a new Deadline

A Deadline is a task with a completion date.

Format: `deadline DEADLINE_NAME /by DATE`

#### Adding a new Event

An Event is a task with a time range.

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

Marks a task to be completed or incompleted.

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

Find tasks that contains a matching substring.

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

Adds a new tag to the tag list.

Format: `add tag #TAG_NAME`

Expected outcome:

```
Got it. I've added this tag:
  TASK_NAME
Now you have TOTAL_TAGS tags in the list.
```

### Deleting tag

Deletes a tag from a tag list.

Format: `delete tag TAG_INDEX`

Example: delete tag 1

Expected outcome:

```
Noted. I've removed this task:
TASK_NAME
Now you have TOTAL_TASKS tasks in the list.
```

### Tagging a task

Associates a tag with a specific task in the task list.

Format: `tag TASK_NAME TASK_INDEX`

Expected outcome:

```
Nice! I've tagged this task: TASK
```

![](/tag_example.png)

### Untagging a task

Removes a tag from a specific task in the task list.

Format: `untag TASK_NAME`

Expected outcome:

```
Nice! I've untagged this task: TASK
```

### Viewing tags list

Shows all the tags in the tag list.

Format: `tags`

Expected outcome:

```
Here are the tags you have:
```

### Exiting the application

Show the goodbye message and save the data.

Format: `bye`

Expected outcome:

```
Have a nice day and see you again soon!
```
