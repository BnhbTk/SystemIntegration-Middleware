> [!NOTE]
> Data included in file mock.csv are mock data. They have been generated artificially a test support of the examples.
# Java version
Note that the provided solution is just an illustration of how to use [Apache Commons CSV](https://commons.apache.org/proper/commons-csv/).
A real Java project may use Maven as a building toolchain.
## What JAR do you need to compile
You need the following JAR:
- commons-codec-1.22.1.jar
- commons-csv-1.14.1.jar
- commons-io-2.22.0.jar
## Compiling command
To compile the program, you should use:

`javac -cp ".:./commons-codec-1.22.1.jar:./commons-csv-1.14.1.jar:./commons-io-2.22.0.jar" Student.java`

Note that this command works in Linux. For windows, replace : with ;
## Running command
To compile the program, you should use:

`java -cp ".:./commons-codec-1.22.1.jar:./commons-csv-1.14.1.jar:./commons-io-2.22.0.jar" Student`

# Python version
You need to install Pydantic using the command `pip install pydantic`. There is no special running settings.

# Node version
You need to install the `csv` module using the command `npm install csv`.
