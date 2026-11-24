from saxonche import PySaxonProcessor

# This is the open source mode of SaxoProcessor. The commercial mode (licence=True)
# requires a licence

def xml2json():
  with PySaxonProcessor(license=False) as proc:
      xsltproc = proc.new_xslt30_processor()
      # Execute and save directly to a file
      xsltproc.transform_to_file(source_file="students.xml", stylesheet_file="convert-xml-json.xslt", output_file="dump.json")

def json2xml():
  with PySaxonProcessor(license=False) as proc:
      xslt_compiler = proc.new_xslt30_processor()
      
      # load the stylesheet file
      executable = xslt_compiler.compile_stylesheet(stylesheet_file="convert-json-xml.xslt")
      #load the data
      with open("students.json") as f:
          data=f.read()
      # prepare the data
      proc_json=proc.make_string_value(data)
      # execute the xslt file
      executable.set_parameter("doc", proc_json)
      result_xml = executable.call_template_returning_string(template_name="main")
      print(result_xml)
