# User Guide to Alex chatbot

## Features: 

### Adding tasks
1. add todo task
 - Command format: todo (description)
2. add deadline task
 - Command format: deadline (description) /by YYYY-MM-DD HHmm
3. add event task
 - Command format: event (description) /from YYYY-MM-DD HHmm /to YYYY-MM-DD HHmm

Note: you do not need to include any parenthesis in your command.

### Editing tasks
1. mark a specific task to be done
 - Command format: mark (task index)
2. unmark a specific task to be undone
 - Command format: unmark (task index)
3. delete a specifc task
 - Command format: delete (task index)

Note: Task index for each task can be known by using command "list". you do not need to include any parenthesis in your command.

### Viewing tasks
1. view all the task(s) added
- Command format: list
2. view all the task(s) related to a specific date
- Command format: YYYY-MM-DD HHmm
3. view all the task(s) that match a certain keyword
- Command format: find (keyword)

Note: You do not need to include any parenthesis in your command.

### Exit appliaction
1. exit the Alex chatbot application
- Command format: bye


## Example Usage: 
### `add a task` - add a event task
Example of usage: event meeting /from 2023-09-28 1000 /to 2023-09-28 1200


```
Got it. I've added this task:
   [E][ ] meeting (from: 10:0 28 September 2023 to: 12:0 28 September 2023)
Now you have 1 tasks in the list.
```

### `delete a task` - delete the first task in the task list
Example of usage: delete 1

```
Noted. I've removed this task:
   [E][ ] meeting (from: 10:0 28 September 2023 to: 12:0 28 September 2023)
Now you have 0 tasks in the list.
```
