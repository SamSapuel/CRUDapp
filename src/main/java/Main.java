import Model.Employees;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Main implements ActionListener {
    private JFrame jFrame;
    public static void main(String[] args) {

        Main main = new Main();
        main.run();

    }

    public void run() {
        jFrame = new JFrame();
        JPanel panel = new JPanel();
        JButton employees = new JButton("Employees Table");
        JButton offices = new JButton("Offices Table");
        employees.addActionListener(this);
        offices.addActionListener(this);

        jFrame.setSize(new Dimension(200, 200));
        jFrame.setMinimumSize(new Dimension(200, 200));
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        panel.add(employees);
        panel.add(offices);
        jFrame.add(panel);
        jFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals("Employees Table")) {
            try {
                Window.start();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }

            jFrame.dispose();
        }
        if (action.equals("Offices Table")) {
            Window2.start();
            jFrame.dispose();
        }
    }
}
