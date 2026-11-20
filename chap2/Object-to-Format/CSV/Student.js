const fs = require('fs')
const { parse } = require('csv')
const { stringify } = require('csv')

// The class
class Student{
    constructor(id,lname,fname,gender,birthday,address){
        this.id=id
        this.lname=lname
        this.fname=fname
        this.gender=gender
        this.birthday=birthday
        this.address=address
    }
}

// This function is used to store data in CSV format
// It will get recored as chunks
function printStudents(students){
    const stringifier = stringify({ header: true, encoding: 'utf8' }) // include headers and consider the chunks as strings
    const chunks = []

    stringifier.on('data', (chunk) => {
        chunks.push(chunk) // just save the chunks to a list
    }).on('end',()=>{// there is no more data
        const csvString = chunks.join('');//join the lines using an empty string
        console.log(csvString)
    })

    //makes a loop to write records
    students.forEach(row => stringifier.write(row))
    
    //tells stringifier that data sending is over
    stringifier.end()
}

// Create the stream
const inputStream = fs.createReadStream('mock.csv')

// Define the options
const parser = parse({
    columns: true, // use the first row as object key
    trim: true,  // trim fields
    skip_empty_lines: true //ignore empty lines
})

let students=[]

// Make the pipe between the stream and the parser
// meaning the inputStream output is directed of the paser
inputStream.pipe(parser)

// Handle the parsed data rows as they arrive
parser.on('readable', function () {
    let record
    while ((record = parser.read()) !== null) {
        students.push(new Student(...Object.values(record)))
    }
}).on('end', function () {
    printStudents(students)
    //  console.log(students)
})
