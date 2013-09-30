#!/usr/bin/python

import pifacecommon
import time
import os
import socket

quit = False

UDP_IP = "10.0.0.1"
UDP_PORT = 55555

def pressed_button(event):
    #Enter UDP packet stuff here.
    
    sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    sock.sendto(getBytes("1"), (UDP_IP, UDP_PORT)) #message has to be converted to bytes.

def pressed_quit(event):
    global quit
    quit = True

pifacecommon.core.init()

# GPIOB is the input ports, including the four buttons.
port = pifacecommon.core.GPIOB

listener = pifacecommon.interrupts.PortEventListener(port)

# set up listeners for buttons
listener.register(0, pifacecommon.interrupts.IODIR_ON, pressed_button)
listener.register(3, pifacecommon.interrupts.IODIR_ON, pressed_quit)

# Start listening for button presses. spawns a new thread.
listener.activate()

# hang around until counter hits 20.
while not quit:
    time.sleep(1)

#closes the listener
listener.deactivate()
