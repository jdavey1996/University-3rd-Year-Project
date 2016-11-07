//Variables.
int LED = 13;
int PIR = 2;
int currentSensorState = LOW;  
int sensorVal = 0;  

void setup() {
  //Initialise pin for LED.
  pinMode(LED, OUTPUT);

  //Initialise pin for PIR.
  pinMode(PIR, INPUT);  
  
  //Sets up serial to input data to.
  Serial.begin(9600);
}

void loop() {
    //Run checkMotionDetected method.
    checkMotionDetected();
  }
  
void checkMotionDetected()
{
  sensorVal = digitalRead(PIR);

  //If PIR sensor is high.
  if (sensorVal == HIGH) {    
    //Turn LED on.
    digitalWrite(LED, HIGH);   

    //If the current recorded sensor state is low, new motion has been detected.
    if (currentSensorState == LOW) {
     //Print to log.
     Serial.println("Motion detected!"); 
     
     //Update the currentSensorState to high (motion has been detected).
     currentSensorState = HIGH;      
    }
  }
  //If PIR sensor is NOT high.
  else {
      //Turn LED off.
      digitalWrite(LED, LOW);

      //If the current recorded sensor state is high, the detected motion has stopped.
      if (currentSensorState == HIGH){
        //Print to log.
        Serial.println("Motion stopped!");
        //Update teh currentSensorState to low (motion is no longer detected).
        currentSensorState = LOW;   
    }
  }
}

