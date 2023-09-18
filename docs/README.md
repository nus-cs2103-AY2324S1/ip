# Aikent Bot
Aikent Bot is a Java bot that helps store your tasks. You can manage and update your tasks.

# User Guide
Use can view the user guide [here](https://yeobohshin.github.io/ip/).

## Set-up
1. Ensure that **Java 11.0.19** is installed on your local machine.
2. Download the **latest** _Aikent.jar_ release **v.02** from the Github repository.
3. Open your terminal and navigate to the directory where the jar file is stored.
4. Type in this command into the terminal to run the chat bot.
   
       java -jar Aikent.jar
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
Saves all the task in the list currently to the hard disk and closes the program.

    bye

## FAQ
*How do I save my tasks?*

Ensure that the bye command is executed before closing the application to update all the task to the hard disk.
