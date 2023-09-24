# User Guide
![Screenshot of the Ui](/src/main/java/duke/uiux/images/Ui.png)

## Features 

### List your tasks!

![Screenshot of a sample list](/src/main/java/duke/uiux/images/list.png)
Log your tasks to complete!

### Events, Todos, Deadlines

![Screenshot of adding a sample task](/src/main/java/duke/uiux/images/adding_deadline.png)
You can categorise your tasks as an event, deadline or just a to-do in general!

### Mark tasks as comeplete

![Screenshot of marking a task](/src/main/java/duke/uiux/images/mark.png)
You can mark tasks as completed! Well done!

### Delete tasks

![Screenshot of deleting a task](/src/main/java/duke/uiux/images/delete.png)
Tasks can be deleted as well. Please do not skive - Spooderman will not be proud.

### Snooze deadlines

/snooze.png
You can snooze deadlines - but only for a day! Bootman is busy.

## Usage

### `list` - Views your list

View your list of tasks. Remember to stay on track!
Write it in the following format:
`list`

Example of usage: 

`list`

Expected outcome:

Your list of tasks will be printed out.

```
1. [E][ ] press conference (from: Oct 24 2023 1200hrs to: Oct 24 2023 1900hrs)
2. [D][ ] press transfer $100000000 credits (by: Oct 24 2023 1200hrs)
3. [T][ ] save the world
```

### `event` - Adds event

Add an event to your list!
Write it in the following format:
`event` event name `/from` date and time in yyyy-MM-dd HHmm `/to` date and time in yyyy-MM-dd HHmm

Example of usage: 

`event press conference /from 2023-10-24 1200 /to 2023-10-24 1900`

Expected outcome:

The event `press conference` which starts from `2023-10-24 1200` and ends at `2023-10-24 1900` will be added to the list.

```
Got it. I've added this task:
[E][ ] press conference (from: Oct 24 2023 1200hrs to: Oct 24 2023 1900hrs)
Now you have 1 tasks in the list.
```

### `deadline` - Adds deadline

Add a deadline to your list!
Write it in the following format:
`deadline` deadline name `/by` date and time in yyyy-MM-dd HHmm 

Example of usage: 

`deadline transfer $100000000 credits /by 2023-10-24 1200`

Expected outcome:

The deadline `deadline transfer $100000000 credits` which is due by `2023-10-24 1200` will be added to the list.

```
Got it. I've added this task:
[D][ ] press transfer $100000000 credits (by: Oct 24 2023 1200hrs)
Now you have 1 tasks in the list.
```

### `todo` - Adds deadline

Add a todo to your list!
Write it in the following format:
`todo` todo name

Example of usage: 

`todo save the world`

Expected outcome:

The task `save the world` will be added to the list.

```
Got it. I've added this task:
[T][ ] save the world
Now you have 1 tasks in the list.
```

### `Find` - Finds your task

You are a busy man with many tasks. Use this command to find them!
Write it in the following format:
`find` the keyword you are looking for

This will give you a list of tasks that contain the keyword.

Example of usage: 

`find save`

Expected outcome:

Tasks with the word `save` will be displayed.

```
Here are the matching tasks in your list:
2. [T][ ] save the world
```


### `mark` - Marks task as done

When you get on your productive streak and finish your tasks, you can mark them as done!
Tasks with a X are completed. 
Write it in the following format:
`mark` item number

Remember to check if your item is in the list! You can use `list` or `find` to check your list.

Example of usage: 

`mark 1`

Expected outcome:

The 1st task will be marked as done.

```
Nice! I've marked this task as done:
[D][ ] press transfer $100000000 credits (by: Oct 24 2023 1200hrs)
```

### `unmark` - Removes the mark on your task

When you mistakenly marked your tasks as done, you can remove it. Don't make the same mistake again! Spooderman will not be amused.
Write it in the following format:
`unmark` item number

Remember to check if your item is in the list! You can use `list` or `find` to check your list.

Example of usage: 

`unmark 1`

Expected outcome:

The 1st task will be marked as incomplete.

```
OK, I've marked this task as not done yet:
[D][ ] press transfer $100000000 credits (by: Oct 24 2023 1200hrs)
```

### `snooze` - Snoozes a deadline

When you are unproductive and require an extension of deadline(why?), you can use this to push back your deadlines by one day (Spooderman is not amused).
Will only work on deadlines.
Write it in the following format:
`snooze` item number

Remember to check if your deadline is in the list! You can use `list` or `find` to check your list.

Example of usage: 

`snooze 1`

Expected outcome:

The 1st task will be snoozed if possible.

```
Ok, I have snoozed this task by 1 day:
[D][ ] press transfer $100000000 credits (by: Oct 25 2023 1200hrs)
```
