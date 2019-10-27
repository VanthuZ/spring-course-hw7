package pl.vanthus.hw7.model.dao;

import pl.vanthus.hw7.model.enums.ColorEnum;
import pl.vanthus.hw7.model.enums.MakeEnum;
import pl.vanthus.hw7.model.enums.ModelEnum;

public interface CarDao {

    void saveVideo(long carId, Enum<MakeEnum> make, Enum<ModelEnum> model, Enum<ColorEnum> color, int productionYear);
}
