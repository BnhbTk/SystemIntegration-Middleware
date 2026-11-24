import json
import xmlschema
import jmespath
from jsonschema import validate, ValidationError

# Load the Schema document
xsd_file_name="student-schema.xsd"
xsd = xmlschema.XMLSchema(xsd_file_name)

# Validate the document against the schema
xml_file_name="students.xml"
if not xsd.is_valid(xml_file_name):
    raise ValueError(f"XML file '{xml_file_name}' failed XSD validation.")
print("Document is valid")

# This is the JSON schema expressed in Jmespath
json_schema = jmespath.compile("""
{
    students: student[].{
        lname: "lname",
        fname: "fname",
        gender: "@gender",
        birthday:"birthday",
        address:"address"
    }
}""")

# load the document as raw dictionary
raw_dict = xsd.to_dict(xml_file_name, strip_namespaces=True)
# generate the json output according to the schema
result=json_schema.search(raw_dict)
print(json.dumps(raw_dict,indent=4,ensure_ascii=False))
