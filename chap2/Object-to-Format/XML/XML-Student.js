import { XMLParser,XMLBuilder } from 'fast-xml-parser';
import {readFileSync} from 'node:fs'

class Student {
    constructor(id,lname,fname,gender,birthday,address) {
        this.id=id
        this.lname=lname
        this.fname=fname
        this.gender=gender
        this.birthday=birthday
        this.address=address
    }

    //note how the attribute is indicated here
    exportXMLObject(){
        return {student:{id:this.id,lname:this.lname,fname:this.fname,"@_gender":this.gender,birthday:this.birthday,address:this.address}}
    }
}

class StudentList {
    constructor() {
        this.list=[] 
    }

    setList(rawList){
        for(let l of rawList)
            this.list.push(new Student(l.id,l.lname,l.fname,l["@_gender"],l.birthday,l.address))
    }
    exportXMLObject(){
        let obj={students:[]}
        for (let st of this.list){
            obj.students.push(st.exportXMLObject())
        }
        return obj
    }
}

const xmlData=readFileSync("students.xml") //load the text of the XML document
const parser = new XMLParser({ ignoreAttributes: false }); //the paser will not ignore the attributes
const rawObj=parser.parse(xmlData) //make the parse
const students=new StudentList() //create an instance in which data will be mapped
students.setList(rawObj.students.student) //fill out the sructure

//to store data as an XML document, we define the option of the concversion
const options = {
  ignoreAttributes: false,       // Include attributes starting with @_
  attributeNamePrefix: '@_',     // Match prefix used during parsing
  format: true,                  // Indent output for readability
  indentBy: '  '                 // 2-space indentation
};

const builder = new XMLBuilder(options); //the builder of the XML document
const xmlOutput = builder.build(students.exportXMLObject()); //The builder will just need a dictionary to be converted

console.log(xmlOutput)
