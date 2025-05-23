package com.student.result;

import javax.swing.*;

public class FeedbackGenerator extends JFrame {

    JTextField marksField;
    JTextArea feedbackArea;

    public FeedbackGenerator() {
        setTitle("Feedback Generator");
        setSize(400, 300);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel lbl = new JLabel("Enter Marks:");
        lbl.setBounds(30, 30, 100, 30);
        add(lbl);

        marksField = new JTextField();
        marksField.setBounds(150, 30, 100, 30);
        add(marksField);

        JButton genBtn = new JButton("Generate");
        genBtn.setBounds(270, 30, 100, 30);
        add(genBtn);

        feedbackArea = new JTextArea();
        feedbackArea.setBounds(30, 80, 320, 150);
        add(feedbackArea);

        genBtn.addActionListener(e -> {
            try {
                int marks = Integer.parseInt(marksField.getText());
                String feedback;
                if (marks >= 90) feedback = "Excellent performance!";
                else if (marks >= 75) feedback = "Very good job!";
                else if (marks >= 60) feedback = "Good, but can improve.";
                else if (marks >= 40) feedback = "Needs more practice.";
                else feedback = "Poor. Needs serious improvement.";
                feedbackArea.setText(feedback);
            } catch (NumberFormatException ex) {
                feedbackArea.setText("Please enter valid marks.");
            }
        });
    }
}