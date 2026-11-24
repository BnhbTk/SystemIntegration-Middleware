# Required installation
Please visit [this site](https://protobuf.dev/installation/) to see how to install the toolchain of protobuf. The most important tool is
`protoc`: the compiler that will generate the bindings for many languages, among which, we can find Python and Java. Node.js needs a special
library to be generate the data conversion code.
# Code generation
After installing the required binaries, Java code generation can be made with (the code will be placed in the subfolder `src`):

`protoc --proto_path=. --java_out=./src ./student_mangement.proto`

For the Python code generation, run this command:

`protoc --proto_path=. --python_out=. --pyi_out=. ./student_mangement.proto`
