import json
from typing import Literal

from pydantic import BaseModel, Field

# Using Pydantic BaseModel makes it easily to transform an object
# into a dictionart
class Student(BaseModel):
    id: int
    lname: str
    fname: str
    gender: Literal["M", "F"]
    birthday: str
    address: str

class StudentList(BaseModel):
    students:list[Student]

# For unmarshalling, just use model_validate from pydantic
def load_json(file_name)->StudentList:
    with open(file_name) as f:
        data=json.load(f)

    db=StudentList.model_validate(data)
    return db

# for marshalling, we just transform the dictionary representation of the object
# into a JSON output
def to_json(students:StudentList)->str:
    return students.model_dump_json(indent=4)

db=load_json("students.json")
print(to_json(db))
