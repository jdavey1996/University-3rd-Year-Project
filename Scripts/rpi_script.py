import web
import RPi.GPIO as GPIO
import time
import json

urls = (
    '/', 'index'
)

#Set mode to BOARD (Pins are referred to by their pin number, compared to BCM where they're referred to by GPIO number).
GPIO.setmode(GPIO.BOARD)
GPIO.setwarnings(False)
LED_PIN = 12
PIR_PIN = 16
#Set up LED on pin 12.
GPIO.setup(LED_PIN,GPIO.OUT)
#Set up PIR sensor on pin 16.
GPIO.setup(PIR_PIN,GPIO.IN)

def MOTION(PIR_PIN):
	if GPIO.input(PIR_PIN):
		print "Motion detected"
		GPIO.output(LED_PIN,GPIO.HIGH)
	else:
		print "Motion no longer detected"
		GPIO.output(LED_PIN,GPIO.LOW)

time.sleep(4)

class index:
        def POST(self):
                data = json.loads(web.data())
                command = data["command"]
                #!!!Check if already running.
                if command == 'on':
                        print "turned on"
                        GPIO.add_event_detect(PIR_PIN, GPIO.BOTH, callback = MOTION)
                        return "ON!"
                elif command == 'off':
                        print "turned off"
                        GPIO.remove_event_detect(PIR_PIN)
                        GPIO.output(LED_PIN,GPIO.LOW)
                        return "OFF!"

if __name__ == "__main__":
        app = web.application(urls, globals())
        app.run()
