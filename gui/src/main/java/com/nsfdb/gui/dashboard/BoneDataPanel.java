package com.nsfdb.gui.dashboard;

import com.nsfdb.api.models.Monkey;

import javax.swing.*;
import java.awt.*;

public class BoneDataPanel extends JPanel {
    Monkey monkey;
    JLabel coronalLabel;
    JLabel saggitalLabel;
    JLabel lambodialLabel;
    JLabel humeralLabel;
    JLabel momLabel;
    JLabel statusLabel;

    public void setMonkey(Monkey monkey) {
        this.monkey = monkey;
        String id = "Coronal suture: 4";
        coronalLabel.setText(id);

        String birth = "Sagittal suture: 4";
        saggitalLabel.setText(birth);

        String gender = "Lamboidal suture: 4";
        lambodialLabel.setText(gender);

        String momId = "Humeral head: 3";
        humeralLabel.setText(momId);
    }

    public BoneDataPanel() {
        JScrollPane scrollPane = new JScrollPane();
        Dimension windowSize = new Dimension(350, 200);
        this.setPreferredSize(windowSize);
        this.setMaximumSize(windowSize);
        this.setMinimumSize(windowSize);

        String coronalSuture = "Coronal suture";
        String saggitalSuture = "Saggital suture";
        String lambodialSuture = "lambodial suture";
        String humeralHead = "Humeral Head";

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Creates label to display the ID
        coronalLabel = new JLabel(coronalSuture);
        coronalLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(coronalLabel);

        // Creates label to display the Date of Birth
        saggitalLabel = new JLabel(saggitalSuture);
        saggitalLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(saggitalLabel);

        // Creates label to display the Date of Death
        lambodialLabel = new JLabel(lambodialSuture);
        lambodialLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(lambodialLabel);

        // Creates label to display the Gender
        humeralLabel = new JLabel(humeralHead);
        humeralLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(humeralLabel);
    }
}
