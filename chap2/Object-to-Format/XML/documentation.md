> [!NOTE]
> Data included in file students.xml are mock data. They have been generated artificially a test support of the examples.
# Java version
The Java version uses Jakarta XML Binding (JAXB) (see the [website](https://javaee.github.io/jaxb-v2/)). Again, this is just a proof of concept
example where you should download the JAR files manually. A real Java project may use Maven as a building toolchain.
## What JAR do you need to compile
You need the following JAR:
- jakarta.activation-api-2.1.3.jar
- jakarta.xml.bind-api-4.0.2.jar
- jaxb-core-4.0.5.jar
- jaxb-runtime-4.0.5.jar
## Compiling command
To compile the program, you should use:

`javac -cp ".:jakarta.activation-api-2.1.3.jar:jakarta.xml.bind-api-4.0.2.jar:jaxb-core-4.0.5.jar:jaxb-runtime-4.0.5.jar" StudentList.java`

Note that this command works in Linux. For windows, replace : with ;
## Running command
To compile the program, you should use:

`java -cp ".:jakarta.activation-api-2.1.3.jar:jakarta.xml.bind-api-4.0.2.jar:jaxb-core-4.0.5.jar:jaxb-runtime-4.0.5.jar" StudentList`

# Python version
You should also install `pydantic` and `xmltodict` using the command `pip install pydantic xmltodict`. There is no special running settings.

# Node version
You need to install the `fast-xml-parser` module using the command `npm fast-xml-parser`.
