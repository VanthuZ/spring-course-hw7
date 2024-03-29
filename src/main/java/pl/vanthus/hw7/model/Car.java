package pl.vanthus.hw7.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import pl.vanthus.hw7.model.enums.ColorEnum;
import pl.vanthus.hw7.model.enums.MakeEnum;
import pl.vanthus.hw7.model.enums.ModelEnum;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {

    private long carId;
    private Enum<MakeEnum> make;
    private Enum<ModelEnum> model;
    private Enum<ColorEnum> color;
    private int productionYear;

    public Car(Enum<MakeEnum> make, Enum<ModelEnum> model, Enum<ColorEnum> color, int productionYear) {
        this.make = make;
        this.model = model;
        this.color = color;
        this.productionYear = productionYear;
    }
}
