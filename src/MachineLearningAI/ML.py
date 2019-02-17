from py4j.java_gateway import JavaGateway

def main():
    gateway = JavaGateway()
    list = gateway.PythonInterface.getRecieveData() # Java list

    #move = tensorflow function

    templist = gateway.PythonInterface.freshList()

    for(e in move):
        templist.add(e)
    Outlist = gateway.PythonInterface.setRecieveData(templist)
    gateway.shutdown()


