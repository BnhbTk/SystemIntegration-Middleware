import json
import xmltodict

from jinja2 import Environment, FileSystemLoader

# Load the XML file. You can use XML schema to validate the file first
with open("students.xml") as f:
    data=xmltodict.parse(f.read())
# Load the Jinja template
env = Environment(loader=FileSystemLoader("./"))
template = env.get_template("jinja-json.jinja2")

# Rendering
print(template.render({"data":data}))
