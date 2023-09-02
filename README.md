# Duke Project Pro
> “Your mind is for having ideas, not holding them.” – David Allen

DukePro frees your mind of having to remember things you need to do. It's,
- Text-based
- Best way to learn
- Easy and Simple to use

All you need to do is,

1. Download from [link](https://nus-cs2103-ay2324s1.github.io/website/schedule/week4/project.html)
2. Double click it
3. Add your tasks
4. Let it manage your task for you :blush:
It is also *Emphasize* Free

Features
- [ ]  Managing tasks
- [ ] Managing deadlines (coming soon)
- [ ]  Reminders (coming soon)

If you Java programmer, you can use it to practice Java too. Here's the `main` method:
```
public class Main {
    public static void main(String[] args) {
        Application.launch(MainApp.class, args);
    }
}

```



## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   ```
