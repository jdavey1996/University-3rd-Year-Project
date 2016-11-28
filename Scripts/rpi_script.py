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
#Set up LED on pin 12.
GPIO.setup(LED_PIN,GPIO.OUT)
#Set up PIR sensor on pin 16.
GPIO.setup(PIR_PIN,GPIO.IN)

FCM_API_KEY="AAAA6cjZLe0:APA91bGBk5a5tTVzEjwFAOrJBOx5r1lI-qomtwgaGRNliHK81ChMrMnpVxgayJvgqORQnTfodzOyBZogopvikspAXUJIYDG7z1qMnyn8ZSUxvtnfPWut5H21dcFFM8Tr_N4QMx_faAs6UwXZPdh0o-KPuFtnwY2sZQ"

def NOTIFICATION(FCM_API_KEY):
    	data={
        	"to" : "cbHBFwi4rHY:APA91bGNGnbPTYLcf1f6Q8Qkdo-1goC4I3pvemmoOiE_olXMIKBXLvRVi_CgBbEIbspsd77aQCs7fLL5o9ftrTVwMgOfLWxvQPpFpAbjNjUJ9_fdh2oUQjc3FkRoHdqwddy5KCbIjj-u",
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

                if command == 'on':
                        GPIO.add_event_detect(PIR_PIN, GPIO.BOTH, callback = MOTION)
                        return "ON!"
                elif command == 'off':
                        GPIO.remove_event_detect(PIR_PIN)
                        GPIO.output(LED_PIN,GPIO.LOW)
                        return "OFF!"

if __name__ == "__main__":
        app = web.application(urls, globals())
        app.run()
