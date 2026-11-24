import json
import xmltodict

from jinja2 import Environment, FileSystemLoader

def json2xml():
     with open("students.json") as f:
         data=json.load(f)
    env = Environment(loader=FileSystemLoader("./"))
    template = env.get_template("jinja-xml.jinja2")
    print(template.render({"data":data}))

def xml2json():
    # Load the XML file. You can use XML schema to validate the file first
    with open("students.xml") as f:
        data=xmltodict.parse(f.read())
    # Load the Jinja template
    env = Environment(loader=FileSystemLoader("./"))
    template = env.get_template("jinja-json.jinja2")
    
    # Rendering
    print(template.render({"data":data}))
