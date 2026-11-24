> [!NOTE]
> Data included in file students.json are mock data. They have been generated artificially a test support of the examples.
# Java version
The Java version uses Gson library (see the [website](https://github.com/google/gson)). Again, this is just a proof of concept
example where you should download the JAR files manually. A real Java project may use Maven as a building toolchain.
## What JAR do you need to compile
You need the following JAR:
- gson-2.14.0.jar
## Compiling command
To compile the program, you should use:

`javac -cp ".:gson-2.14.0.jar" JSONStudentList.java`

Note that this command works in Linux. For windows, replace : with ;
## Running command
To compile the program, you should use:

`java -cp ".:gson-2.14.0.jar" JSONStudentList`

# Python version
You should also install `pydantic` using the command `pip install pydantic`. There is no special running settings.

# Node version
You don't any third-party library to run the example.
