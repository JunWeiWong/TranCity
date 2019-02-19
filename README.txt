PHASE2

For the phase2 of this project, the java file MainDemo.java in the package Controllers (Controllers.MainDemo) is the
file that needs to be executed to be able to perform the GUI Interface.

log.txt has been created to save any changes on transit user, transit cards and admin users.

========================================

Introduction PHASE1

The main interface(Controllers.InterfacePhase1) reads several files (e.g. Line1.txt, Route30.txt) to create the Model.TransitMap.
The module will also read events.txt, to calculate revenue on a daily basis. Fares for each passenger will also be
calculated automatically based on their entry and exit station or stop.

========================================
Formatting

Each file to be read will have similar formatting with subtle differences.

--------------------
-events.txt 
Each line is to contain only one action. Parameters are separated by comas and with no spaces, and the first word
indicates the keyword of the action to be taken.

The following keywords are contained in this file:

To create a new day we use the keyword NewDay following by the date.
Example: NewDay,01/01/2018)


To create a new user we use the keyword New follow by the Name of the User and the email.
Example: New,Robert Smith,robert.smith@gmail.com


To create a new transit card with $19 we use the keyword AddCard follow by the email address, the number of the card.
Example: AddCard,robert.smith@gmail.com,1201

If the card is to be added with extra credit a 4th parameter will be expected.
Example: AddCard,robert.smith@gmail.com,1201,20


To change the name of the user we use the keyword ChangeName, follow by the email address and the new name.
Example: ChangeName,jun.wei@gmail.com,Gerson Lopez


To add credit on a existed card we use the keyword AddCredit follow by the card number and the amount to be added
Example: AddCredit,1210,50


To vew the balance on a card we use the keword ViewBalance follow by the card number.
Example: ViewBalance,1206


To report a card stolen we use the keyword ReportStolenCard follow by the card number.
Example: ReportStolenCard,1206


To suspend a card we use the keyword Suspend follow by the card number.
Example: Suspend,1206


To vew the most recent trips of a transit user we use the keyword ViewTrips following by the email of the user.
Example: ViewTrips,sophia.rogers@gmail.com


All cards are activated when they are added but if a card is suspended and needs to be activated again we use the
keyword ActivateCard follow by the card number.
Example: ActivateCard,1201


If the cardholder has more than one card, he/she can transfer total or partial balance from one card to another, we
use the keyword TransferBalance follow by email card number from where the balance is taken and card number where
the balance is deposited.
Example: TransferBalance,sophia.rogers@gmail.com,1206,1207 (this means that the balance from 1206 is been transferred to 1207)

If the cardholder wish to transfer partial amount the format is:
TransferBalance,sophia.rogers@gmail.com,1206,1207,10   (this means that $10 will be transferred from 1206 to 1207)

NOTE: the cardholder most have the two cards in his/her list to make a transfer

To remove a card from the list of the cardholder we use the keyword RemoveCard follow by the email and the card to be
removed.
Example: RemoveCard,sophia.rogers@gmail.com,1206


For the TapIn and TapOut system we use the following formats:

SUBWAY:
TAPIN  (EnterSubway,card number,station,line name,time) as follows:   EnterSubway,1207,Kipling,Line2,09:20:00
TAPOUT (ExitSubway,card number,station,line name,time) as follows:    ExitSubway,1207,Union,Line1,09:10:00

BUS:
TAPIN  (EnterBus,card number,stop number,route name,time) as follows: EnterBus,1207,940,Route6Bay,11:00:00
TAPOUT (ExitBus,card number,stop number,route name,time) as follows:  ExitBus,1207,267,Route6Bay,11:30:00


To get the average cost per month of a cardholder we use the keyword AverageCostPerMonth, email and time.
Example: AverageCostPerMonth,robert.smith@gmail.com,10:30:45


For the Admin part we use the following keywords to get a report CompareOverAllRevenueToTheOperatingCost or AdminInfo.

We have declared a final variable called OPERARTINGCOSTPERDAY in the Controllers.Administrator class, this to enter the operating cost
per day that the client would like to assign. for now we assigned $20,000.00 as an approximation.

In the case that the card holder forgets to tapIn or TapOut in the system, he/she will not be charged for that trip,
this entry will not be recorded and will not be shown in his/her most recent trips.

For phase 1, we are not considering the case where the cardholder enters into one subway line and exists from another,
because the subway lines created don't intersect.

--------------------
-line.txt
Each line is to contain details for only one station. Comas are used to separate different parameters. Parameters
are arranged in a sequence that latitude follows longitude, and station name after.

Example:
43.750053813,-79.462342744,Vaughan Metropolitan Centre

If an extra parameter appears, that indicates that the station intersects with a bus stop

Example:
43.645722666,-79.380462258,Union,15037 this indicates that the station Union intersects with the stop 15037

--------------------
-route.txt
Each line is to contain details for only one station. Comas are used to separate different parameters.
Parameters are arranged in a sequence that latitude follows longitude, stop number follows longitude,
and stop name comes after stop number.

Example:
43.67580645369672,-79.40258145332336,940,Davenport Rd at Dupont St

If an extra parameter appears, that indicates that the station/stop intersects with other station/stop

Example:
43.646149218679874,-79.37932133674622,15037,Bay St at Front St West South Side,Union
this indicates that the bus stop 15037 intersects with the subway station Union


========================================
Requirements

This module requires several java modules to be complied successfully. Those include java.util and java.io. 

========================================
Contributors & Maintainers

Gerson L. Bazan (Gerson) - gerson.lopezbazan@mail.utoronto.ca
Yuanrui He (Jaron) - yuanrui.he@mail.utoronto.ca
Jun W. Wong (Jun) - junwei.wong@mail.utoronto.ca
Guanhao Zhao (Zhao) - guanhao.zhao@mail.utoronto.ca

========================================
FAQ

Should you have any questions regarding usage of this system please contact any of the contributors above.