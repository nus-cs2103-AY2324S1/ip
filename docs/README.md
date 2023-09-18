### Chat User Guide

Chat is a deranged and unconventional twist on personal assistant applications. Unlike its more sober counterparts, Chat boasts the riotous and unpredictable personality of [Tw*tch chat](https://www.twitch.tv/), making it a one-of-a-kind platform for managing your schedule and tasks.

## Features 

### Adding Tasks

Chat keeps track of your tasks so you don't have to! Just enter the name and type of task you want, and Chat will remember this task forever!

### Storing Tasks

Chat stores its tasks in a txt file, so your data will be saved each time you use chat. Don't worry about forgetting any of your tasks!

### Memes

Just type the keyword 'meme' to get a surprise from your favorite streamer!

## Usage

### `todo` - Creates a todo

Just type:

`todo (task description)`

to add a task with no time parameters.

Expected outcome:

```
I've added this task:
[T][ ] watch youtube during lunch
Now you have 1 task in the list.
```

### `deadline` - Creates a deadline

Just type:

`todo (task description) /by (task deadline date and/or time)`

to add a task with a deadline.

For example, typing
```
deadline grind valorant /by 2023/09/18
```

gives

```
I've added this event:
[D][ ] grind valorant (by: 2023-09-18)
Now you have 2 tasks in the list.
```

### `event` - Creates an event

Just type:

`event (task description) /from (task start date and/or time) /to (task end date and/or time)`

to add a task with both starting and ending date/times

For example, typing
```
event meme convention /from 2023/09/18 12:00 /to 16:00
```

gives

```
I've added this event:
[E][ ] meme convention (from: 2023-09-18 12:00 to: 2023-09-18 16:00)
Now you have 3 tasks in the list.
```

### `list` - List your tasks

Just type `list` to get a detailed summary of each of your tasks.

### `find` - Find a specific task

Just type `task (keyword)` to find any tasks containing the specified keyword.

### `sort` - Sort your tasks

Just type `sort (name/date/type)` to sort your tasks alphabetically, chronologically, or by type respectively.

### `mark` - Mark your tasks as done

Just type `mark (index)` to mark the task at the specified index as done.

### `unmark` - Mark your tasks as not done

Just type `unmark (index)` to mark the task at the specified index as not done.

### `delete` - Delete your tasks

Just type `delete (index)` to delete the task at the specified index.

### `bye` - Quit the app

Just type `bye` to quit the app.
> "Bye gamer" - Chat
