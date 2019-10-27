package pl.vanthus.hw7.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.vanthus.hw7.model.Car;
import pl.vanthus.hw7.model.enums.ColorEnum;
import pl.vanthus.hw7.model.enums.MakeEnum;
import pl.vanthus.hw7.model.enums.ModelEnum;

@Repository
public class CarDaoImpl implements CarDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CarDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveVideo(long carId, Enum<MakeEnum> make, Enum<ModelEnum> model, Enum<ColorEnum> color, int productionYear) {
           Car car = new Car(carId, make, model, color, productionYear);
           String sql = "INSERT INTO cars VALUES (?, ?, ?, ?, ?)";
           jdbcTemplate.update(sql, car.getCarId(), car.getMake().toString(), car.getModel().toString(), car.getColor().toString(), car.getProductionYear());

    }
}
