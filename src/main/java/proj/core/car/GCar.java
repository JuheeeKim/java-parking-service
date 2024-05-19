package proj.core.car;

public class GCar {
    private Long carId;
    private String phoneNum;
    private int year;
    private int month;
    private int date;
    private int hour;
    private int minute;
    private int parkingNum;

    public GCar(Long carId, int year, int month, int date, int hour, int minute) {
        this.carId = carId;
        this.year = year;
        this.month = month;
        this.date = date;
        this.hour = hour;
        this.minute = minute;
    }

    public GCar(Long carId, String phoneNum, int year, int month, int date, int hour, int minute, int parkingNum) {
        this.carId = carId;
        this.phoneNum = phoneNum;
        this.year = year;
        this.month = month;
        this.date = date;
        this.hour = hour;
        this.minute = minute;
        this.parkingNum = parkingNum;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getParkingNum() {
        return parkingNum;
    }
}
