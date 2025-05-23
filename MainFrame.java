package com.student.result;

import javax.swing.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Student Result System");
        setSize(400, 400);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton addBtn = new JButton("Add Result");
        JButton viewBtn = new JButton("View Results");
        JButton searchBtn = new JButton("Search Result");
        JButton printBtn = new JButton("Print Result");
        JButton feedbackBtn = new JButton("Generate Feedback");

        addBtn.setBounds(100, 40, 200, 30);
        viewBtn.setBounds(100, 90, 200, 30);
        searchBtn.setBounds(100, 140, 200, 30);
        printBtn.setBounds(100, 190, 200, 30);
        feedbackBtn.setBounds(100, 240, 200, 30);

        add(addBtn); add(viewBtn); add(searchBtn); add(printBtn); add(feedbackBtn);

        addBtn.addActionListener(e -> new AddResult().setVisible(true));
        viewBtn.addActionListener(e -> new ViewResults().setVisible(true));
        searchBtn.addActionListener(e -> new SearchResult().setVisible(true));
        printBtn.addActionListener(e -> new PrintResults().setVisible(true));
        feedbackBtn.addActionListener(e -> new FeedbackGenerator().setVisible(true));
    }

    public static void main(String[] args) {
        new MainFrame().setVisible(true);
    }
}