
import csv # exists in the standard library
from typing import Literal

from pydantic import BaseModel # should be installed with pip install pydantic

# The class of the model
class Student(BaseModel):
    id:int
    lname:str
    fname:str
    gender: Literal['M', 'F']
    birthday:str
    address:str

# With BaseModel, the constructor is automatically generated. The function model_validate
# checks the type of data.
def load_students(file_name)->list[Student]:
    with open(file_name) as f:
        reader=csv.DictReader(f)
        return[Student.model_validate(row) for row in reader]

def print_students(students:list[Student])->str:
    output=[",".join([f for f in list(Student.model_fields)])]
    output.extend([",".join(str(e) for e in st.model_dump().values()) for st in students])
    return "\n".join(output)

# load from CSV
students=load_students("mock.csv")

# store as csv
print(print_students(students))
