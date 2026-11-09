# This is main program of the web server, it invokes the swagger api
# Swagger.yml contains all the necessary service declaration while binding
# each endpoint to a Python function (in the business logic file stock-web.py
# Note that you can access the Swagger UI by adding /ui to the url field declared
# in the yaml file

import connexion
from flask import render_template
from flask_cors import CORS


app = connexion.App(__name__, specification_dir="./")
app.add_api("swagger.yml")
CORS(app.app)


@app.route("/")
def view_home():
    return render_template('index-swg.html')


if __name__ == '__main__':
    app.run(host="0.0.0.0", port=9100)
