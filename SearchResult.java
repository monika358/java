package com.student.result;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;
import java.sql.*;
import java.text.MessageFormat;

public class ViewResults extends JFrame {

    JTable resultTable;
    DefaultTableModel model;

    public ViewResults() {
        setTitle("View All Results");
        setSize(700, 500);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        String[] columns = {"ID", "Student ID", "Name", "Subject", "Marks", "Grade", "Date"};
        model = new DefaultTableModel(columns, 0);
        resultTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(resultTable);
        scrollPane.setBounds(20, 20, 640, 370);
        add(scrollPane);

        JButton printBtn = new JButton("Print");
        printBtn.setBounds(550, 400, 100, 30);
        add(printBtn);

        printBtn.addActionListener(e -> showPrintPreview());

        loadResults();
    }

    private void loadResults() {
        model.setRowCount(0); // Clear existing rows
        try (Connection con = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM student_results";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

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
            JOptionPane.showMessageDialog(this, "Error loading results: " + ex.getMessage());
        }
    }

    private void showPrintPreview() {
        JDialog previewDialog = new JDialog(this, "Print Preview", true);
        previewDialog.setSize(700, 400);
        previewDialog.setLocationRelativeTo(this);
        previewDialog.setLayout(new BorderLayout());

        JTable previewTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(previewTable);
        previewDialog.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton confirmPrint = new JButton("Print");
        JButton cancel = new JButton("Cancel");
        buttonPanel.add(confirmPrint);
        buttonPanel.add(cancel);
        previewDialog.add(buttonPanel, BorderLayout.SOUTH);

        confirmPrint.addActionListener(e -> {
            previewDialog.dispose();
            printResults();
        });

        cancel.addActionListener(e -> previewDialog.dispose());

        previewDialog.setVisible(true);
    }

    private void printResults() {
        try {
            MessageFormat header = new MessageFormat("All Student Results");
            MessageFormat footer = new MessageFormat("Page {0}");
            boolean complete = resultTable.print(JTable.PrintMode.FIT_WIDTH, header, footer);
            if (complete) {
                JOptionPane.showMessageDialog(this, "Print complete", "Print", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Print canceled", "Print", JOptionPane.WARNING_MESSAGE);
            }
        } catch (PrinterException pe) {
            JOptionPane.showMessageDialog(this, "Print failed: " + pe.getMessage(), "Print Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}