package tests.bluetooth;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import tests.base.BaseTest;
import tests.utils.UniversalMethods;

public class BluetoothFlow {
    InstructionScreen instructionScreen = new InstructionScreen();
    PermissionScreen permissionScreen = new PermissionScreen();
    ScanningScreen scanningScreen = new ScanningScreen();

    public void bluetoothFlow() {
      instructionScreen.instructionScreen();
      permissionScreen.permissionScreen();
      scanningScreen.scanningScreen();

    }


}
