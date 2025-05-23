package com.student.result;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class AddResult extends JFrame {
    JTextField idField, nameField, subjectField, marksField, gradeField;

    public AddResult() {
        setTitle("Add Student Result");
        setSize(300, 300);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel idLabel = new JLabel("Student ID:");
        JLabel nameLabel = new JLabel("Name:");
        JLabel subjectLabel = new JLabel("Subject:");
        JLabel marksLabel = new JLabel("Marks:");
        JLabel gradeLabel = new JLabel("Grade:");

        idField = new JTextField();
        nameField = new JTextField();
        subjectField = new JTextField();
        marksField = new JTextField();
        gradeField = new JTextField();
        JButton submitBtn = new JButton("Submit");

        idLabel.setBounds(20, 20, 80, 25);
        idField.setBounds(120, 20, 150, 25);
        nameLabel.setBounds(20, 60, 80, 25);
        nameField.setBounds(120, 60, 150, 25);
        subjectLabel.setBounds(20, 100, 80, 25);
        subjectField.setBounds(120, 100, 150, 25);
        marksLabel.setBounds(20, 140, 80, 25);
        marksField.setBounds(120, 140, 150, 25);
        gradeLabel.setBounds(20, 180, 80, 25);
        gradeField.setBounds(120, 180, 150, 25);
        submitBtn.setBounds(100, 220, 100, 30);

        add(idLabel); add(idField);
        add(nameLabel); add(nameField);
        add(subjectLabel); add(subjectField);
        add(marksLabel); add(marksField);
        add(gradeLabel); add(gradeField);
        add(submitBtn);

        submitBtn.addActionListener(e -> {
            try (Connection con = DatabaseConnection.getConnection()) {
                String sql = "INSERT INTO student_results (student_id, name, subject, marks, grade, result_date) VALUES (?, ?, ?, ?, ?, CURDATE())";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, idField.getText());
                pst.setString(2, nameField.getText());
                pst.setString(3, subjectField.getText());
                pst.setInt(4, Integer.parseInt(marksField.getText()));
                pst.setString(5, gradeField.getText());
                pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "Result Added Successfully!");
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });
    }
}