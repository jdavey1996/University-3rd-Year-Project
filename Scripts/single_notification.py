import requests
import json
#http://docs.python-requests.org/en/master/user/quickstart/
MY_API_KEY="_____________"

datas={
    "to" : "______device id____",
    "data" : {
        "notif1" : "test",
        "notif2" : "test2"
    }
}
dataAsJSON = json.dumps(datas)



headers = { "Authorization" : "key="+MY_API_KEY,
            "Content-type" : "application/json"
            }

r = requests.post('https://fcm.googleapis.com/fcm/send', data = dataAsJSON, headers=headers )

print(r.text)
