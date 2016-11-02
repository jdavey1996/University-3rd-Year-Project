

void setup() {
  //Initialise pin for LED.
  pinMode(LED_BUILTIN, OUTPUT);

  //Sets up serial to input data to.
  Serial.begin(9600);
  Serial.println("Input 1 to turn LED on or 0 to turn LED off");
}

void loop() {
    //Run checkMotionDetected method.
    checkMotionDetected();
  }
  

void checkMotionDetected()
{
  //If serial input is detected.
  if(Serial.available())
  {
    //Read in character.
    int input = Serial.read(); 

    //Set LED on/off based on read in value.
    if(input == '1')
    {
      Serial.println("LED On");
      digitalWrite(LED_BUILTIN, HIGH);
    }
    else if (input == '0')
    {
      Serial.println("LED Off");
      digitalWrite(LED_BUILTIN, LOW);
    }
  }
  
}

