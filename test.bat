@ECHO OFF



@REM Clear any previous build files, remake bin\
del /S /Q .\bin\ > nul
rmdir /S /Q .\bin\
mkdir .\bin\

@REM Compile
javac .\src\com\cloud\chatbot\*.java -cp .\src\ -Xlint:none -d .\bin\
IF ERRORLEVEL 1 (
    echo:
    echo ^>^>^> javac failed
    exit /B 1
)

@REM Clear previous output
del .\tests\ACTUAL.txt

@REM Test main class, generate output
java -classpath .\bin\ com.cloud.chatbot.Cloud < .\tests\input.txt > .\tests\ACTUAL.txt

@REM Compare output with expected
FC .\tests\ACTUAL.txt .\tests\EXPECTED.txt
