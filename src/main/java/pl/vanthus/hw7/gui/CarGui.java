package pl.vanthus.hw7.gui;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import org.springframework.beans.factory.annotation.Autowired;

import pl.vanthus.hw7.model.dao.CarDao;
import pl.vanthus.hw7.model.enums.ColorEnum;
import pl.vanthus.hw7.model.enums.MakeEnum;
import pl.vanthus.hw7.model.enums.ModelEnum;



@Route("car-gui")
public class CarGui extends HorizontalLayout {

    CarDao carDao;

    @Autowired
    public CarGui(CarDao carDao) {
        this.carDao = carDao;

        initAddCarForm();




    }


    private void initAddCarForm(){
        FormLayout addCar = new FormLayout();

        Select<MakeEnum> makeSelect = new Select<>();
        makeSelect.setLabel("Make");
        makeSelect.setItems(MakeEnum.values());

        Select<ModelEnum> modelSelect = new Select<>();
        modelSelect.setLabel("Model");
        modelSelect.setItems(ModelEnum.values());

        Select<ColorEnum> colorSelect = new Select<>();
        colorSelect.setLabel("Color");
        colorSelect.setItems(ColorEnum.values());

        TextField productionYearField = new TextField("Production Year");


        Button addButton = new Button("Add car", new Icon(VaadinIcon.CAR));

        addButton.addClickListener(event -> {
            carDao.saveVideo(
                    makeSelect.getValue(),
                    modelSelect.getValue(),
                    colorSelect.getValue(),
                    Integer.parseInt(productionYearField.getValue())
            );
            UI.getCurrent().getPage().reload();
        });

        addCar.add(makeSelect, modelSelect, colorSelect, productionYearField, addButton);
        addCar.setResponsiveSteps(
                new FormLayout.ResponsiveStep("25em", 1),
                new FormLayout.ResponsiveStep("35em", 2)
        );

        add(addCar);
    }


}
