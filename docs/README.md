# User Guide for ~~Duke~~ Cortana

- [Command Summary](#command-summary)
- [Features](#features)
  - [Adding a todo task: todo](#adding-a-todo-task-todo)
  - [Adding a deadline task: deadline](#adding-a-deadline-task-deadline)
  - [Adding an event task: event](#adding-an-event-task-event)
  - [Viewing all tasks: list](#viewing-all-tasks-list)
  - [Marking a task as done: mark](#marking-a-task-as-done-mark)
  - [Marking a task as not done: mark](#marking-a-task-as-not-done-unmark)
  - [Deleting a task: delete](#deleting-a-task-delete)
  - [Finding tasks with a description: find](#finding-tasks-with-a-description-find)
  - [Viewing all tasks on a certain date: taskson](#viewing-all-tasks-on-a-certain-date-taskson)
  - [Finding the next available dates: freedates](#finding-the-next-available-dates-freedates)
 
All dates are in ```YYYY-MM-DD``` format. 


## Command Summary
<table>
<tr>
<th>Command</th><th>Format, Example</th>
</tr>
<tr>
<td>todo</td>
<td>

```todo {description}```
<br>
Example: ```todo task```

</td>
</tr>
<tr>
<td>deadline</td>
<td>

```deadline {description} /by {due date}```
<br>
Example: ```deadline task /by 2023-01-01```

</td>
</tr>
<tr>
<td>event</td>
<td>

```event {description} /from {start date} /to {end date}```
<br>
Example: ```event task /from 2023-01-01 /to 2023-01-02```

</td>
</tr>
<tr>
<td>list</td>
<td>

```list```

</td>
</tr>
<tr>
<td>mark</td>
<td>

```mark {index of task}```
<br>
Example: ```mark 1```

</td>
</tr>
<tr>
<td>unmark</td>
<td>

```unmark {index of task}```
<br>
Example: ```unmark 1```

</td>
</tr>
<tr>
<td>mark</td>
<td>

```delete {index of task}```
<br>
Example: ```delete 1```

</td>
</tr>
<tr>
<td>mark</td>
<td>

```find {description}```
<br>
Example: ```find task```

</td>
</tr>
<tr>
<td>taskson</td>
<td>

```taskson {date}```
<br>
Example: ```taskson 2023-01-01```

</td>
</tr>
<tr>
<td>freedates</td>
<td>

```freedates```

</td>
</tr>
</table>

## Features 

### Adding a todo task: todo

Adds a todo task with just a description

Format: ```todo {description}```

Example: ```todo wash clothes```

### Adding a deadline task: deadline

Adds a deadline task with a description and a due date

Format: ```deadline {description} /by {due date}```

Example: ```deadline essay /by 2023-12-30```

### Adding an event task: event

Adds an event task with a description, a start date and an end date

Format: ```event {description} /from {start date} /to {end date}```

Example: ```event orientation /from 2023-07-20 /to 2023-07-23```

### Viewing all tasks: list

Shows all the tasks currently saved

Format: ```list```

### Marking a task as done: mark

Marks a task as done

Format: ```mark {index of task}```

Example: ```mark 1```

### Marking a task as not done: unmark

Marks a task as not done

Format: ```unmark {index of task}```

Example: ```unmark 1```

### Deleting a task: delete

Deletes a task from the list

Format: ```delete {index of task}```

Example: ```delete 1```

### Finding tasks with a description: find

Finds tasks with the matching description

Format: ```find {description}```

Example: ```find essay```

### Viewing all tasks on a certain date: taskson

Shows all tasks occurring on a certain date

Format: ```taskson {date}```

Example: ```taskson 2023-12-30```

### Finding the next available dates: freedates

Finds up to next 5 dates without tasks

Format: ```freedates```


[Go back to top](#user-guide-for-duke-cortana)
