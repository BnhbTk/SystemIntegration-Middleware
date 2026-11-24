import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collector;
import java.util.stream.Collectors;

//We are using Google Gson library
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

//There is nothing special to do here. The class structure
//will correspond to the structure of the JSON file
public class JSONStudentList {
    private ArrayList<JSONStudent> students;

    public JSONStudentList(){
        students=new ArrayList<>();
    }

    public JSONStudentList(ArrayList<JSONStudent> students){
        this.students=students;
    }

    public ArrayList<JSONStudent> getStudents(){
        return students;
    }

    @Override
    public String toString(){
        return students.stream().map(JSONStudent::toString).collect(Collectors.joining("\n"));
    }

    //The class file of the main class is passeed to the method fromGson along with the text of the JSON file
    public void loadFromFile(String fileName,Gson gson) {
        try{
            String content=Files.readString(Path.of(fileName));
            students=((JSONStudentList)gson.fromJson(content,JSONStudentList.class)).students;
            
        }catch(Exception e){

        }
    }

    //To marshat the object, just call the method toJson
    public String toJson(Gson gson){
        return gson.toJson(this);
    }

    public static void main(String...args){
        final GsonBuilder builder = new GsonBuilder();
        final Gson gson = builder.setPrettyPrinting().serializeNulls().create();
        JSONStudentList students=new JSONStudentList();
        students.loadFromFile("students.json",gson);
        System.out.println(students.toJson(gson));
    }
}

class JSONStudent {
    private int id;
    private String lname;
    private String fname;
    private String gender;
    private String birthday;
    private String address;

    public JSONStudent(int id, String lname, String fname, String gender, String birthday, String address) {
        this.id = id;
        this.lname = lname;
        this.fname = fname;
        this.gender = gender;
        this.birthday = birthday;
        this.address = address;
    }

    @Override
    public String toString() {
        return "JSONStudent [id=" + id + ", lname=" + lname + ", fname=" + fname + ", gender=" + gender + ", birthday="
                + birthday + ", address=" + address + "]";
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

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
