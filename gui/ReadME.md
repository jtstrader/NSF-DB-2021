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
               
        JPanel display = new DisplayPanel();
        display.add(treePanel);
        display.add(lifePanel);
        display.add(closurePanel);
        display.add(healingPanel);
        display.add(monkeyPanel);
        this.add(display, BorderLayout.CENTER);
          
   Then the same panels are added to an array list of JPanels which are then inputted into the Navigation Panel.  
   
        ArrayList<JPanel> panels = new ArrayList<>();
        panels.add(treePanel);
        panels.add(lifePanel);
        panels.add(closurePanel);
        panels.add(healingPanel);
        panels.add(monkeyPanel);        

 - **The main method:** 
   
      The Main method contains the try and catch function for exceptions.
      
       try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException e) { // Exception
                    e.printStackTrace();
                } catch (InstantiationException e) { // Exception
                    e.printStackTrace();
                } catch (IllegalAccessException e) { // Exception
                    e.printStackTrace();
                } catch (UnsupportedLookAndFeelException e) { // Exception
                    e.printStackTrace();
                }

      
      It also contains the functions for building the GUI view. 
      
               JFrame window = null;
                try {
                    window = new DBMainWindow();

                    window.setTitle("Monkey DB");

                    //MonkeyDetailsPanel detailsPanel = new MonkeyDetailsPanel();
                    //window.add(detailsPanel, BorderLayout.EAST);

                    window.pack();
                    window.setVisible(true);
                    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                } catch (ExecutionException e) { // Exception
                    e.printStackTrace();
                } catch (JsonProcessingException e) { // Exception
                    e.printStackTrace();
                } catch (InterruptedException e) { // Exception
                    e.printStackTrace();
                } catch (TimeoutException e) { // Exception
                    e.printStackTrace();
                } catch (IOException e) { // Exception
                    e.printStackTrace();
                }
      

****
Panels...
---
--DisplayPanel--

- **DisplayPanel Class**

  A extension on the JPanel With an Array List as a parameter.

- **DisplayPanel**

  Contains the JPanel constructors, and the panel dimensions



--NavigationPanel--

- **NavigationPanel Class**

  A extension on the JPanel With an Array List as a parameter.

- **NavigationPanel**

   Has an JPanel Array List attribute, this is so a link to the other panels can be created.
   It also contains the dimension window size. It also contains the button creation and the submenu buttons. 
   Gets the images and resize them to if into the panel. 
   

- **SetPanelColor**

 Sets the background color of the navigation panel.

- **CreateAnalyticsPopup**

 Builds the popup panel for the analytics options. These option allows for access of the Life table, healing table and the Suturare Chart. 
 This method creates the different different popup buttons need for the analytics option. The healing button can be seen below. 
  
        JButton healingBut = new JButton("Healing Graph");
        healingBut.setBorderPainted(false);
        healingBut.setFocusPainted(false);
        healingBut.setFont(new Font("Arial", Font.PLAIN, 12));
        healingBut.setBackground(new Color(253,238,229));
        healingBut.setForeground(new Color(214, 79, 1));
        healingBut.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ButtonModel model = (ButtonModel) healingBut.getModel();
                // Enables and Shows the Healing Graph Panel(HealingPanel)
                // Disable and Hides all the other display panels
                if(model.isPressed()) {
                    setVisiblePanel(panelList.get(3));
                }
                else if(model.isRollover()) {
                    healingBut.setBackground(Color.WHITE);
                    healingBut.setForeground(Color.BLACK);
                }
                else {
                    healingBut.setBackground(new Color(253,238,229));
                    healingBut.setForeground(new Color(214, 79, 1));
                }
            }
        });
        popupNav.add(healingBut);


--FamilyTreePanel--

Contains the following:
- **FamilyTreePanel Class** 
    
     An extension of the JPanel class, that contains the FamilyTreePanel Methods. 

