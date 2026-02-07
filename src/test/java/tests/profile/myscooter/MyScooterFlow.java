package tests.profile.myscooter;

public class MyScooterFlow {
    MyScooter myScooter = new MyScooter();
    MyScooterScreen myScooterScreen = new MyScooterScreen();
    EditSecooterName editSecooterName = new EditSecooterName();
    Scooters scooters = new Scooters();
    public void myScooterFlow() {
        myScooter.myScooter();
        scooters.scooters();
        myScooterScreen.myScooterScreen();
        editSecooterName.editSecooterName();
    }
}
