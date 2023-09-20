# Bocchi 🎸
## Features 
- Adding a task - todo / deadline / event
- Deleting a task - delete
- Viewing tasks - list
- Marking a task - mark
- Unmarking a task - unmark
- Exit the program - bye
  
### Adding a task
1. Add a task with no stipulated duration.

    > Format: todo {task name}
    
    `todo Sleep`

2. Add a task with a deadline.

    > Format: deadline {task name} /by {deadline}
    
    `deadline English homework /by 2023-11-22 23:59:59`

3. Add a task with a duration.

    > Format: event {task name} /from {start} /to {end}

    ` event Badminton /from 2023-11-23 10:00:00 /to 2023-11-23 12:00:00`

### Deleting a task
Delete a task at a specified index.

> Format: delete {index}`

`delete 1`

### Viewing your task list
View your current tasks.

> Format: list

`list`

### Marking a task
Mark a task at a specified index as done.

> Format: mark {index}

`mark 1`

### Unmarking a task
Mark a task at a specified index as not done.

> Format: unmark {index}

`unmark 1`

### Exit the program

> Format: bye

`bye`
