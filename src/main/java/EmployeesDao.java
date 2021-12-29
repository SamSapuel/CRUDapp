import Model.Employees;
import Model.Office;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Main methods for entity Employees which we use with the help of HibernateSessionFactoryUtil
 */

public class EmployeesDao {
    public static Employees findById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Employees employee = session.get(Employees.class, id);
        session.close();
        return employee;

    }

    public static void save(Employees employee) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(employee);
        tx1.commit();
        session.close();
    }
    public static void update(Employees employee) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(employee);
        tx1.commit();
        session.close();
    }
    public static void delete(Employees employee) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(employee);
        tx1.commit();
        session.close();
    }

    public static List<Employees> findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Employees> employees = (List<Employees>) session.createQuery("FROM Employees").list();
        session.close();
        return employees;

    }
}
