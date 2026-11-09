import csv
from dataclasses import asdict, dataclass


@dataclass
class Item:
    id: int
    name: str
    price: float
    quantity: int
    
    def to_dict(self):
        return asdict(self)
    
        

@dataclass
class Stock:
    items: list[Item]
    
    def __init__(self,filename):
        self.items=[]
        with open(filename, "r") as f:
            csv_reader = csv.reader(f)
            next(csv_reader)  # Skip the header row
            for row in csv_reader:
                id, name, price, quantity = row
                item = Item(int(id), name, float(price), int(quantity))
                self.items.append(item)

    def get_item(self, id):
        res=[item for item in self.items if item.id==id]
        if res:
            return res[0]
        else:
            return None
    
    def get_name(self, id):
        item=self.get_item(id)
        return item.name if item else None
    
    def get_quantity(self, id):
        item=self.get_item(id)
        return item.quantity if item else None
    
    def get_price(self, id, qty):
        item=self.get_item(id)
        return item.price * qty if item else None



