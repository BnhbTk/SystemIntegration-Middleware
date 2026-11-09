import Pyro5.api


@Pyro5.api.expose
class ComputerAddition:
    def __init__(self,v:int):
        self.v=v
    def make_addition(self,a:int,b:int)->int:
        return a+b+self.v

daemon = Pyro5.server.Daemon()
ns = Pyro5.api.locate_ns()
uri=daemon.register(ComputerAddition(4))
ns.register("additionner",uri)
print("I am ready")
daemon.requestLoop()
