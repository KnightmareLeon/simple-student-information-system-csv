package main;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

import app.buttons.ManagementToolbar;
import app.frames.SaveChecker;
import app.input.fields.SearchBar;
import app.tables.ManagementTable;

public class Main {

    public static void main(String[] args) throws Exception{
        JFrame mainFrame = new JFrame();
        mainFrame.setResizable(true);     
        mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        mainFrame.setTitle("Student Management System");
        mainFrame.setLayout(new BorderLayout());

        ManagementTable table = new ManagementTable();
        JScrollPane sp = new JScrollPane(table);

        JToolBar tb = new ManagementToolbar(sp, table);
        mainFrame.add(tb, BorderLayout.NORTH);
        
        SearchBar searchBar = new SearchBar(table.getRowSorter());

        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        content.add(searchBar, BorderLayout.NORTH);
        content.add(sp, BorderLayout.CENTER);

        mainFrame.add(content, BorderLayout.CENTER);

        mainFrame.setSize((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 100, (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 100);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);  
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        WindowListener listener = new SaveChecker(table);
        mainFrame.addWindowListener(listener);
    }


}
