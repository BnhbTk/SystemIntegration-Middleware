# Brief Pyro guideline
This is a brief description on how to design a small distributed application using **Python Pyro**. In this guideline, we will implement
a simple service that compute the sum of two integers.

## First step: install dependencies
Pyro can be installed with the command (it should be sufficient to run the example and develop basic application with this library):
```
pip install Pyro5
```

## Second step: define the server object and registering it on the naming registry
Unlike Java, Pyro does not require to develop a common interface for both the client and the server. To define a server object, we should first
import `Pyro5.api`. A server class should be decorated with the decorator `@Pyro5.api.expose`. After that, the business logic is quite the same
for any usual Python program. In our example, the server exposes a method that computes the sum of two integers:
```python
@Pyro5.api.expose
class AdditionComputer:
    def make_addition(self,a:int,b:int)->int:
        return a+b
```
The server will run as a daemon, meaning that it will keep running even if the source code seem to terminate. The server will locate the naming
registry, publishes the name then starts serving:
```python
daemon = Pyro5.server.Daemon()
ns = Pyro5.api.locate_ns()
uri=daemon.register(AdditionComputer) # What object to expose (if the constructor takes arguments, they can be passed here
ns.register("additionner",uri) # Register the object with name
print("I am ready")
daemon.requestLoop() # Server is ready to serve
```

## Third step: define the client
This step is relatively easier in Pyro5 (compared to Java) as it does not require a common interface. We have just to inform Pyro about how
to locate the naming server, get the reference of the remote object and invoke the remote method.
```python
import Pyro5.api

Pyro5.config.NS_HOST = "localhost" # Replace this by the IP address of the machine that hosts the naming registry
Pyro5.config.NS_PORT = 9090 # You can choose any other unused port

obj_add=Pyro5.api.Proxy("PYRONAME:additionner") # Fetch for the object. Note that we should add the prefix "PYRONAME:"
print(obj_add.make_addition(4,5)) # invoke the remote method
```
## Put everything together
The system will be launched following these steps:
1. Run the naming registry `pyro5-ns` for the CLI
1. Run the server
1. Run the client
