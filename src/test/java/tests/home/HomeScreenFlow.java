package tests.home;

import listeners.ExtentLogger;

public class HomeScreenFlow {
    Home home = new Home();
    RideingImpact rideingImpact = new RideingImpact();
    BatteryCard batteryCard = new BatteryCard();
    public void homeScreenFlow() {
        ExtentLogger.info("🏠 Home Screen Flow Started");
        home.home();
        rideingImpact.rideingImpactScreen();
        batteryCard.batteryCard();
        ExtentLogger.info("✅ Home Screen Flow Completed");
    }
}
