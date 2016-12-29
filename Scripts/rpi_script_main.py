import web
import requests
import RPi.GPIO as GPIO
import time
import json
import sqlite3
from rpi_script_database import Database

Database.createDb()

urls = (
    '/control', 'control',
    '/history', 'history'
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

FCM_API_KEY="AAAAayrfxpk:APA91bGkKVon_NZHLtiyHq4QSacUiNk_TDr9l7DTmsCdEry0jnvddnJwhrUdhygCjwpLaa9cYpCF7CzXrW86EuWlaiE1Th9T5mNvb1FR60qT_i_KoVP9JyV3gdWHB1w7lyX8YOJFiCwaiqfc7G_2L2fCJmvneAnhQw"



def NOTIFICATION(FCM_API_KEY):
    	data={
        	"to" : "eEoKl2EHjpc:APA91bGHQJNL7s-WJ_ZDn5Ud9CVwUbnaz4TxuySRLF_6sZt8w8d0x5xMpedwx-itG2Br3VUji65ossG-b3gR_Ehe14rTA7cYmIdzFmrYwbvoxC-s-medajSAZYHjKi1oPeKa95GBatj8",
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
		#Set LED to on.
		GPIO.output(LED_PIN,GPIO.HIGH)
        	#Send notification.
        	NOTIFICATION(FCM_API_KEY)
        	#Add detection to database.
        	Database.insertDb()
	else:
		print "Motion no longer detected"
		GPIO.output(LED_PIN,GPIO.LOW)

class control:
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

class history:
        def GET(self):
                return Database.getDataDb()

if __name__ == "__main__":
        app = web.application(urls, globals())
        app.run()
