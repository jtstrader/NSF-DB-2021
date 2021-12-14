# NSF DB Build Instructions

## How to run the application

1. Clone the git repository

2. Open the application in IntelliJ
    - If Intellij is not downloaded, go to this link to download it: https://www.jetbrains.com/idea/download/#section=windows 
    
3. Start by running the pom.xml files in the api and gui folders.
    - To do this, right click "pom.xml", click "Maven", and then click "Reload project"
    ![Screenshot (655)](https://user-images.githubusercontent.com/74464395/146044032-c03901f1-a216-4eba-bf04-46e0a7b1597c.png)
    ![Screenshot (656)](https://user-images.githubusercontent.com/74464395/146044237-4de492c5-a717-4a8c-bdcf-1328043ed233.png)

4. Ensure that Java 17 is installed on your computer.
    - To verify this, navigate to File/Project Structure
    - Then, under Project Settings/Project, look for the Project SDK. If your dropdown menu looks like the following, then you need to download Java 17 onto your computer.
    ![Screenshot (654)](https://user-images.githubusercontent.com/74464395/146043381-3b8dc070-d756-4df3-b811-3aefbfc31799.png)
    - To download Java 17, go to the following website and follow the instructions in the download wizard: https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html
    - Navigate back to Project Settings/Project and find the Project SDK section. Now, the dropdown menu should look like the following:
    ![Screenshot (660)](https://user-images.githubusercontent.com/74464395/146047086-95f27fe1-09bf-45b1-841d-89b2d3707a86.png)
    - Select Java 17 as the SDK and click 'Apply' at the bottom of the window to apply the SDK change and then 'OK' to close the window.
    
5. Now, set the configuration for the API.
    - Navigate to the top of the IntelliJ window and click 'Add Configuration'
    ![Screenshot (677)](https://user-images.githubusercontent.com/74464395/146064926-810dbb4f-7801-455f-8db8-18bb8b21c927.png)
    - Click the plus sign in the top left corner and then select 'Application' from the dropdown menu 
    ![Screenshot (676)](https://user-images.githubusercontent.com/74464395/146064527-c3815136-92b6-4e5b-9a4f-534dee355bd4.png)
    - Put 'API' down as the name of the configuration
    - For the module, select '17 version 17.0.1'
    ![Screenshot (671)](https://user-images.githubusercontent.com/74464395/146056563-deb6854c-79b1-43de-a953-a1de608dee38.png)
    - For the -cp <no module' box, click the dropdown arrow and click 'api' from the dropdown menu
    ![Screenshot (672)](https://user-images.githubusercontent.com/74464395/146056822-95937f9c-d464-4dfb-b5e6-c31f1d57bc85.png)
    - For the main class, select 'com.nsfdb.api.ApiApplication'
    ![Screenshot (670)](https://user-images.githubusercontent.com/74464395/146056116-dc7d9953-7e5d-410b-9490-96e795c64fd5.png)
    - Then, enter in the environment variables for the application. To get the application up and running, you need to add variables for:
      - Database name
      - Database username
      - Database password
    - Click 'Apply' and then 'OK'
    - Finally, go ahead and run the API by clicking the green arrow button to the right of the configuration manager.
    ![Screenshot (674)](https://user-images.githubusercontent.com/74464395/146060110-c0723a37-31bd-432d-8125-fde03392c579.png)
    - If you get an error running the API, verify that your environment variables are all spelled correctly.

6. Now, set the GUI configuration.
   - Go back to the configuration manager
   - Click the plus sign in the top left corner and select 'Application' from the dropdown menu
   - Put 'GUI' down as the name of the configuration
   - For the module, select '17 version 17.0.1'
   - For the -cp <no module' box, click the dropdown arrow and click 'gui' from the dropdown menu
   - For the main class, select 'com.nsfdb.gui.main.DBMainWindow'
   - Click 'Apply' and then 'OK'
   - Now run the GUI configuration the same way you ran the API configuration.

Now you're done! If both the API and GUI configurations are able to start successfully, then you'll see the GUI pop up after starting the GUI configuration (if the API is running in the background with no errors).
