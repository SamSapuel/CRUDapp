import Model.Employees;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.*;
import org.mockito.stubbing.OngoingStubbing;

import javax.persistence.*;
import java.util.*;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.atLeastOnce;


public class EmployeesTest {

    private int userid;

    @Mock
    EmployeeService service;

    @Spy
    List<Employees> employees = new ArrayList<Employees>();

    @Spy
    Employees employee;

    @Mock
    private static EntityManager em;

    @Mock
    private static EntityManagerFactory emf;

    @Mock
    private static EmployeesDao employeesDao;

    @Mock
    private EmployeeService employeeService;

    @Mock
    private EntityTransaction et;

    @BeforeAll
    public void setUp() throws Exception{

        Map<Object, Object> properties = new HashMap<Object, Object>();
        properties.put("openjpa.ConnectionURL",
                "jdbc:postgresql://slon.felk.cvut.cz:5432/db21_shevcdmi");
        properties.put("openjpa.ConnectionUserName", "db21_shevcdmi");
        properties.put("openjpa.ConnectionPassword", "Sk4KW5");
        //set Schema

        //set Driver Name
        emf = Persistence.createEntityManagerFactory("PersistenceUnitName",
                properties);
        em = emf.createEntityManager();
        Mockito.when(emf.createEntityManager()).thenReturn(em);


    }



    @Test
    public void testSave() throws Exception {
        List<Employees> employees;
        MockitoAnnotations.initMocks(this);
        employees = employeeService.findAllEmployees();
        when(employeeService.findAllEmployees()).thenReturn(employees);
        System.out.println(employees.size());
        //Assert.assertEquals(employeeService.findAllEmployees(), []);
        Assert.assertEquals(employeeService.findAllEmployees(), employees);
        //Assert.assertEquals(expected.size(), employeeService.findAllEmployees().size());
        verify(employeeService, atLeastOnce()).findAllEmployees();


    }

    @Test
    public void createTest() {
        EmployeeService employeeService = mock(EmployeeService.class);


        Employees employee = createTestEntityEmployeesCorrect();
        when(employeeService.findEmployee((int) employee.getId())).thenReturn(employee);

        employeeService.saveEmployee(employee);
        Employees actual = employeeService.findEmployee(9999);

        Assertions.assertEquals(employee, actual);


    }

    @Test
    public void incorrectFindEmployee() {
        Employees employee = createTestEntityEmployeesCorrect();
        Employees employee2 = createTestEntityEmployeesCorrect();
        employee2.setId(10000);

        EmployeeService employeeService = mock(EmployeeService.class);
        when(employeeService.findEmployee(9999)).thenReturn(employee);
        when(employeeService.findEmployee(10000)).thenThrow(PersistenceException.class);

        Assertions.assertEquals(employee, employeeService.findEmployee(9999));
        Assertions.assertThrows(PersistenceException.class, () -> {
            employeeService.findEmployee(10000);
                });

    }

    @Test
    public void employeeCreateAndUpdateAndDelete() {
        Employees employee = new Employees();
        Random random = new Random();
        int id = random.nextInt(100);
        employee.setId(id);
        employee.setName(" ");
        employee.setSurname(" ");
        employee.setCountry(" ");
        employee.setCity(" ");
        employee.setStreet(" ");

        EmployeeService employeeService1 = new EmployeeService();
        List<Employees> expected = employeeService1.findAllEmployees();
        employeeService1.saveEmployee(employee);

        Assertions.assertEquals(id, employee.getId());

        employee.setName("Dmitrij");
        employee.setSurname("Shevchenko");
        employee.setCountry("Cz");
        employee.setCity("Praha");
        employee.setStreet("Ulice234");
        employeeService1.updateEmployee(employee);

        Assertions.assertEquals("Dmitrij", employee.getName());
        Assertions.assertEquals("Shevchenko", employee.getSurname());
        Assertions.assertEquals("Cz", employee.getCountry());
        Assertions.assertEquals("Praha", employee.getCity());
        Assertions.assertEquals("Ulice234", employee.getStreet());

        employeeService1.deleteEmployee(employee);
        List<Employees> result = employeeService1.findAllEmployees();

        Assertions.assertEquals(expected.size(), result.size());
        //Mockito.doReturn(new Employees()).when(employeesDao).findById(1);

        //Mockito.verify()
    }

    @Test
    public void employee2CreateAndDelete() {
        Employees employee = new Employees();
        Random random = new Random();
        int id = random.nextInt(100);
        employee.setId(id);
        employee.setName(" ");
        employee.setSurname(" ");
        employee.setCountry(" ");
        employee.setCity(" ");
        employee.setStreet(" ");
        employeesDao.save(employee);


        employee.setId(id+1);
        employee.setName("Dimitrij");
        employee.setSurname("Shevchenko");
        employee.setCountry("Cz");
        employee.setCity("Praha");
        employee.setStreet("Ulice234");
        employeesDao.save(employee);

        Employees employees = employeesDao.findById(id);
        employeesDao.delete(employees);

        Assertions.assertEquals(1, employeesDao.findAll().size());

    }

    @Test
    public void employee2CreateException() {
        Employees employee = new Employees();
        Random random = new Random();
        employee.setId(10);
        employee.setName("");
        employee.setSurname("");
        employee.setCountry("");
        employee.setCity("");
        employee.setStreet("");
        employeesDao.save(employee);
        Employees employees1 = new Employees();
        employee.setId(10);
        employee.setName("");
        employee.setSurname("");
        employee.setCountry("");
        employee.setCity("");
        employee.setStreet("");

        Assertions.assertThrows(PersistenceException.class, () -> {
            employeesDao.save(employee);
        });




    }

    public Employees createTestEntityEmployeesCorrect() {
        int id = 9999;
        String name = "Jiri";
        String surname = "Novak";
        String country = "Cz";
        String city = "Praha";
        String street = "Ulice999";
        return new Employees(id, name, surname, country, city, street);


    }


}
