from dataclasses import dataclass

import student_mangement_pb2 as smp

@dataclass
class PyStudent:
    id:int
    lname:str
    fname:str
    gender:str
    birthday:str
    address:str

# Create an object
student=smp.Student()
student.id=1
student.lname="Bendada"
student.fname="Foued"
student.gender="M"
student.birthday="1/1/2000"
student.address="Annaba"

# Marshall the object into protobuf
binary_data = student.SerializeToString()

print(f"Encoded Binary (bytes): {binary_data}")
print(f"Binary Size: {len(binary_data)} bytes\n")

# Create an empty instance
decoded_student = smp.Student()
# Fill it out thanks to unmarshalling
decoded_student.ParseFromString(binary_data)
print(decoded_student)
