import sqlite3
import time
import json
import picamera
import base64

class Database:
    @staticmethod
    def createDb():
        db_con = sqlite3.connect('3rdyearproject.db')
        db_cur = db_con.cursor()
        #Create database if doesn't already exist.
        db_cur.execute('''CREATE TABLE IF NOT EXISTS tbl_detections(detection_num INTEGER PRIMARY KEY, detection_date text, detection_time text, detection_img text)''')
        db_con.commit()
        db_con.close()

    @staticmethod
    def insertDb():
	img = Database.captureImg()
        db_con = sqlite3.connect('3rdyearproject.db')
        db_cur = db_con.cursor()
        now_date = (time.strftime("%d/%m/%Y"))
        now_time = (time.strftime("%H:%M:%S"))
        db_cur.execute("INSERT INTO tbl_detections (detection_date, detection_time, detection_img) VALUES (?, ?, ?)",(now_date, now_time, img))
        db_con.commit()
        db_con.close()

    @staticmethod
    def getDataDb():
        db_con = sqlite3.connect('3rdyearproject.db')
        db_cur = db_con.cursor()
        db_cur.execute('SELECT * FROM tbl_detections ORDER BY detection_num DESC')

        detectionsList = []
        for row in db_cur:
            id = row[0]
            date = row[1]
            time = row[2]
	    img = row[3]
            detectionsList.append({'id': id, 'date' : date, 'time': time, 'img': img})

        db_con.close()
        return json.dumps(detectionsList)

    @staticmethod
    def captureImg():
        camera = picamera.PiCamera()
        camera.capture('image.jpg')
        camera.close()
	
	with open("image.jpg", "rb") as imageFile:
		str = base64.b64encode(imageFile.read())
	return str
