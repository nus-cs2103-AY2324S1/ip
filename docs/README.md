# User Guide for ~~Duke~~ Davidson

- [Command Summary](#command-summary)
- [Features](#features)
    - [Adding a todo task: todo](#adding-a-todo-task-todo)
    - [Adding a deadline task: deadline](#adding-a-deadline-task-deadline)
    - [Adding an event task: event](#adding-an-event-task-event)
    - [Viewing all tasks: list](#viewing-all-tasks-list)
    - [Marking a task as done: mark](#marking-a-task-as-done-mark)
    - [Un-marking a task as done: mark](#marking-a-task-as-not-done-unmark)
    - [Deleting a task: delete](#deleting-a-task-delete)
    - [Finding tasks based on a keyword: find](#finding-tasks-with-a-description-find)
    - [Viewing all tasks on a certain date: tasks on](#viewing-all-tasks-on-a-certain-date-tasks-on)
    - [View all deadlines in sorted order: sort](#view-all-deadlines-in-sorted-order-sort)


## Command Summary
<table>
<tr>
<th>Command</th><th>Format, Example</th>
</tr>
<tr>
<td>todo</td>
<td>

`todo {description}`

Example: `todo task`

</td>
</tr>
<tr>
<td>deadline</td>
<td>

`deadline {description} /by {due date}`

Example: `deadline task /by 20/02/2023`

</td>
</tr>
<tr>
<td>event</td>
<td>

`event {description} /from {start date} /to {end date}`

Example: `event task /from 20/02/2023 /to 21/02/2023`

</td>
</tr>
<tr>
<td>list</td>
<td>

`list`

</td>
</tr>
<tr>
<td>mark</td>
<td>

`mark {index of task}`

Example: `mark 1`

</td>
</tr>
<tr>
<td>unmark</td>
<td>

`unmark {index of task}`

Example: `unmark 1`

</td>
</tr>
<tr>
<td>mark</td>
<td>

`delete {index of task}`

Example: `delete 1`

</td>
</tr>
<tr>
<td>mark</td>
<td>

`find {keyword}`

Example: `find task`

</td>
</tr>
<tr>
<td>tasks on</td>
<td>

`tasks on {date}`

Example: `tasks on 20/02/2023`

</td>
</tr>
<tr>
<td>sort</td>
<td>

`sort`

</td>
</tr>
</table>

## Features

### Adding a todo task: todo

Add a todo task with a description

Format: ```todo {description}```

### Adding a deadline task: deadline

Add a deadline task with a description and a due date

Format: ```deadline {description} /by {due date}```

### Adding an event task: event

Add an event task with a description, start date and end date

Format: ```event {description} /from {start date} /to {end date}```

### Viewing all tasks: list

Show all previously added tasks

Format: ```list```

### Marking a task as done: mark

Mark a task as done

Format: ```mark {index of task}```

### Marking a task as not done: unmark

Un-mark a task as done

Format: ```unmark {index of task}```

### Deleting a task: delete

Delete a task from the list

Format: ```delete {index of task}```

### Finding tasks with a description: find

Find tasks with the matching keyword

Format: ```find {keyword}```

### Viewing all tasks on a certain date: tasks on

Show all tasks occurring on a certain date

Format: ```tasks on {date}```

### View all deadlines in sorted order: sort

Show all deadline tasks in sorted order (from earliest to latest)

Format: ```sort```


[Go back to top](#user-guide-for-duke-davidson)
