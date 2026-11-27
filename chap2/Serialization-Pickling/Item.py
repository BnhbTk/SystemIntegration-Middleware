import pickle

class Item:
    def __init__(self,id:int,name:str,price:float,quantity:int):
        self.id=id
        self.name=name
        self.price=price
        self.quantity=quantity
    
    def __str__(self):
        return f"Item({self.id},'{self.name}',{self.price},{self.quantity})"

item:Item=Item(5,"USB mouse",100,50)
print(item)

# The in which data should be stored should open in binary mode
# and this should be the case when we try to load it back
with open("data.pkl", "wb") as file:
    pickle.dump(item, file)

# Code to unpickle the object
with open("item.pkl","rb") as file:
    item=pickle.load(file)
    print(item)
