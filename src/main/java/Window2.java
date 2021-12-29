import Model.Employees;
import Model.Office;
import net.bytebuddy.implementation.bytecode.Throw;

import javax.persistence.criteria.CriteriaBuilder;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * GUI of Office entity
 */

public class Window2 {
    private JTextField textFieldCabinetNumber;
    private JTextField textFieldCountry;
    private JTextField textFieldCity;
    private JTextField textFieldStreet;
    private JTextField textFieldChairNumber;
    private JTextField textFieldDepartment;
    private JButton createButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton readButton;
    private JPanel values;
    private JTextField textFieldEmployee;
    private OfficeService officeService = new OfficeService();
    private JTable tableOffices = new JTable();


    public Window2() {
        /**
         * Buttons which represent CRUD model in our app
         */

        //Create
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean find = false;
                //Exception exception = new Exception();
                Office office = new Office();
                office.setDepartment(Integer.parseInt(textFieldDepartment.getText()));
                office.setChairNumber(Integer.parseInt(textFieldChairNumber.getText()));
                office.setCabinetNumber(Integer.parseInt(textFieldCabinetNumber.getText()));
                office.setCountry(textFieldCountry.getText());
                office.setCity(textFieldCity.getText());
                office.setStreet(textFieldStreet.getText());
                office.setEmployee(Integer.parseInt(textFieldEmployee.getText()));
                int employee = Integer.parseInt(textFieldEmployee.getText());
                for (Employees employees : new EmployeeService().findAllEmployees()) {
                    if (employee == (int) employees.getId()) {
                        OfficeDao.save(office);
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
                    JOptionPane.showMessageDialog(optionPane, "Employee with Id " + textFieldEmployee.getText() + " doesnt exist, try again!");
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

        //Update
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean find = false;
                for (Office office : officeService.findAllOffices()) {
                    int id = Integer.parseInt(textFieldDepartment.getText());
                    int chair = Integer.parseInt(textFieldChairNumber.getText());
                    int cabinet = Integer.parseInt(textFieldCabinetNumber.getText());
                    int employee = Integer.parseInt(textFieldEmployee.getText());
                    if (id == office.getDepartment()) {
                        //OfficeDao.delete(office);
                        //Office office1 = new Office();
                        office.setChairNumber(chair);
                        office.setCabinetNumber(cabinet);
                        office.setCountry(textFieldCountry.getText());
                        office.setCity(textFieldCity.getText());
                        office.setStreet(textFieldStreet.getText());
                        office.setEmployee(employee);
                        OfficeDao.update(office);
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
                    JOptionPane.showMessageDialog(optionPane, "Office with Id " + textFieldDepartment.getText() + " doesnt exist, try again!");
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
                for (Office office : officeService.findAllOffices()) {
                    int office_id = Integer.parseInt(textFieldDepartment.getText());
                    //int employee_id = Integer.parseInt(textFieldEmployee.getText());
                    if (office_id == office.getDepartment()) {
                        OfficeDao.delete(office);
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
                    JOptionPane.showMessageDialog(optionPane, "Office with Id " + textFieldDepartment.getText() + " doesnt exist, try again!");
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

        //Read
        readButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame tableFrame = new JFrame("Offices");
                tableFrame.setSize(new Dimension(500, 500));
                tableFrame.setMinimumSize(new Dimension(400, 400));
                tableFrame.setLocationRelativeTo(null);
                tableFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                tableFrame.add(tableOffices);
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

    // Advanced settings for a table
    private void FillData() throws SQLException, ClassNotFoundException {

        DefaultTableModel defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn("Department");
        defaultTableModel.addColumn("Chair number");
        defaultTableModel.addColumn("Cabinet number");
        defaultTableModel.addColumn("Country");
        defaultTableModel.addColumn("City");
        defaultTableModel.addColumn("Street");
        defaultTableModel.addColumn("Employee");
        for (Office office : this.officeService.findAllOffices()) {
            defaultTableModel.addRow(new Object[]{office.getDepartment(), office.getChairNumber(), office.getCabinetNumber(), office.getCountry(), office.getCity(), office.getStreet(), office.getEmployee()});

        }

        tableOffices.setModel(defaultTableModel);

    }

    public static void start() {
        JFrame jFrame = new JFrame("Office");
        jFrame.setSize(new Dimension(400, 400));
        jFrame.setMinimumSize(new Dimension(400, 400));
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jFrame.getContentPane().add(new Window2().values);
        jFrame.setVisible(true);

    }


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
        values = new JPanel();
        values.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(9, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(values, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        textFieldCabinetNumber = new JTextField();
        textFieldCabinetNumber.setText("");
        values.add(textFieldCabinetNumber, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textFieldCountry = new JTextField();
        textFieldCountry.setText("");
        values.add(textFieldCountry, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textFieldCity = new JTextField();
        textFieldCity.setText("");
        values.add(textFieldCity, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textFieldStreet = new JTextField();
        textFieldStreet.setText("");
        values.add(textFieldStreet, new com.intellij.uiDesigner.core.GridConstraints(5, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textFieldChairNumber = new JTextField();
        textFieldChairNumber.setText("");
        values.add(textFieldChairNumber, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textFieldDepartment = new JTextField();
        values.add(textFieldDepartment, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Unique department number");
        values.add(label1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Chair number");
        values.add(label2, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Cabinet number");
        values.add(label3, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Country");
        values.add(label4, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("City");
        values.add(label5, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Street");
        values.add(label6, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        createButton = new JButton();
        createButton.setText("Create");
        values.add(createButton, new com.intellij.uiDesigner.core.GridConstraints(7, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        updateButton = new JButton();
        updateButton.setText("Update");
        values.add(updateButton, new com.intellij.uiDesigner.core.GridConstraints(7, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        deleteButton = new JButton();
        deleteButton.setText("Delete");
        values.add(deleteButton, new com.intellij.uiDesigner.core.GridConstraints(7, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        readButton = new JButton();
        readButton.setText("Table");
        values.add(readButton, new com.intellij.uiDesigner.core.GridConstraints(8, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textFieldEmployee = new JTextField();
        values.add(textFieldEmployee, new com.intellij.uiDesigner.core.GridConstraints(6, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setText("Employee");
        values.add(label7, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }
}
