package com.mta.diemdanhandroid.entity;

public class BluetoothEntity {
    private String nameDevice;
    private String macDevice;

    public BluetoothEntity(String nameDevice, String macDevice) {
        this.nameDevice = nameDevice;
        this.macDevice = macDevice;
    }

    public String getNameDevice() {
        return nameDevice;
    }

    public void setNameDevice(String nameDevice) {
        this.nameDevice = nameDevice;
    }

    public String getMacDevice() {
        return macDevice;
    }

    public void setMacDevice(String macDevice) {
        this.macDevice = macDevice;
    }
}
