package com.nsfdb.gui.dashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MonkeyDetailsPanel extends JPanel {
    public MonkeyDetailsPanel() {
        Dimension windowSize = new Dimension(300, 400);
        this.setPreferredSize(windowSize);
        this.setMaximumSize(windowSize);
        this.setMinimumSize(windowSize);

        String id = "Animal ID";
        String birth = "Date of Birth";
        String death = "Date of Death";
        String gender = "Gender";
        String momId = "Mom";
        String status = "Status";

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Creates label to display the ID
        JLabel idLabel = new JLabel(id);
        idLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(idLabel);

        // Creates label to display the Date of Birth
        JLabel birthLabel = new JLabel(birth);
        birthLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(birthLabel);

        // Creates label to display the Date of Death
        JLabel deathLabel = new JLabel(death);
        deathLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(deathLabel);

        // Creates label to display the Gender
        JLabel genderLabel = new JLabel(gender);
        genderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(genderLabel);

        // Creates label to display the Mom's ID
        JLabel momLabel = new JLabel(momId);
        momLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        momLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        this.add(momLabel);

        // Creates label to display the Status
        JLabel statusLabel = new JLabel(status);
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(statusLabel);


    }
}
