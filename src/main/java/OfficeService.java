import Model.Employees;
import Model.Office;

import java.util.List;

/**
 * Its like interface of OfficeDao
 */

public class OfficeService {

    private OfficeDao officeDao = new OfficeDao();

    public OfficeService() {

    }

    public Office office(int id) {
        return OfficeDao.findOfficeById(id);
    }

    public void saveOffice(Office office) {
        OfficeDao.save(office);
    }

    public void deleteOffice(Office office) {
        OfficeDao.delete(office);
    }
    public void updateOffice(Office office) {
        OfficeDao.update(office);
    }
    public List<Office> findAllOffices() {
        return OfficeDao.findAll();
    }
    public Office findOfficeById(int id) {
        return OfficeDao.findOfficeById(id);
    }
}
