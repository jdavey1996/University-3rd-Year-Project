//Setup
import RPi.GPIO as GPIO
import time
GPIO.setmode(GPIO.BCM)
GPIO.setwarnings(False)
GPIO.setup(18,GPIO.OUT)

//Simple loop turning an LED on/off.
num = 0
while num< 1):
	//Sets pin controlling LED to high.
	GPIO.output(18,GPIO.HIGH)
	time.sleep(2)
	//Sets pin controlling LED to low.
	GPIO.output(18,GPIO.LOW)
   	time.sleep(2)