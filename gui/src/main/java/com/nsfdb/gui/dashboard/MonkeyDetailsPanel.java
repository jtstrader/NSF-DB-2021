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
    JLabel birthLabel;
    JLabel deathLabel;
    JLabel genderLabel;
    JLabel momLabel;
    JLabel statusLabel;

    public void setMonkey(Monkey monkey) {
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

    public MonkeyDetailsPanel() {
        Dimension windowSize = new Dimension(250, 400);
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

        // Creates label to display the Date of Birth
        birthLabel = new JLabel(birth);
        birthLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(birthLabel);

        // Creates label to display the Date of Death
        deathLabel = new JLabel(death);
        deathLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(deathLabel);

        // Creates label to display the Gender
        genderLabel = new JLabel(gender);
        genderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(genderLabel);

        // Creates label to display the Mom's ID
        momLabel = new JLabel(momId);
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
        statusLabel = new JLabel(status);
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(statusLabel);

    }
}
