package tests.settings.mygarage;

import tests.base.BaseTest;

public class MyGarageFlow extends BaseTest {
    MyGarage myGarage = new MyGarage();
    ScooterManual scooterManual = new ScooterManual();
    public void myGarageFlow() {
        myGarage.myGarage();
        scooterManual.scooterManual();
        driver.navigate().back();

    }
}
