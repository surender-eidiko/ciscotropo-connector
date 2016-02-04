## CiscoTropo Anypoint™ Connector


[Cisco Tropo Connector to connect to Cisco Tropo]

## Author
: 
Cisco Inc.

## Supported Mule runtime versions
:
Mule 3.7.0

## [Cisco Tropo] supported versions
:
Cisco Tropo Version 1.0 and above.

## Service or application supported modules
Example:Cisco Tropo

## Running the Demo

The Demo is used to create a Session with TROPO. The tropo connector create session is used to send an SMS to the user.
The payload is used to identity the user to send the SMS. 
Payload sample: #[['customerName':'ratan','numberToDial':'9258640632','msg':'Hai From Tropo Connector']]"
numberToDial identifies the phone number to send the SMS and 'msg' contains the txt to include in the SMS. 

To run the demo open a browser and type http://localhost:8081/. It will trigger the flow.

