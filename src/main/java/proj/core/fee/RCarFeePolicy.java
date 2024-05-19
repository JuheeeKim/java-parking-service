package proj.core.fee;

import proj.core.car.RCar;

public class RCarFeePolicy implements FeePolicy<RCar> {

    private int residentFee;

    public RCarFeePolicy(int residentFee) {
        this.residentFee = residentFee;
    }

    @Override
    public int calculateFee(int parkingMin, int tenMinutesFee, int parkingDate, int residentFee) {
        float parkingFee = (float) residentFee * (parkingDate / 30.0f);
        return (int)Math.floor(parkingFee/1000) * 1000;
    }
}
