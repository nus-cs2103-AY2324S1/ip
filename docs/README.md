# User Guide for Jarvis Personal Assistant Chatbot
---

Jarvis is a Personal Assistant Chatbot that helps users to manage their day-to-day tasks.

## Features
---
### Creating Tasks

You can request Jarvis to create 3 different types of tasks:
- ***Todo*** - Tasks that are to be done regardless of time.
- ***Event*** - Tasks that are to be done between specified date / time.
- ***Deadline*** - Tasks that are to be done by a certain date / time.

### Marking Tasks

You can ask Jarvis to mark your tasks as done once you have completed it them.

### Unchecking Tasks

You can request Jarvis to uncheck your marked tasks if you realise the tasks is not done.

### Listing Tasks

You can ask Jarvis to list all existing tasks.

### Deleting Tasks

You can get Jarvis to delete existing tasks.

### Finding Tasks

You can request Jarvis to search through all your existing task and find task(s) that matches your search keyword.

### Automatic Saving and Loading of Tasks

Jarvis automatically saves tasks into a local file as you enter commands and automatically loads tasks when you start Jarvis.

## Usage
---
### `todo` - Adds a new todo task

Jarvis creates a todo task and adds it to the current list of tasks.

***Format:***
`todo DESCRIPTION PRIORITY`
`DESCRIPTION`: Description of the todo task.
`PRIORITY`: Priority of the todo task. It can only be low/medium/high.

***Example of usage:***
- Asking Jarvis to create a todo with description `build Mark III suit` and `medium` priority.
  `todo build Mark III suit medium`

***Expected outcome:***
- A new todo created and added to the list of tasks.
```  
As you please Sir, I've added the task:
	[T][ ][M] build Mark III suit
You have now 1 tasks in the list Sir.
```

### `deadline` - Adds a new deadline task

Jarvis creates a deadline task and adds it to the current list of tasks.

***Format:***
`deadline DESCRIPTION /BY PRIORITY`
`DESCRIPTION`: Description of the todo task.
`BY`: Date/time which the deadline task should be completed by.
`PRIORITY`: Priority of the deadline task. It can only be low/medium/high.

***Example of usage:***
- Asking Jarvis to create a deadline with description `destroy Ultron` and `high` priority.
  `todo destroy Ultron /by Monday 2pm high`

***Expected outcome:***
- A new deadline created and added to the list of tasks.
```  
As you please Sir, I've added the task:
	[D][ ][M] destroy Ultron (by: Monday 2pm)
You have now 1 tasks in the list Sir.
```

### `event` - Adds a new event task

Jarvis creates a event task and adds it to the current list of tasks.

***Format:***
`event DESCRIPTION /from FROM /to TO PRIORITY`
`DESCRIPTION`: Description of the todo task.
`FROM`: Start date/time of the event task.
`TO`: End date/time of the event task.
`PRIORITY`: Priority of the event task. It can only be low/medium/high.

***Example of usage:***
- Asking Jarvis to create a deadline with description `celebration for winning` and `low` priority.
  `event celebration for winning /from Tuesday 6pm /to Wednesday 2am low`

***Expected outcome:***
- A new event created and added to the list of tasks.
```  
As you please Sir, I've added the task:
	[E][ ][L] celebration for winning (from: Tuesday 6pm to: Wednesday 2am)
You have now 1 tasks in the list Sir.
```

### `mark` - Marks an existing task as done

Jarvis marks the task with the specified index as done.

***Format:***
`mark INDEX`
`INDEX`: Index of the task to be marked.

***Example of usage:***
- Asking Jarvis to mark the task with index `1` as done.
  `mark 1`

***Expected outcome:***
- Task with index `1` is marked as done. Task marked done will have a visible `X` in the 2<sup>nd</sup> square bracket.
```  
Roger that Sir, I've marked this task as done:
	[T][X][M] build Mark III suit
```

### `uncheck` - Unchecks an existing task marked as done

Jarvis unchecks the task previously marked as done with the specified index as not done.

***Format:***
`uncheck INDEX`
`INDEX`: Index of the task to be unchecked.

***Example of usage:***
- Asking Jarvis to uncheck the task with index `1` as not done.
  `uncheck 1`

***Expected outcome:***
- Task with index `1` is marked as not done. Task marked not done will have an existing `X` in the 2<sup>nd</sup> square bracket removed.
```  
Roger that Sir, I've marked this task as done:
	[T][ ][M] build Mark III suit
```

### `list` - Lists all existing tasks

Jarvis lists all existing tasks.

***Format:***
`list` `

***Example of usage:***
- Asking Jarvis to list all existing tasks.
  `list`

***Expected outcome:***
- A list of all existing tasks.
```  
Roger that Sir, I've marked this task as done:
1.[T][ ][H] read book  
2.[T][X][H] read bible  
3.[D][ ][H] return book (by: Monday)  
4.[E][ ][H] project meeting (from: Monday 2pm to: 4pm)
```

### `delete` - Deletes an existing task

Jarvis deletes the task with the specified index.

***Format:***
`delete INDEX`
`INDEX`: Index of the task to be deleted.

***Example of usage:***
- Asking Jarvis to delete the task with index `1`.
  `delete 1`

***Expected outcome:***
- Task with index `1` is deleted. Deleted tasks will no longer appear when the command `list` is entered.
```  
Alright Sir, I've removed this task:
	[T][X][M] build Mark III suit
You have now 2 tasks in the list Sir.
```

### `find` - Searches existing tasks

Jarvis finds all the task with the specified keyword.

***Format:***
`find KEYWORD`
`KEYWORD`: Keyword to be searched.

***Example of usage:***
- Asking Jarvis to delete the task with index `1`.
  `find book`

***Expected outcome:***
- Task(s) with the containing the word "search" will be shown in a list.
```  
Here are the matching task in your list Sir:
1.[D][][M] return book (by: Monday)
2.[T][][M] return book
```
