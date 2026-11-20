import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;


//indicate the root element of the XML document
@XmlRootElement(name = "students")
public class StudentList{
    //As this is an ArrayList, we should indicate the tags associated to each object in the list
    @XmlElement(name = "student")
    private ArrayList<JAXBStudent> students;

    public StudentList(){
        students=new ArrayList<>();
    }

    public StudentList(ArrayList<JAXBStudent> students){
        this.students=students;
    }

    public ArrayList<JAXBStudent> getStudents(){
        return students;
    }

    //the unmarshaller will just to instantiate some classes
    public static StudentList loadXML(String fileName) throws Exception{
        File xmlFile=new File(fileName);//load the text
        JAXBContext context = JAXBContext.newInstance(StudentList.class);//create the context of unmarshalling by considering the structure of the liste
        Unmarshaller unmarshaller = context.createUnmarshaller();//the unmarshaller
        return (StudentList) unmarshaller.unmarshal(xmlFile);//go ahead and produce the instances from the XML file
    }

    public String toXML() throws Exception{
        JAXBContext context = JAXBContext.newInstance(StudentList.class);//Again, we create the context by considering the structure of the class
        Marshaller marshaller = context.createMarshaller();//the marshaller
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);//the marshaller will pretty print the output
        StringWriter sw=new StringWriter();//output here is a string
        marshaller.marshal(this, sw);//store the objects as an XML document
        return sw.toString();
    }

    public static void main(String...args) throws Exception{
        StudentList list=loadXML("students.xml");
        System.out.println(list.toXML());
        
    }
}

//This is just the order of tags
@XmlType(propOrder = { "id", "lname", "fname", "birthday", "address"})
class JAXBStudent {
    private int id;
    private String lname;
    private String fname;
    private String gender;
    private String birthday;
    private String address;

    public JAXBStudent(){}

    public JAXBStudent(int id, String lname, String fname, String gender, String birthday, String address) {
        this.id = id;
        this.lname = lname;
        this.fname = fname;
        this.gender = gender;
        this.birthday = birthday;
        this.address = address;
    }

    //with the annotation @XmlElement, we associate a property with a tag
    @XmlElement(name = "id")
    public void setId(int id) {
        this.id = id;
    }

    @XmlElement(name = "lname")
    public void setLname(String lname) {
        this.lname = lname;
    }

    @XmlElement(name = "fname")
    public void setFname(String fname) {
        this.fname = fname;
    }

    //for an attribute we should use the annotation @XmlAttribute
    @XmlAttribute(name="gender")
    public void setGender(String gender) {
        this.gender = gender;
    }

    @XmlElement(name = "birthday")
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @XmlElement(name = "address")
    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getLname() {
        return lname;
    }

    public String getFname() {
        return fname;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getAddress() {
        return address;
    }
}
