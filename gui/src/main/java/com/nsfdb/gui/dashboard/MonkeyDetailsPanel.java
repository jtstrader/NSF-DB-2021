package com.nsfdb.gui.dashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class MonkeyDetailsPanel extends JPanel {
    public MonkeyDetailsPanel() {
        Dimension windowSize = new Dimension(300, 400);
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
        JLabel idLabel = new JLabel(id);
        idLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(idLabel);

        // Creates label to display the Date of Birth
        JLabel birthLabel = new JLabel(birth);
        birthLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(birthLabel);

        // Creates label to display the Date of Death
        if (death != null) {
            JLabel deathLabel = new JLabel(death);
            deathLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.add(deathLabel);
        }

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

        // Creates label to display the Status
        JLabel statusLabel = new JLabel(status);
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(statusLabel);

    }
}
