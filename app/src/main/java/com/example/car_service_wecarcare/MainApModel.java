package com.example.car_service_wecarcare;

public class MainApModel {
    String vehicleNo;
    String vehicleName;
    String appointmentDate;
    String appointmentTime ;
    String anySpecialInstruction;

    MainApModel()
    {

    }

    public MainApModel(String vehicleNo, String vehicleName, String appointmentDate, String appointmentTime, String anySpecialInstruction) {
        this.vehicleNo = vehicleNo;
        this.vehicleName = vehicleName;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.anySpecialInstruction = anySpecialInstruction;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getAnySpecialInstruction() {
        return anySpecialInstruction;
    }

    public void setAnySpecialInstruction(String anySpecialInstruction) {
        this.anySpecialInstruction = anySpecialInstruction;
    }


}
