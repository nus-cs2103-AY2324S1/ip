# Aikent Bot
Aikent Bot is a Java bot that helps you store your tasks. You can manage and update your tasks.

# User Guide
Use can view the user guide [here](https://yeobohshin.github.io/ip/).

## Commands 
Aikent bot will response to certain commands
### Add ToDo Task
ToDo Task are task with no specifications.
    
    todo [DESCRIPTION]

### Add Deadline Task
Deadline Task are task with deadlines.

    deadline [DESCRIPTION] /by [DATE]

### Add Event Task
Event Task are task that has a start and end date.

    event [DESCRIPTION] /from [STARTDATE] /to [ENDDATE]

### Mark task
Marks the task as completed.

    mark [INDEX]

### Unmark task
Marks the task as incompleted.

    unmark [INDEX]

### Delete task
Deletes a task from the task list.

    delete [INDEX]

### Find Tasks
Search up task that matches the user input.

    find [DESCRIPTION]

### Update Task
Update description of a task.

    update [INDEX] /description [DESCRIPTION]

Update deadline of a deadline task.

    update [INDEX] /by [DEADLINE]

Update start date of event task.

    update [INDEX] /from [STARTDATE]

Update end date of event task.
    
    update [INDEX] /to [ENDDATE]

### Prints list
Prints all the tasks in the list currently.
    
    list

### Save list
Saves all the task in the list currently to the hard disk.

    bye
