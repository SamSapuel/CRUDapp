import Model.Employees;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.StringStack;
import org.hibernate.mapping.Table;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

/**
 * GUI of Employees entity
 */

public class Window {
    private JTextField textFieldName;
    private JTextField textFieldSurname;
    private JTextField textFieldCountry;
    private JTextField textFieldCity;
    private JTextField textFieldStreet;
    private JTextField textFieldId;
    private JPanel Values;
    private JButton createButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton readButton;
    private JPanel TablePanel;
    private JTable tableEmployees;
    private JScrollPane TableScrollPanel;
    private EmployeeService employeeService = new EmployeeService();
    private List employeeList;
    private JFrame jFrame;

    public Window() {

        /**
         * Buttons which represent CRUD model in our app
         */

        // Create
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Employees employee = new Employees();
                employee.setId(String.valueOf(textFieldId.getText()));
                employee.setName(textFieldName.getText());
                employee.setSurname(textFieldSurname.getText());
                employee.setCountry(textFieldCountry.getText());
                employee.setCity(textFieldCity.getText());
                employee.setStreet(textFieldStreet.getText());
                EmployeesDao.save(employee);

                JOptionPane optionPane = new JOptionPane();
                JOptionPane.showMessageDialog(optionPane, "Check the table!");
                JButton okButton = new JButton("Ok");
                okButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        optionPane.setVisible(false);
                    }
                });
                optionPane.add(okButton);

            }
        });

        //Update
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean find = false;
                for (Employees employee : employeeService.findAllEmployees()) {
                    int id = Integer.parseInt(textFieldId.getText());
                    if (id == employee.getId()) {
                        employee.setName(textFieldName.getText());
                        employee.setSurname(textFieldSurname.getText());
                        employee.setCountry(textFieldCountry.getText());
                        employee.setCity(textFieldCity.getText());
                        employee.setStreet(textFieldStreet.getText());
                        EmployeesDao.update(employee);
                        find = true;

                        JOptionPane optionPane = new JOptionPane();
                        JOptionPane.showMessageDialog(optionPane, "Check the table!");
                        JButton okButton = new JButton("Ok");
                        okButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                optionPane.setVisible(false);
                            }
                        });
                        optionPane.add(okButton);
                    }
                }
                if (!find) {
                    JOptionPane optionPane = new JOptionPane();
                    JOptionPane.showMessageDialog(optionPane, "Employee with Id " + textFieldId.getText() + " doesnt exist, try again!");
                    JButton okButton = new JButton("Ok");
                    okButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            optionPane.setVisible(false);
                        }
                    });
                    optionPane.add(okButton);
                }
            }
        });

        //Delete
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean find = false;
                //EmployeesDao employeesDao = new EmployeesDao();
                //EmployeeService employeeService = new EmployeeService();
                //textFieldId.getText();
                //employeeService.findAllEmployees();
                for (Employees employee : employeeService.findAllEmployees()) {
                    int id = Integer.parseInt(textFieldId.getText());
                    if (id == employee.getId()) {
                        EmployeesDao.delete(employee);
                        find = true;

                        JOptionPane optionPane = new JOptionPane();
                        JOptionPane.showMessageDialog(optionPane, "Check the table!");
                        JButton okButton = new JButton("Ok");
                        okButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                optionPane.setVisible(false);
                            }
                        });
                        optionPane.add(okButton);

                        //break;
                    }
                }
                if (!find) {
                    JOptionPane optionPane = new JOptionPane();
                    JOptionPane.showMessageDialog(optionPane, "Employee with Id " + textFieldId.getText() + " doesnt exist, try again!");
                    JButton okButton = new JButton("Ok");
                    okButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            optionPane.setVisible(false);
                        }
                    });
                    optionPane.add(okButton);
                }
            }
        });

        // Read
        readButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame tableFrame = new JFrame("Employees");
                tableEmployees = new JTable();
                tableFrame.setSize(new Dimension(500, 500));
                tableFrame.setMinimumSize(new Dimension(400, 400));
                tableFrame.setLocationRelativeTo(null);
                tableFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                tableFrame.add(tableEmployees);
                try {
                    FillData();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
                tableFrame.setVisible(true);

            }
        });
    }

    public static void start() throws SQLException, ClassNotFoundException {

        JFrame jFrame = new JFrame("Employee");
        jFrame.setSize(new Dimension(400, 400));
        jFrame.setMinimumSize(new Dimension(400, 400));
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jFrame.getContentPane().add(new Window().Values);
        jFrame.setVisible(true);


    }

    // Advanced settings for a table
    private void FillData() throws SQLException, ClassNotFoundException {
        ArrayList<Employees> employeesList = new ArrayList<>();
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn("PersonalID");
        defaultTableModel.addColumn("Name");
        defaultTableModel.addColumn("Surname");
        defaultTableModel.addColumn("Country");
        defaultTableModel.addColumn("City");
        defaultTableModel.addColumn("Street");
        for (Employees employees : this.employeeService.findAllEmployees()) {
            //Employees employees = new Employees(1, "Dmitriy", "Shevchenko", "Cz", "Praha", "Ulice12345");
            defaultTableModel.addRow(new Object[]{employees.getId(), employees.getName(), employees.getSurname(), employees.getCountry(), employees.getCity(), employees.getStreet()});


        }

        tableEmployees.setModel(defaultTableModel);
        //tableEmployees.setFillsViewportHeight(true);
        //Values.add(tableEmployees);
        //TableScrollPanel.setViewportView(tableEmployees);
        //TablePanel.repaint();

    }


    /** Maybe its variant to realize the table
     ArrayList<Employees> employeesList = new ArrayList<>();
     DefaultTableModel defaultTableModel = new DefaultTableModel(new String[]{"PersonalID", "Name", "Surname", "Country", "City", "Street"}, 0);
     Class.forName("org.postgresql.Driver");
     Connection c = DriverManager.getConnection("jdbc:postgresql://slon.felk.cvut.cz:5432/db21_shevcdmi", "db21_shevcdmi", "Sk4KW5");
     PreparedStatement ps = c.prepareStatement("SELECT * FROM Employees");
     ResultSet rs = ps.executeQuery();
     while (rs.next()) {
     defaultTableModel.addRow(new Object[]{rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)});
     }
     tableEmployees.setModel(defaultTableModel);*/
    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        Values = new JPanel();
        Values.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(8, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(Values, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        textFieldSurname = new JTextField();
        textFieldSurname.setText("");
        Values.add(textFieldSurname, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textFieldCountry = new JTextField();
        textFieldCountry.setText("");
        Values.add(textFieldCountry, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textFieldCity = new JTextField();
        textFieldCity.setText("");
        Values.add(textFieldCity, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textFieldStreet = new JTextField();
        textFieldStreet.setText("");
        Values.add(textFieldStreet, new com.intellij.uiDesigner.core.GridConstraints(5, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textFieldName = new JTextField();
        textFieldName.setText("");
        Values.add(textFieldName, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textFieldId = new JTextField();
        Values.add(textFieldId, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Id");
        Values.add(label1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Name");
        Values.add(label2, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Surname");
        Values.add(label3, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Country");
        Values.add(label4, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("City");
        Values.add(label5, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Street");
        Values.add(label6, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        createButton = new JButton();
        createButton.setText("Create");
        Values.add(createButton, new com.intellij.uiDesigner.core.GridConstraints(6, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        updateButton = new JButton();
        updateButton.setText("Update");
        Values.add(updateButton, new com.intellij.uiDesigner.core.GridConstraints(6, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        deleteButton = new JButton();
        deleteButton.setText("Delete");
        Values.add(deleteButton, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        readButton = new JButton();
        readButton.setText("Table");
        Values.add(readButton, new com.intellij.uiDesigner.core.GridConstraints(7, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }


}
