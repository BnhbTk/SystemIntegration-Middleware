import {readFileSync} from "node:fs"

// Working with JSON in Javascript is straightforward since JSON is supported natively in this language.
// Therefore, there is nothing to install
class Student {
    constructor(id,lname,fname,gender,birthday,address) {
        this.id=id
        this.lname=lname
        this.fname=fname
        this.gender=gender
        this.birthday=birthday
        this.address=address
    }

}

class StudentList {
    constructor(rawList){
        this.students=rawList.map(l=>new Student(l.id,l.lname,l.fname,l.gender,l.birthday,l.address))
    }
}
// marshalling is made by JSON.stringify and unmarshalling is made by JSON.parse
let stList=new StudentList(JSON.parse(readFileSync("students.json")).students)
console.log(JSON.stringify(stList))
