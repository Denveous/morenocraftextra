package net.morenorcraftextra.util;

public class DoorSensorVisibility {
    private static boolean visible = false;
    
    public static void setVisible(boolean visible) {
        DoorSensorVisibility.visible = visible;
    }
    
    public static boolean isVisible() {
        return visible;
    }
}