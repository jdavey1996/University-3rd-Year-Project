import web
import RPi.GPIO as GPIO

urls = (
    '/', 'index'
)

#Set mode to BOARD (Pins are referred to by their pin number, compared to BCM where they're referred to by GPIO number).
GPIO.setmode(GPIO.BOARD)
GPIO.setwarnings(False)
LED_PIN = 12
#Set up LED on pin 12.
GPIO.setup(LED_PIN,GPIO.OUT)


class index:
	def GET(self):
	    user_data = web.input()
	    if user_data.userin == 'on':
	        GPIO.output(LED_PIN,GPIO.HIGH)
            return "ON!"
        elif user_data.userin == 'off':
            GPIO.output(LED_PIN,GPIO.LOW)
		    return "OFF!"

if __name__ == "__main__":
    app = web.application(urls, globals())
    app.run()
