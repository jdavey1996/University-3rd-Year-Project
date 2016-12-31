import sqlite3
import time
import json

class Database:
    @staticmethod
    def createDb():
        db_con = sqlite3.connect('3rdyearproject.db')
        db_cur = db_con.cursor()
        #Create database if doesn't already exist.
        db_cur.execute('''CREATE TABLE IF NOT EXISTS tbl_detections(detection_num INTEGER PRIMARY KEY, detection_date text, detection_time text)''')
        db_con.commit()
        db_con.close()

    @staticmethod
    def insertDb():
        db_con = sqlite3.connect('3rdyearproject.db')
        db_cur = db_con.cursor()
        now_date = (time.strftime("%d/%m/%Y"))
        now_time = (time.strftime("%H:%M:%S"))
        db_cur.execute("INSERT INTO tbl_detections (detection_date, detection_time) VALUES (?, ?)",(now_date, now_time))
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
            detectionsList.append({'id': id, 'date' : date, 'time': time})

        db_con.close()
        return json.dumps(detectionsList)
