import Model.Employees;
import org.junit.Test;

public class Generator {
    public static Integer createEmployee(EmployeesDao employeesDao, String name, String surname, String country, String city, String street) {
        Employees employee = new Employees();
        int id = employeesDao.findAll().size() + 1;

        employee.setId(id);
        employee.setName(name);
        employee.setSurname(surname);
        employee.setCountry(country);
        employee.setCity(city);
        employee.setStreet(street);
        employeesDao.save(employee);

        return id;


    }
}
