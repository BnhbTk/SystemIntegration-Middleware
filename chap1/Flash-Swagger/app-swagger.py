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