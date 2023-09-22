# Chatty's User Guide

## Features 

### Add Tasks

Add either a Deadline task, an Event task or a ToDo task.

### Delete Task

Delete unwanted task from the list.

### Mark Task

Mark the added tasks as done or not done.

### Find Task

Find task using keywords

### List Task

List out all the tasks currently in the list.

### Set Alias

Set an alias (an alternative keyword) for any of the command keyword and use the alias as your new keyword.

### Auto Storage and Load

All the task added will be automatically stored and loaded whenever the you start the chatbot.

The alias set will also be remembered there is no need for you to re-set the alias again whenever the chatbot is restarted.


## Usage

### 1. Add Deadline task: `deadline` 
**Format: `deadline task /by yyyy-mm-ddTHH:mm`**
- replace "task" with your task description
- replace "yyyy-mm-dd" with the deadline date
- replace "HH:mm" with the deadline time, remember to add semicolon
- Keep the "T" in yyyy-mm-ddTHH:mm, it is part of the formatting
> 📝Note: The time format (HH:mm) is in 24-hour clock

Example of usage: 

`deadline submit iP /by 2023-09-22T23:59`

Expected outcome:

Description of the outcome.

```
Got it. I've added this task into the list:
     [D][] submit iP (by: 22 September 2023 23:59)
You now have 1 task(s) in the list. 
```

### 2. Add Event task: `event`
**Format: `event task /from yyyy-mm-ddTHH:mm /to yyyy-mm-ddTHH:mm`**
- replace "task" with your task description
- replace "yyyy-mm-dd" with the start / end date
- replace "HH:mm" with the start / end time, remember to add semicolon
- Keep the "T" in yyyy-mm-ddTHH:mm, it is part of the formatting
> 📝Note: The time format (HH:mm) is in 24-hour clock

Example of usage:

`event internship talk /from 2023-09-22T1600 /to 2023-09-22T1800`

Expected outcome:

```
Got it. I've added this task into the list:
     [E][] internship talk (from: 22 September 2023 16:00 to: 22 September 2023 18:00)
You now have 2 task(s) in the list. 
```

### 3. Add ToDo task: `todo`
**Format: `todo task`**
- replace "task" with your task description

Example of usage:

`todo buy pen refill`

Expected outcome:

```
Got it. I've added this task into the list:
     [T][] buy pen refill
You now have 3 task(s) in the list. 
```


### 4. Mark task as done: `done`
**Format: `done number`**
- replace "number" with the number associated with the task you want to mark as done

Example of usage:

`done 1`

Expected outcome:

```
Nice! I've marked this task as done:
    [D][X] submit iP (by: 22 September 2023 23:59)
```

### 5. Mark task as undone: `undone`
**Format: `undone number`**
- replace "number" with the number associated with the task you want to mark as undone

Example of usage:

`undone 1`

Expected outcome:

```
Ok, I've marked this task as not done:
    [D][] submit iP (by: 22 September 2023 23:59)
```

### 6. Delete task: `delete`
**Format: `delete number`**
- replace "number" with the number associated with the task you want to delete

Example of usage:

`delete 1`

Expected outcome:

```
Alright, I've removed this task from the list:
    [D][] submit iP (by: 22 September 2023 23:59)
Now you have 2 task(s) in your list.
```

### 6. Find task: `find`
**Format: `find keyword`**
- replace "keyword" with the keyword you want to find

Example of usage:

`find buy`

Expected outcome:

```
Here are the list of task(s) matching your keyword:
2. [T][] buy pen refill
```

### 7. List task: `list`
**Format: `list`**

Example of usage:

`list`

Expected outcome:

```
Here are the task(s) in your list:
1. [E][] internship talk (from: 22 September 2023 16:00 to: 22 September 2023 18:00)
2. [T][] buy pen refill
```

### 8. Set alias for each command: `set`
**Format: `set alias oriCommand`**
- replace "oriCommand" with the original command word that you are setting
- replace "alias" with the alias you want to set the command word as
> 📝Note: 
> - All the keyword given above are default keyword that can still be used even after u set an alias
> - After setting an alias, you can replace the long keyword with the alias you set (example shown below)

Example of usage:

`set t todo`

Expected outcome:

```
I have set [ t ] as the alias for [ todo ] command
```

**(AFTER SETTING ALIAS)**

Example of usage 

`t buy lunch`

Expected outcome:

```
Got it. I've added this task into the list:
      [T][] buy lunch
You now have 3 task(s) in the list.
```

### 9. Exit the chatbot: `bye`
**Format: `bye`**

Example of usage:

`bye`

Expected outcome:

```
Bye! Hope to see you again soon!
```