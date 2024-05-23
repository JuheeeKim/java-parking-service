package proj.core.car;

public class RCar {
    private Long carId;
    private String phoneNum;
    private int year;
    private int month;
    private int date;
    private int parkingNum;

    public RCar(Long carId, String phoneNum, int year, int month, int date, int parkingNum) {
        this.carId = carId;
        this.phoneNum = String.valueOf(phoneNum);
        this.year = year;
        this.month = month;
        this.date = date;
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

    public int getMonth() {
        return month;
    }

    public int getDate() {
        return date;
    }

    public int getParkingNum() {
        return parkingNum;
    }
}
