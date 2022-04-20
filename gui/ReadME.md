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
   
   ![nav1](https://user-images.githubusercontent.com/83418612/145680129-e21e9ed2-46eb-4e9f-95c8-37e0f71879d4.png)


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

![nav2](https://user-images.githubusercontent.com/83418612/145680122-e4e0876c-41bf-4f0a-8639-10bd27001cb8.png)



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

![unknown](https://user-images.githubusercontent.com/83418612/145679953-c3ef2a83-d8be-4c66-b9f2-00ac1caa3442.png)



--LifeTablePanel--

Contains the following:

- **LifeTablePanel Class**
        
    An extension of the JPanel class, that contains the LifeTablePanel method. 

- **LifeTreePanel **     
        
   The LifeTablePanel method takes the date Calcutlated in the LifeTable Class, and complies the 2 diminsional array into a viewable format. 

- **main **    
        
  The Main method is for testing thr Lifetablepanel class and making sure it will out put what is needed, into a JFrame. 

![unknown](https://user-images.githubusercontent.com/83418612/145679962-85c06972-191c-4891-964b-9431cf1c8482.png)


--MonkeyDetialsPanel--

Contains the following:

- **MonkeyDetailsPanel class**    
        
The Monkey Details Panel class contains the setMonkey and Monkey Detial Panel method, which is an extension of the JPanel class. 
It also contains the following variables labels: Monkey, ID, Brith, Death, gender, mom, and status.

- **setMonkey**    
        
The SetMonkey method takes in the monkey and sets the following variable data. Monkey, ID, Brith, Death, gender, mom, and status

        String id = "Animal ID: " + monkey.getAnimal_id();
        idLabel.setText(id);

        String birth = "Date of Birth: " + monkey.getDate_of_birth();
        birthLabel.setText(birth);

        //System.out.println(monkey.getDate_of_death());
        if(monkey.getDate_of_death() == null) {
            deathLabel.setVisible(false);
            this.repaint();
        }
        else {
            String death = "Date of Death: " + monkey.getDate_of_death();
            deathLabel.setText(death);
            deathLabel.setVisible(true);
        }
        String gender = "Gender: " + monkey.getSex();
        genderLabel.setText(gender);

        String momId = "Mom: " + monkey.getBehavior_mom();
        momLabel.setText(momId);

        String status = "Status: " + monkey.getStatus();
        statusLabel.setText(status);
    }

- **MonkeyDetialsPanel**    
    
   The Monkey Detial panel sets the panel size, and defualt vaules of the labe variables. This method also as different sections for creating the label, and in certain case making the label clickable. For example the momLabel
   
         Dimension windowSize = new Dimension(350, 200);
        this.setPreferredSize(windowSize);
        this.setMaximumSize(windowSize);
        this.setMinimumSize(windowSize);

        String id = "Animal ID";
        String birth = "Date of Birth";
        String death = null;
        String gender = "Gender";
        String momId = "Mom";
        String status = "Status";

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Creates label to display the ID
        idLabel = new JLabel(id);
        idLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(idLabel);

 ![unknown1](https://user-images.githubusercontent.com/83418612/145680028-62c218b1-0487-4487-a6e9-33230d5d934c.png)

--HealingPanel--

 - **The HealingPanel Class**

  The healing panel class contains the healing panel method, which is an extension of the JPanel class. 
  
 - **HealingPanel**

  The healing method is for a simple static image of the Measurement chart. 
  
  ![image](https://user-images.githubusercontent.com/83418612/145452154-d44adfb7-60b9-4b53-a115-6aee7d975686.png)


--ClosurePanel--


 - **The ClosurePanel Class**

  The closure panel class contains the closure method, which is an extension of the JPanel class. 
  
 - **Closure**

  The closure method is for a simple static image of the Suture chart. 
  
  ![Screenshot_624](https://user-images.githubusercontent.com/83418612/145585222-eee6a63c-fa6f-44a1-a2e7-5a6d9234da3b.png)
  
  
 --BoneDatePanel--


 - **The BoneDataPanel Class**

  The Bone data Panel class contains the setMonkey and Bone Data Panel method, which is an extension of the JPanel class. 
It also contains the following variables labels: Monkey, ID, Brith, Death, gender, mom, and status.
  
 - **Set Monkey**

  The SetMonkey method takes in the monkey and sets the following variable data. Monkey, ID, Brith, Death, gender, mom, and status
    
        this.monkey = monkey;
        String id = "Animal ID: " + monkey.getAnimal_id();
        idLabel.setText(id);

        String birth = "Date of Birth: " + monkey.getDate_of_birth();
        birthLabel.setText(birth);

        System.out.println(monkey.getDate_of_death());
        if(monkey.getDate_of_death() == null) {
            deathLabel.setVisible(false);
            this.repaint();
        }
        else {
            String death = "Date of Death: " + monkey.getDate_of_death();
            deathLabel.setText(death);
            deathLabel.setVisible(true);
        }
        String gender = "Gender: " + monkey.getSex();
        genderLabel.setText(gender);

        String momId = "Mom: " + monkey.getBehavior_mom();
        momLabel.setText(momId);

        String status = "Status: " + monkey.getStatus();
        statusLabel.setText(status);
    }
  
  - **BoneDataPanel**
  
 The BoneData panel sets the panel size, and defualt vaules of the labe variables. This method also as different sections for creating the label, and in certain case making    the label clickable. For example the momLabel
 
        JScrollPane scrollPane = new JScrollPane();
        Dimension windowSize = new Dimension(350, 200);
        this.setPreferredSize(windowSize);
        this.setMaximumSize(windowSize);
        this.setMinimumSize(windowSize);

        String id = "Bones";
        String birth = "Bones";
        String death = null;
        String gender = "Bones";
        String momId = "Bones";
        String status = "Bones";

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Creates label to display the ID
        idLabel = new JLabel(id);
        idLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(idLabel);
 
 ![unknown2](https://user-images.githubusercontent.com/83418612/145680037-9844b39f-9ab9-4d01-a3b3-98ecc8db3182.png)

 
 --MonkeyTablePanel--
 
 - **MonkeyTablePanel Class**
 
 The MonkeyTable Panel class contains the MonkeyTable Panel, Get Monkey Array, GetMonkeyNode, and GetMonkey method, which is an extension of the JPanel class. 
 It also contains a JTable variable.
 
 - **MonkeyTablePanel**

The MonkeyTable Panel creates the table with the following columns: Animal ID, Date of Brith, Date of Death, Gender, Mom, and Status. The method also pulls in an object from the Family Tree class. Since the Family tree class is an arraylist, it can be pulled in with a 2 dimensional string list into the getMonkeyArray method. 


    public MonkeyTablePanel(FamilyTree myTree) throws JsonProcessingException, InterruptedException {
        // Creates a 2D array from the FamilyTree class and adds it to a JTable
        LifeTable tableData = new LifeTable();
        String columns[] = {"Animal ID", "Date of Birth", "Date of Death", "Gender", "Mom", "Status"};

        ArrayList<FamilyTreeNode[]> monkeyList = myTree.getMonkeyList();
        String data[][] = new String[myTree.size()][6];

        getMonkeyArray(monkeyList, data);

        monkeyTable = new JTable(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JScrollPane sp = new JScrollPane(monkeyTable);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(sp);

 - **GetMonkeyArray**

 Gets all of the monkeys. It also goes through the founders and adds them to an array list followed by adding the monkeys bellow them. 
 
 
    private void getMonkeyArray(ArrayList<FamilyTreeNode[]> monkeyList, String[][] data) {
        // Gets all the monkeys and adds them to an array
        int[] pos = {0};
        // For loop that goes through the founders and adds them to the array and also adds the monkeys below them
        for(FamilyTreeNode[] familyTreeNodes : monkeyList) {
            data[pos[0]][0] = familyTreeNodes[0].getMonkey().getAnimal_id();
            data[pos[0]][1] = familyTreeNodes[0].getMonkey().getDate_of_birth().toString();
            data[pos[0]][2] = (familyTreeNodes[0].getMonkey().getDate_of_death() != null)
                                    ? familyTreeNodes[0].getMonkey().getDate_of_death().toString() : "null";
            data[pos[0]][3] = familyTreeNodes[0].getMonkey().getSex();
            data[pos[0]][4] = familyTreeNodes[0].getMonkey().getBehavior_mom();
            data[pos[0]][5] = familyTreeNodes[0].getMonkey().getStatus();
            pos[0]++;
            getMonkeyArrayNodes(familyTreeNodes[0], data, pos);
        }
 
 
 - **GetMonkeyNodes **
  
 An recursive get function to get the 1st child of the root, and all of the child's siblings and those below them. 
 
    private void getMonkeyArrayNodes(FamilyTreeNode root, String[][] data, int[] pos) {
        // Recursively gets the first child of the root, as well as the child's siblings and those below them.
        if(root.getChild()[0] != null) {
            data[pos[0]][0] = root.getChild()[0].getMonkey().getAnimal_id();
            data[pos[0]][1] = root.getChild()[0].getMonkey().getDate_of_birth().toString();
            data[pos[0]][2] = (root.getChild()[0].getMonkey().getDate_of_death() != null)
                                ? root.getChild()[0].getMonkey().getDate_of_death().toString() : "null";
            data[pos[0]][3] = root.getChild()[0].getMonkey().getSex();
            data[pos[0]][4] = root.getChild()[0].getMonkey().getBehavior_mom();
            data[pos[0]][5] = root.getChild()[0].getMonkey().getStatus();
            pos[0]++;
            getMonkeyArraySiblings(root.getChild()[0], data, pos);
            getMonkeyArrayNodes(root.getChild()[0], data, pos);
        }
    }
 
 -** GetMonkeySibling **

Recursivelt gets the siblings of the root monkey. 


    private void getMonkeyArraySiblings(FamilyTreeNode root, String[][] data, int[] pos) {
        // Recursively gets the sibling of the root monkey
        if(root.getSibling()[0] != null) {
            data[pos[0]][0] = root.getSibling()[0].getMonkey().getAnimal_id();
            data[pos[0]][1] = root.getSibling()[0].getMonkey().getDate_of_birth().toString();
            data[pos[0]][2] = (root.getSibling()[0].getMonkey().getDate_of_death() != null)
                                ? root.getSibling()[0].getMonkey().getDate_of_death().toString() : "null";
            data[pos[0]][3] = root.getSibling()[0].getMonkey().getSex();
            data[pos[0]][4] = root.getSibling()[0].getMonkey().getBehavior_mom();
            data[pos[0]][5] = root.getSibling()[0].getMonkey().getStatus();
            pos[0]++;
            getMonkeyArrayNodes(root.getSibling()[0], data, pos);
            getMonkeyArraySiblings(root.getSibling()[0], data, pos);
        }
    }


 - **Main**!
 
 The Main is for testing the building of the table.
 
 [unknown](https://user-images.githubusercontent.com/83418612/145680047-6252b459-57f2-4dcd-8e9f-bbb8553603ee.png)


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
