GUI
--
developed with intellJ and Java 17

Use Maven to get necessary Libraries

To run the GUI you will need to run the ApiAplication first then run the DBMainWindow. 
The ApiAplicationn must still be running for the application to work. 
The Api also must be connected to the database. 

****
DBMainWindow
--

This is where the main GUI frame is built. 
In the DBMainWindow there is the following:
 - **The DBMainWindow class:**
 
   The DBMainWindow Class contains the necessary calls to creating the class for FamilyTreePanel, LifeTablePanel, 
 ClosurePanel, HealingPanel, DisplayPanel. 
   The Display Panel build the main panel that the followings panels will show up in: FamilyTreePanel, LifeTreePanel, ClosurePanel, and the HealingPanel
   Then the same panels are added to an array list of JPanels which are then inputted into the Navigation Panel.    

 - **The main method:** 
   
      The Main method contains the try and catch function for exceptions. It also contains the functions for building the GUI view. 

****
Panels...
---
--DisplayPanel--

- **DisplayPanel Class**

  A extension on the JPanel With an Array List as a parameter.

- **DisplayPanel Method**

  Contains the JPanel constructors, and the panel dimensions

--NavigationPanel--

- **NavigationPanel Class**

  A extension on the JPanel With an Array List as a parameter.

- **NavigationPanel operation**

   Has an JPanel Array List attribute, this is so a link to the other panels can be created.
   It also contains the dimension window size. It also contains the button creation and the submenu buttons. 
   Gets the images and resize them to if into the panel. 
   

- **SetPanelColor**



- **CreateAnalyticsPopup**



- **SetVisiblePanel**



- **isOptimizedDrawingEnabled**




--FamilyTreePanel--

Contains the following:
- **FamilyTreePanel Class** 
    
        

- **FamilyTreePanel method**

      

- **fillTree operation**

      
   
- **fillsibling operation** 
   
   


--LifeTablePanel--

Contains the following:

- **LifeTablePanel Class**
        


- **LifeTreePanel method**     
        


- **main method**    
        


--MonkeyDetialsPanel--

Contains the following:

- **MonkeyDetailsPanel class**    
        


- **setMonkey operation**    
        


- **MonkeyDetialsPanel operation**    
        


--HealingPanel--




--ClosurePanel--



****

Photos...
--
**NFS DB Logo**


**Founder Monkey** 


**Female Monkey** 


**Male Monkey**



****