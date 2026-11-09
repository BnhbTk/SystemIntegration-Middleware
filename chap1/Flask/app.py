from flask import Flask, abort, render_template, request
from stock import *

app = Flask(__name__)

stock=Stock("data.csv")

@app.route("/")
def view_home():
    return render_template("index.html")

@app.route("/item_name")
def item_name():
    id=request.args.get("id")
    if not id:
        abort(400,"No id have been provided")
    id=int(id.strip())
    item_name=stock.get_name(id)
    if not item_name:
        abort(404,f"Id {id} does not exist")
    return item_name

@app.route("/item_quantity")
def item_qty():
    id=request.args.get("id")
    if not id:
        abort(400,"No id have been provided")
    id=int(id.strip())
    qty=stock.get_quantity(id)
    if not qty:
        abort(404,f"Id {id} does not exist")
    return str(qty)

@app.route('/price-of',methods=["POST"])
def item_price():
    request_data = request.get_json()
    if "id" not in request_data or "qty" not in request_data:
        abort(400,"Incorrect request")
    price=stock.get_price(int(request_data["id"].strip()),int(request_data["qty"].strip()))
    if not price:
        abort(400,"Cannot compute the price")
    return str(price)

if __name__=="__main__":
    app.run(host="0.0.0.0", port=9100)