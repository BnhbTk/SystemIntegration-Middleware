import Pyro5.api

Pyro5.config.NS_HOST = "localhost"
Pyro5.config.NS_PORT = 9090


obj_add=Pyro5.api.Proxy("PYRONAME:additionner")

print(obj_add.make_addition(4,5))
