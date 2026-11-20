from typing import Any, Literal

import xmltodict
from pydantic import BaseModel, Field, field_validator

class Student(BaseModel):
    key: int
    lname: str
    fname: str
    # This is to declare gender as an XML attribute
    gender: Literal["M", "F"] = Field(
        validation_alias="@gender", serialization_alias="gender"
    )
    birthday: str
    address: str

class StudentList(BaseModel):
    students:list[Student]=Field(serialization_alias="students")
    
    @field_validator('students', mode='before')
    @classmethod
    def ensure_list(cls, value: Any) -> Any:
        # If xmltodict passes a single dictionary instead of a list, wrap it in a list
        if isinstance(value, dict):
            return [value]
        return value

# Load data from the XML file and map it to the Student class. XML is first converted into
# a dictionary, then this is passed as argument to the constructor of the classes
def load_xml(file_name)->StudentList:
    with open(file_name) as f:
        source=f.read()

    parsed_dict = xmltodict.parse(source)
    return StudentList(students=parsed_dict["students"]["student"])

# Store data as an XML document. The function returns a string representation of the document
def to_xml(students:StudentList):
    model_dict=students.model_dump()
    return xmltodict.unparse({"students":{"student":model_dict["students"]}}, pretty=True, full_document=False)
    
students=load_xml("students.xml")
print(to_xml(students))
