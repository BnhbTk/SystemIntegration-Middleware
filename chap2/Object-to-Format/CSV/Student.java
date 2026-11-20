// This solution uses Apache Commons CSV: https://commons.apache.org/proper/commons-csv/
// For compiling and running steps, see documentation.md
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.QuoteMode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Student{
    public int id;
    public String lname;
    public String fname;
    public char gender;
    public String birthday;
    public String address;

    
    public Student(int id, String lname, String fname, char gender, String birthday, String address) {
        this.id = id;
        this.lname = lname;
        this.fname = fname;
        this.gender = gender;
        this.birthday = birthday;
        this.address = address;
    }


    @Override 
    public String toString(){
        return String.format("Item(%d,'%s','%s',%s,%s,'%s')", id,lname,fname,gender,birthday,address);
    }

    public static ArrayList<Student> loadStudents(String fileName) throws IOException {
        //CSV is a class the defines the options for reading from a CSV file
        CSVFormat readFormat = CSVFormat.DEFAULT.builder()
            .setDelimiter(',') //delimiter is ,
            .setQuote('"') //use string quotes
            .setIgnoreSurroundingSpaces(true) //triming the fields
            .setNullString("N/A") //a field with value N/A is intepreted a null string
            .setHeader("id", "lname", "fname", "gender","birthday","address") //defines the header
            .setSkipHeaderRecord(true) //do not include the first row in data
            .get();
        // parse a file
        CSVParser parser=readFormat.parse(Files.newBufferedReader(Paths.get(fileName)));
        ArrayList<Student> students=new ArrayList<>();
        //Read the records then fill out the class instances
        for (CSVRecord record : parser) {
            Student st=new Student(Integer.parseInt(record.get("id")),record.get("lname"),record.get("fname"),record.get("gender").charAt(0),record.get("birthday"),record.get("address"));
            students.add(st);
        }
        return students;
    }

    public static String printStudents(ArrayList<Student> students) throws IOException {
        StringWriter stringWriter = new StringWriter();
        CSVFormat printFormat = CSVFormat.DEFAULT.builder()
            .setDelimiter(',')
            .setQuote('"')
            .setIgnoreSurroundingSpaces(true)
            .setNullString("N/A")
            .setHeader("id", "lname", "fname", "gender","birthday","address")
            .get();
        CSVPrinter csvPrinter = new CSVPrinter(stringWriter, printFormat);
        for (Student st : students)//Write each student as a record
            csvPrinter.printRecord(st.id,st.lname,st.fname,st.gender,st.birthday,st.address);
        return stringWriter.toString().trim();
    }


    public static void main(String...args) throws Exception{        
        ArrayList<Student> students=loadStudents("mock.csv");
        System.out.println(printStudents(students));
    }

    
}
