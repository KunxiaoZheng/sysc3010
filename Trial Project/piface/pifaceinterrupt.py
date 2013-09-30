#!/usr/bin/python

import pifacecommon
import time

quit = 0

def pressed_button(event):
    print('*', end='')
	
	#Enter UDP packet stuff here.
	
	count =+ 1

pifacecommon.core.init()

# GPIOB is the input ports, including the four buttons.
port = pifacecommon.core.GPIOB

listener = pifacecommon.interrupts.PortEventListener(port)

# set up listeners for buttons
listener.register(0, pifacecommon.interrupts.IODIR_ON, pressed_button)

# Start listening for button presses. spawns a new thread.
listener.activate()

# hang around until counter hits 20.
while quit < 20:
    time.sleep(1)

#closes the listener
listener.deactivate()
