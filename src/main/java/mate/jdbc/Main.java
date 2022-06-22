package mate.jdbc;

import java.util.List;
import java.util.Optional;
import mate.jdbc.dao.ManufacturerDao;
import mate.jdbc.lib.Injector;
import mate.jdbc.model.Manufacturer;

public class Main {
    private static final Injector injector = Injector.getInstance("mate");

    public static void main(String[] args) {
        ManufacturerDao manufacturerDao = (ManufacturerDao) injector
                .getInstance(ManufacturerDao.class);
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setCountry("Ukraine");
        manufacturer.setName("Elise");

        Manufacturer savedManufacturer = manufacturerDao.create(manufacturer);
        System.out.println("After create method: ");
        System.out.println(savedManufacturer + System.lineSeparator());

        List<Manufacturer> all = manufacturerDao.getAll();
        System.out.println("GetAll: ");
        all.forEach(System.out::println);
        System.out.println(System.lineSeparator());

        savedManufacturer.setCountry("England");
        savedManufacturer.setName("John");
        System.out.println("Update method: ");
        System.out.println(manufacturerDao.update(savedManufacturer));

        Optional<Manufacturer> optManufacturer = manufacturerDao.get(savedManufacturer.getId());
        System.out.println("Get by id: " + savedManufacturer.getId());
        optManufacturer.ifPresent(System.out::println);
        System.out.println(System.lineSeparator());

        manufacturerDao.delete(savedManufacturer.getId());
        System.out.println("After delete by id " + savedManufacturer.getId());
        all = manufacturerDao.getAll();
        all.forEach(System.out::println);

        manufacturerDao.get(10L);
    }
}
