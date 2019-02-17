from py4j.java_gateway import JavaGateway
import tensorflow as tf
from tensorflow.keras import layers
import numpy as np
import Math


def main():
    move = []
    gateway = JavaGateway()
    list = gateway.PythonInterface.getRecieveData() # Java list
    lst = to_lst(list)



    topindex = tensor_flow(lst)
    # i, j, s
    move.add(topindex % (len(lst)/5))
    move.add(topindex // (len(lst)/5))
    move.add(topindex % 4)

    templist = gateway.PythonInterface.freshList()

    for(e in move):
        temp_list.add(e)

    out_list = gateway.PythonInterface.setRecieveData(temp_list)
    gateway.shutdown()


def tensor_flow(list):

    data = np.asarray(list)

    model = tf.keras.Sequential()
    model.add(layers.Dense(data.size(), activation='relu')
    model.add(layers.Dense(data.size()/2, activation='relu')
    model.add(layers.Dense(data.size()*(4/5)), activation='softmax')


    model.compile(optimizer=tf.train.AdamOptimizer(0.002), \
        loss=tf.keras.losses.categorical_crossentropy, \
        metrics=[tf.keras.metrics.categorical_accuracy]

    log = model.callbacks.CSVLogger('my_file.csv', separator=",", append=False)

    model.fit(data, epoch=10, batch_size=data.size(), callback=["log"]

    batch_print_callback = LamdaCallback(on_epoch_end=lamda epoch, logs: print(epoch))

    model.evaluate(data, batch_size=30)

    my_data = genfromtxt('my_file.csv',delimits=',')

    topval=0

    for i in range(len(my_data)):
        val = my_data[i]
        if val>topval:
            val = topval
            topindex = i

    return (topindex//5)*Math.sqrt(len(my_data))


def to_lst(list):
    l = []
    for(e in list):
        l.append(e)
    return l