- **FamilyTreePanel**

     This methods builds the basic frame work for the panel. It builds the sice of the panel. It also instaiate the Family tree class from the analytics. 
     It also calls the method to create the tree and print the tree. Then it plugs in the Family tree built into a Jtree. 
     
                             FamilyTree myTree = new FamilyTree();
                             myTree.create();
                             myTree.printTree();
                             tree = new JTree(myTree.treeify());
     There is also a section so on start up all the nodes are showing. 
     
                          // Expands the tree to show all nodes
                           for(int i = 0; i < tree.getRowCount(); i++) {
                                         tree.expandRow(i);
                              }            
     In this method there is also a part to that the closade areas are remembered while using the application
     
                      tree.getSelectionModel().setSelectionMode
                (TreeSelectionModel.SINGLE_TREE_SELECTION);

                tree.addTreeSelectionListener(new TreeSelectionListener() {
                public void valueChanged(TreeSelectionEvent e) {
                FamilyTreeNode node = (FamilyTreeNode)
                        tree.getLastSelectedPathComponent();
     In the method it also allows for a monkey to be selected and show that monkeys detials in the MonkeyDetails Panel. 
     
                if (node == null) return;
                detailsPanel.setMonkey(node.getMonkey()); 
     
- **fillTree**

     The fillTree method is used to fill a test/place holder tree until the Anayltics had completed the FamilyTree Class. 
   
- **fillsibling**
   
    The fillsibliing method is used to fill a test/place holder tree until the Anayltics had completed the FamilyTree Class. 
   
- **ViewTest**

    The ViewTest method is used to fill a test/place holder tree until the Anayltics had completed the FamilyTree Class. 


--LifeTablePanel--

Contains the following:

- **LifeTablePanel Class**
        
    An extension of the JPanel class, that contains the LifeTablePanel method. 

- **LifeTreePanel **     
        
   The LifeTablePanel method takes the date Calcutlated in the LifeTable Class, and complies the 2 diminsional array into a viewable format. 

- **main **    
        
  The Main method is for testing thr Lifetablepanel class and making sure it will out put what is needed, into a JFrame. 



--MonkeyDetialsPanel--

Contains the following:

- **MonkeyDetailsPanel class**    
        


- **setMonkey**    
        


- **MonkeyDetialsPanel**    
        


--HealingPanel--

 - **The HealingPanel Class**

  The healing panel class contains the closure method, which is an extension of the JPanel class. 
  
 - **HealingPanel**

  The healing method is for a simple static image of the Measurement chart. 
  ![image](https://user-images.githubusercontent.com/83418612/145452154-d44adfb7-60b9-4b53-a115-6aee7d975686.png)


--ClosurePanel--


 - **The ClosurePanel Class**

  The closure panel class contains the closure method, which is an extension of the JPanel class. 
  
 -** Closure**

  The closure method is for a simple static image of the Suture chart. 
  ![Screenshot_624](https://user-images.githubusercontent.com/83418612/145585222-eee6a63c-fa6f-44a1-a2e7-5a6d9234da3b.png)
  
  
 --BoneDatePanel--


 - **The BoneDatePanel Class**

  The 
  
 - **Set Monkey**

  The 
  
  - **BoneDatePanel**
  
 the
 
 
 
 --MonkeyTablePanel--
 
 -** MonkeyTablePanel Class**
 
 
 
 - **MonkeyTablePanel**



 - **GetMonkeyArray**
 
 
 
 - **GetMonkeyNodes **
 
 
 
 -** GetMonkeySibling **
 
 
 
 - **Main**

****


Resources..
--
**NFS DB Logo**

![logo](https://user-images.githubusercontent.com/83418612/145592076-faeeeb80-d94d-480e-9406-3305b3335984.png)

**Founder Monkey** 

![MotherMonkey](https://user-images.githubusercontent.com/83418612/145592107-381092c9-172c-4c03-b996-a351a94d010b.png)

**Female Monkey** 

![FemaleMonkey](https://user-images.githubusercontent.com/83418612/145592116-fc06d8a4-ccf6-4187-9271-80b36e190458.png)

**Male Monkey**

![MaleMonkey](https://user-images.githubusercontent.com/83418612/145592128-f53ff19d-8eb0-4421-b0e2-188758f3270a.png)

****
