package com.nsfdb.gui.dashboard;

import com.nsfdb.api.models.Monkey;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class MonkeyDetailsPanel extends JPanel {
    Monkey monkey;
    JLabel idLabel;
    JLabel idInfo;
    JLabel birthLabel;
    JLabel birthInfo;
    JLabel deathLabel;
    JLabel deathInfo;
    JLabel genderLabel;
    JLabel genderInfo;
    JLabel momLabel;
    JLabel momInfo;
    JLabel statusLabel;
    JLabel statusInfo;

    // This JPanel will be created inside the FamilyTreePanel
    public void setMonkey(Monkey monkey) {
        this.monkey = monkey;
        String id = " Animal ID: ";
        String id2 = monkey.getAnimal_id();
        idLabel.setText(id);
        idInfo.setText(id2);
        idLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        idInfo.setFont(new Font("Arial", Font.PLAIN, 12));
        idLabel.setBackground(new Color(220, 220, 220));
        idLabel.setOpaque(true);
        idInfo.setBackground(new Color(220, 220, 220));
        idInfo.setOpaque(true);

        String birth = " Date of Birth: ";
        String birth2 = monkey.getDate_of_birth() + " ";
        birthLabel.setText(birth);
        birthInfo.setText(birth2);
        birthLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        birthInfo.setFont(new Font("Arial", Font.PLAIN, 12));

        //System.out.println(monkey.getDate_of_death());
        if(monkey.getDate_of_death() == null) {
            deathLabel.setVisible(false);
            deathInfo.setVisible(false);
            this.repaint();
        }
        else {
            String death = " Date of Death: ";
            String death2 = monkey.getDate_of_death() + " ";
            deathLabel.setText(death);
            deathInfo.setText(death2);
            deathLabel.setFont(new Font("Arial", Font.PLAIN, 12));
            deathInfo.setFont(new Font("Arial", Font.PLAIN, 12));
            deathLabel.setVisible(true);
            deathInfo.setVisible(true);
            deathLabel.setBackground(new Color(220, 220, 220));
            deathLabel.setOpaque(true);
            deathInfo.setBackground(new Color(220, 220, 220));
            deathInfo.setOpaque(true);
        }
        String gender = " Gender: ";
        String gender2 = monkey.getSex();
        genderLabel.setText(gender);
        genderInfo.setText(gender2);
        genderLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        genderInfo.setFont(new Font("Arial", Font.PLAIN, 12));

        String momId = " Mom: ";
        String momId2 = monkey.getBehavior_mom();
        momLabel.setText(momId);
        momInfo.setText(momId2);
        momLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        momInfo.setFont(new Font("Arial", Font.PLAIN, 12));
        momLabel.setBackground(new Color(220, 220, 220));
        momLabel.setOpaque(true);
        momInfo.setBackground(new Color(220, 220, 220));
        momInfo.setOpaque(true);

        String status = " Status: ";
        String status2 = monkey.getStatus();
        statusLabel.setText(status);
        statusInfo.setText(status2);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        statusInfo.setFont(new Font("Arial", Font.PLAIN, 12));
    }

    public MonkeyDetailsPanel() {
        Dimension windowSize = new Dimension(300, 200);
        this.setPreferredSize(windowSize);
        this.setMaximumSize(windowSize);
        this.setMinimumSize(windowSize);

        String id = " Please select a monkey...";
        String birth = "";
        String death = null;
        String gender = "";
        String momId = "";
        String status = "";
        String id2 = "";
        String birth2 = "";
        String death2 = null;
        String gender2 = "";
        String momId2 = "";
        String status2 = "";

        //this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setLayout(new GridLayout(6,2));
        this.setBackground(Color.WHITE);




        // Creates label to display the ID
        idLabel = new JLabel(id);
        idLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(idLabel);
        idInfo = new JLabel(id2);
        idInfo.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(idInfo);



        // Creates label to display the Date of Birth
        birthLabel = new JLabel(birth);
        birthLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(birthLabel);
        birthInfo = new JLabel(birth2);
        birthInfo.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(birthInfo);

        // Creates label to display the Date of Death
        deathLabel = new JLabel(death);
        deathLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(deathLabel);
        deathInfo = new JLabel(death2);
        deathInfo.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(deathInfo);


        // Creates label to display the Gender
        genderLabel = new JLabel(gender);
        genderLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(genderLabel);
        genderInfo = new JLabel(gender2);
        genderLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(genderInfo);

        // Creates label to display the Mom's ID
        momLabel = new JLabel(momId);
        momLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        momInfo = new JLabel(momId2);
        momInfo.setAlignmentX(Component.LEFT_ALIGNMENT);
        momLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Mom Clicked");
            }
        });
        // Changes mouse cursor on hover.
        momLabel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                momLabel.setCursor(Cursor.getPredefinedCursor(
                                    Cursor.HAND_CURSOR));

            }
        });
        this.add(momLabel);
        this.add(momInfo);


        // Creates label to display the Status
        statusLabel = new JLabel(status);
        statusLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        statusInfo = new JLabel(status2);
        statusInfo.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(statusLabel);
        this.add(statusInfo);

    }
}
