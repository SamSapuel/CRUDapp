import Model.Employees;
import Model.Office;

import java.util.List;

/**
 * Its like interface for EmployeesDao
 */

public class EmployeeService {

    private EmployeesDao employeesDao = new EmployeesDao();

    public EmployeeService() {

    }

    public Employees findEmployee(int id) {
        return employeesDao.findById(id);
    }

    public void saveEmployee(Employees employee) {
        employeesDao.save(employee);
    }

    public void deleteEmployee(Employees employee) {
        employeesDao.delete(employee);
    }
    public void updateEmployee(Employees employee) {
        employeesDao.update(employee);
    }
    public List<Employees> findAllEmployees() {
        return employeesDao.findAll();
    }

}


