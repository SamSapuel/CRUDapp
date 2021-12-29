import Model.Employees;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import static org.mockito.Mockito.mock;

public class DaoTest {
    @Test
    public void testDaoMethodFindById() {
        EmployeeService employeeService = mock(EmployeeService.class);
        Employees actual = createTestEntity();

        when(employeeService.findEmployee((int) actual.getId())).thenReturn(actual);

        Employees expected = employeeService.findEmployee((int) actual.getId());

        Assertions.assertEquals(expected, actual);

        verify(employeeService).findEmployee((int) actual.getId());
    }

    public Employees createTestEntity(){

        int id = 9999;
        String name = "Jiri";
        String surname = "Novak";
        String country = "Cz";
        String city = "Praha";
        String street = "Ulice999";
        return new Employees(id, name, surname, country, city, street);
    }

    @Test
    public void testDaoMethodFindAll(){
        // arrange

        EmployeeService employeeService = mock(EmployeeService.class);

        List<Employees> actual = createListFarmar();

        //act
        when(employeeService.findAllEmployees()).thenReturn(actual);
        List<Employees> expected = employeeService.findAllEmployees();

        //assert
        assertEquals(expected, actual);

    }

    public List<Employees> createListFarmar(){

        List<Employees> list = new ArrayList<>();

        list.add(new Employees(8, "Test1", "Test2", "Test3", "Test4", "Test5"));
        list.add(new Employees(9, "Test1", "Test2", "Test3", "Test4", "Test6"));
        list.add(new Employees(10, "Test1", "Test2", "Test3", "Test4", "Test7"));

        return list;
    }



}
