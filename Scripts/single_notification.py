import requests
import json
#http://docs.python-requests.org/en/master/user/quickstart/
MY_API_KEY="AAAA6cjZLe0:APA91bGBk5a5tTVzEjwFAOrJBOx5r1lI-qomtwgaGRNliHK81ChMrMnpVxgayJvgqORQnTfodzOyBZogopvikspAXUJIYDG7z1qMnyn8ZSUxvtnfPWut5H21dcFFM8Tr_N4QMx_faAs6UwXZPdh0o-KPuFtnwY2sZQ"

datas={
    "to" : "cbHBFwi4rHY:APA91bGNGnbPTYLcf1f6Q8Qkdo-1goC4I3pvemmoOiE_olXMIKBXLvRVi_CgBbEIbspsd77aQCs7fLL5o9ftrTVwMgOfLWxvQPpFpAbjNjUJ9_fdh2oUQjc3FkRoHdqwddy5KCbIjj-u",
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
