package com.student.result;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class ViewResults extends JFrame {

    JTable table;
    DefaultTableModel model;

    public ViewResults() {
        setTitle("View All Student Results");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);

        String[] columns = {"ID", "Student ID", "Name", "Subject", "Marks", "Grade", "Date"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 10, 560, 330);
        add(scrollPane);

        loadData();
    }

    private void loadData() {
        try (Connection con = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM student_results";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Object[] row = {
                    rs.getInt("id"),
                    rs.getString("student_id"),
                    rs.getString("name"),
                    rs.getString("subject"),
                    rs.getInt("marks"),
                    rs.getString("grade"),
                    rs.getDate("result_date")
                };
                model.addRow(row);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error loading data: " + ex.getMessage());
        }
    }
}