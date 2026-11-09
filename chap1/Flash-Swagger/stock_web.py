from stock import *
from flask import abort, request

stock=Stock("data.csv")

def item_name(id):
    item_name=stock.get_name(id)
    if not item_name:
        abort(404,f"Id {id} does not exist")
    return item_name


def item_price(body):
    price=stock.get_price(body["id"],body["qty"])
    if not price:
        abort(400,"Cannot compute the price")
    return str(price)