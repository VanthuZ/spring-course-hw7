package pl.vanthus.hw7.gui;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import org.springframework.beans.factory.annotation.Autowired;

import pl.vanthus.hw7.model.Car;
import pl.vanthus.hw7.model.dao.CarDao;
import pl.vanthus.hw7.model.enums.ColorEnum;
import pl.vanthus.hw7.model.enums.MakeEnum;
import pl.vanthus.hw7.model.enums.ModelEnum;



@Route("car-gui")
public class CarGui extends VerticalLayout {

    CarDao carDao;

    @Autowired
    public CarGui(CarDao carDao) {
        this.carDao = carDao;

        Image image = new Image("https://cdn1.rentingcarz.com/cdn/images/landing-pages/xexotic-cars-midbanner.jpg.pagespeed.ic.lqGm6ZAgoo.jpg", "Banner");
        image.setWidth("100%");

        add(image);
        initAddCarForm();
        initGridCar();

    }

    private void initGridCar(){
        Grid<Car> carGrid = new Grid<>(Car.class);
        carGrid.setItems(carDao.findAll());

        Button betweenGivenYearsButton = new Button("Search Between Given Years");
        Button showAllButton = new Button("Show All Cars");
        TextField yearFromField = new TextField("Production year from");
        TextField yearToField = new TextField("Production year to");

        betweenGivenYearsButton.addClickListener(event ->
                carGrid.setItems(
                carDao.getCarsBetweenGivenProductionYears(
                        Integer.parseInt(yearFromField.getValue()),
                        Integer.parseInt(yearToField.getValue()))));

        showAllButton.addClickListener(event -> carGrid.setItems(carDao.findAll()));

        FormLayout gridLayout = new FormLayout();
        gridLayout.add(betweenGivenYearsButton, showAllButton, yearFromField, yearToField);

        gridLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("25em", 1),
                new FormLayout.ResponsiveStep("35em", 2));


        setHorizontalComponentAlignment(Alignment.CENTER, gridLayout);

        add(gridLayout);
        add(carGrid);
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


        Button addButton = new Button("Add Car", new Icon(VaadinIcon.CAR));

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

        setHorizontalComponentAlignment(Alignment.CENTER, addCar);
        add(addCar);
    }


}
