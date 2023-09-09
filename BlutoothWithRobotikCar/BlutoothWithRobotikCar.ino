int data ;  

#define  motorR1 7
#define  motorR2 6
#define  motorRE 9
#define  motorL1 4
#define  motorL2 5
#define  motorLE 3


void setup() {
  // put your setup code here, to run once:
Serial1.begin(9600);
 pinMode(motorR1,OUTPUT);
  pinMode(motorR2,OUTPUT);
  pinMode(motorRE,OUTPUT);
  pinMode(motorL1,OUTPUT);
  pinMode(motorL2,OUTPUT);
  pinMode(motorLE,OUTPUT);
}

void loop() {
  // put your main code here, to run repeatedly:
if(Serial1.available()>0)
{
  data=Serial1.read();

  }
if(data=='1')
{
  forward();
}
if(data=='2')
{
  back();
}
if (data=='3')
{
  stop();
}
if (data=='5')
{
  left();
}
if (data=='4')
{
  right();
}



  

}


void right(){
  digitalWrite(motorR1,LOW);
  digitalWrite(motorR2,HIGH);
  analogWrite(motorRE,250);
  digitalWrite(motorL1,HIGH);
  digitalWrite(motorL2,LOW);
  analogWrite(motorLE,250);
}



void left(){
  digitalWrite(motorR1,HIGH);
  digitalWrite(motorR2,LOW);
  analogWrite(motorRE,250);
  digitalWrite(motorL1,LOW);
  digitalWrite(motorL2,HIGH);
  analogWrite(motorLE,250);
}


void forward(){
  digitalWrite(motorR1,HIGH);
  digitalWrite(motorR2,LOW);
  analogWrite(motorRE,250);
  digitalWrite(motorL1,HIGH);
  digitalWrite(motorL2,LOW);
  analogWrite(motorLE,250);
}

void back(){
  digitalWrite(motorR1,LOW);
  digitalWrite(motorR2,HIGH);
  analogWrite(motorRE,250);
  digitalWrite(motorL1,LOW);
  digitalWrite(motorL2,HIGH);
  analogWrite(motorLE,250);
}
void stop(){
  digitalWrite(motorR1,LOW);
  digitalWrite(motorR2,LOW);
  analogWrite(motorRE,250);
  digitalWrite(motorL1,LOW);
  digitalWrite(motorL2,LOW);
  analogWrite(motorLE,250);
}
