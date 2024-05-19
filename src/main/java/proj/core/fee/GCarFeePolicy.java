package proj.core.fee;

import proj.core.car.GCar;

public class GCarFeePolicy implements FeePolicy<GCar>{

    private int tenMinutesFee;

    public GCarFeePolicy(int tenMinutesFee) {
        this.tenMinutesFee = tenMinutesFee;
    }

    @Override
    public int calculateFee(int parkingMin, int tenMinutesFee, int parkingDate, int residentFee) {
        int parkingFee = tenMinutesFee * parkingMin/10;
        return ((int)Math.round((float) parkingFee / 1000)) * 1000;
    }
}
