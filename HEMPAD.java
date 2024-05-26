// Java Program to create a text editor using java
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.plaf.nimbus.*;
import javax.swing.text.*;

class HEMPAD extends JFrame implements ActionListener {
    // Text component
    JTextArea textArea;

    // Frame
    JFrame frame;

    // Constructor
    HEMPAD() {
        // Create a frame
        frame = new JFrame("Hempad");

        try {
            // Set Nimbus look and feel
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Text component
        textArea = new JTextArea();

        // Add scroll pane to text area
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Create a menubar
        JMenuBar menuBar = new JMenuBar();

        // Create a menu for File
        JMenu fileMenu = new JMenu("File");

        // Create menu items
        JMenuItem newMenuItem = new JMenuItem("New");
        JMenuItem openMenuItem = new JMenuItem("Open");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        JMenuItem printMenuItem = new JMenuItem("Print");

        // Add action listener
        newMenuItem.addActionListener(this);
        openMenuItem.addActionListener(this);
        saveMenuItem.addActionListener(this);
        printMenuItem.addActionListener(this);

        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(printMenuItem);

        // Create a menu for Edit
        JMenu editMenu = new JMenu("Edit");

        // Create menu items
        JMenuItem cutMenuItem = new JMenuItem("Cut");
        JMenuItem copyMenuItem = new JMenuItem("Copy");
        JMenuItem pasteMenuItem = new JMenuItem("Paste");

        // Add action listener
        cutMenuItem.addActionListener(this);
        copyMenuItem.addActionListener(this);
        pasteMenuItem.addActionListener(this);

        editMenu.add(cutMenuItem);
        editMenu.add(copyMenuItem);
        editMenu.add(pasteMenuItem);

        JMenuItem closeMenuItem = new JMenuItem("Close");
        closeMenuItem.addActionListener(this);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(closeMenuItem);

        frame.setJMenuBar(menuBar);
        frame.add(scrollPane);
        frame.setSize(500, 500);
        frame.setVisible(true);
    }

    // If a button is pressed
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("Cut")) {
            textArea.cut();
        } else if (command.equals("Copy")) {
            textArea.copy();
        } else if (command.equals("Paste")) {
            textArea.paste();
        } else if (command.equals("Save")) {
            // Create an object of JFileChooser class
            JFileChooser fileChooser = new JFileChooser("f:");

            // Invoke the showsSaveDialog function to show the save dialog
            int response = fileChooser.showSaveDialog(null);

            if (response == JFileChooser.APPROVE_OPTION) {
                // Set the label to the path of the selected directory
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());

                try {
                    // Create a file writer
                    FileWriter writer = new FileWriter(file, false);

                    // Create buffered writer to write
                    BufferedWriter buffer = new BufferedWriter(writer);

                    // Write
                    buffer.write(textArea.getText());

                    buffer.flush();
                    buffer.close();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(frame, "The user cancelled the operation");
            }
        } else if (command.equals("Print")) {
            try {
                // print the file
                textArea.print();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage());
            }
        } else if (command.equals("Open")) {
            // Create an object of JFileChooser class
            JFileChooser fileChooser = new JFileChooser("f:");

            // Invoke the showsOpenDialog function to show the open dialog
            int response = fileChooser.showOpenDialog(null);

            // If the user selects a file
            if (response == JFileChooser.APPROVE_OPTION) {
                // Set the label to the path of the selected directory
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());

                try {
                    // String
                    String content = "", line = "";

                    // File reader
                    FileReader reader = new FileReader(file);

                    // Buffered reader
                    BufferedReader buffer = new BufferedReader(reader);

                    // Initialize line
                    line = buffer.readLine();

                    // Take the input from the file
                    while ((content = buffer.readLine()) != null) {
                        line = line + "\n" + content;
                    }

                    // Set the text
                    textArea.setText(line);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(frame, "The user cancelled the operation");
            }
        } else if (command.equals("New")) {
            textArea.setText("");
        } else if (command.equals("Close")) {
            frame.setVisible(false);
        }
    }

    // Main class
    public static void main(String[] args) {
        new HEMPAD();
    }
}
