import web
import requests
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
running = 0
#Set up LED on pin 12.
GPIO.setup(LED_PIN,GPIO.OUT)
#Set up PIR sensor on pin 16.
GPIO.setup(PIR_PIN,GPIO.IN)

FCM_API_KEY="____________________"

def NOTIFICATION(FCM_API_KEY):
    	data={
        	"to" : "_____device id____",
        	"data" : {
            	"notification" : "motion detected"
            	}
    	}
    	headers = { "Authorization" : "key="+FCM_API_KEY,
                	"Content-type" : "application/json"
                	}
    	r = requests.post('https://fcm.googleapis.com/fcm/send', data = json.dumps(data), headers=headers )
    	print(r.text)

def MOTION(PIR_PIN):
	if GPIO.input(PIR_PIN):
		print "Motion detected"
		GPIO.output(LED_PIN,GPIO.HIGH)
        	NOTIFICATION(FCM_API_KEY)
	else:
		print "Motion no longer detected"
		GPIO.output(LED_PIN,GPIO.LOW)

class index:
        def POST(self):
                data = json.loads(web.data())
                command = data["command"]
                global running

                if command == 'on':
                	if running == 0:
                        	time.sleep(5)
                                GPIO.add_event_detect(PIR_PIN, GPIO.BOTH, callback = MOTION)
                            	running = 1
                            	return "001"
               		elif running == 1:
                            	return "003"
                elif command == 'off':
                        if running == 1:
                            	GPIO.remove_event_detect(PIR_PIN)
                            	GPIO.output(LED_PIN,GPIO.LOW)
                            	running = 0
                            	return "000"
                        elif running == 0:
                            	return "002"

if __name__ == "__main__":
        app = web.application(urls, globals())
        app.run()

