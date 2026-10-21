import csv
from dataclasses import asdict, dataclass


# This solution uses dataclasses as a basic data model
# Data classes are intended to hold data with some helpers
# to initialize and storing data

@dataclass
class Item:
    id:int
    name:str
    price:float
    quantity:int
    
    # This method is used by dataclasses to do extra init tasks
    # Here we make some data conversion operations
    def __post_init__(self):
        self.id=int(self.id)
        self.price=float(self.price)
        self.quantity=int(self.quantity)

@dataclass
class Stock:
    items:list[Item]
    def loadCSV(self, file_name):
        with open(file_name, "r") as file:
            reader = csv.DictReader(file)
            self.items=[Item(**row) for row in reader]

    def get_item(self, id):
        filtered_object=[item for item in self.items if item.id==id]
        return filtered_object[0] if filtered_object else None
    
    def get_name(self, id):
        item = self.get_item(id)
        return item.name if item else None
    
    def get_quantity(self, id):
        item = self.get_item(id)
        return item.quantity if item else None
    
    def get_price(self, id):
        item = self.get_item(id)
        return item.price if item else None
    
stock = Stock([])
stock.loadCSV("data.csv")
item=stock.get_item(1)
print(asdict(item))
print(stock.get_name(1))
print(stock.get_quantity(1))
print(stock.get_price(1))
