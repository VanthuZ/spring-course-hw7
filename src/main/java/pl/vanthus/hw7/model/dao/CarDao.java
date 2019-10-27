package pl.vanthus.hw7.model.dao;

import pl.vanthus.hw7.model.Car;
import pl.vanthus.hw7.model.enums.ColorEnum;
import pl.vanthus.hw7.model.enums.MakeEnum;
import pl.vanthus.hw7.model.enums.ModelEnum;

import java.util.List;

public interface CarDao {

    void saveVideo(Enum<MakeEnum> make, Enum<ModelEnum> model, Enum<ColorEnum> color, int productionYear);
    List<Car> findAll();
    List<Car> getCarsBetweenGivenProductionYears(int yearFrom, int yearTo);
}
