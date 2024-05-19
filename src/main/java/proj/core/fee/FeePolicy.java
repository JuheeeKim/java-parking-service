package proj.core.fee;

public interface FeePolicy<T> {
    int calculateFee(int parkingMin, int tenMinutesFee, int parkingDate, int residentFee);
}
