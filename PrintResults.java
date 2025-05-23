package com.student.result;

import javax.swing.*;
import java.awt.*;
import java.awt.print.*;

public class PrintResults extends JFrame implements Printable {

    private JTextArea printArea;

    public PrintResults() {
        setTitle("Print Student Result");
        setSize(500, 400);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Text area to enter result content
        printArea = new JTextArea("Enter or paste the result here...");
        add(new JScrollPane(printArea), BorderLayout.CENTER);

        // Button to trigger print
        JButton printBtn = new JButton("Print");
        printBtn.addActionListener(e -> showPrinterDialogAndPrint());
        add(printBtn, BorderLayout.SOUTH);
    }

    // Show print dialog and allow user to select printer
    private void showPrinterDialogAndPrint() {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(this);

        if (job.printDialog()) {  // shows the printer selection dialog
            try {
                job.print();
            } catch (PrinterException ex) {
                JOptionPane.showMessageDialog(this, "Print failed: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Defines what gets printed
    @Override
    public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException {
        if (pageIndex > 0) return NO_SUCH_PAGE;

        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());

        // Print content of JTextArea
        printArea.printAll(g);

        return PAGE_EXISTS;
    }
}