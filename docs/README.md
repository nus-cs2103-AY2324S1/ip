# User Guide
Duke is a chatbot that can be used to manage multiple type of tasks.


## Quickstart
1. download the .jar file from [here](https://github.com/dinde2004/ip/releases/tag/A-Jar).
2. execute the .jar file by using command `java -jar path/to/jar/file`.
3. add your tasks.
4. let the chatbot handle tasks for you 😉.

## Features 

### Add todo task
Add todo task with description
Format: `todo {description}` or `t {description}`

### Add deadline task
Add deadline task with description and a deadline date
Format: `deadline {description} /by {deadline date}` or `d {description} /by {deadline date}`

### Add event task
Add event task with description, start date and end date
Format: `event {description} /from {start date} /to {end date}` or `e {description} /from {start date} /to {end date}`

### View all tasks
Show all added tasks
Format: `list` or `ls` or `l`

### Mark task done
Mark a task as done
Format: `mark {task index}`

### Mark task not done
Mark a task as not done
Format: `unmark {task index}`

### Delete task
Delete a task
Format: `delete {task index}`

## Important note
- The index of the task is 1-indexed. This means that the index of the task in the task list starts from 1.