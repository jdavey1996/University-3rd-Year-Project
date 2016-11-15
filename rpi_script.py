#Import libraries to enable waiting and GPIO access.
import RPi.GPIO as GPIO
import time
#Set mode to BOARD (Pins are referred to by their pin number, compared to BCM where they're referred to by GPIO number).
GPIO.setmode(GPIO.BOARD)
GPIO.setwarnings(False)
#Set up PIR sensor on pin 16.
GPIO.setup(16,GPIO.IN)
#Set up LED on pin 12.
GPIO.setup(12,GPIO.OUT)

#Initially set LED to off before starting to detect motion.
GPIO.output(12,GPIO.LOW)

#Sleep for 4 second.
time.sleep(4)

#Continuous loop to check if motion is detected.
num = 0
while num< 1:
	#Reads PIR input into variable.
	i=GPIO.input(16)
	#Code to execute when motion is not detected.
	if i==0:
		print "NO MOTION DETECTED"
		#Sets pin controlling LED to low.
		GPIO.output(12,GPIO.LOW)
	#Code to execute when motion is detected.
	elif i==1:
		print "MOTION DETECTED"
		#Sets pin controlling LED to high.
		GPIO.output(12,GPIO.HIGH)

	#Sleep for 2 seconds.
	time.sleep(2)
