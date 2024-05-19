package proj.core.car;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RCarRepository implements CarRepository<RCar> {

    // Map의 key: long타입->Id저장 ,, Map의 value: id, phoneNum, ... 등의 인스턴스를 가진 car 객체 저장
    private static final Map<Long, RCar> store = new HashMap<>();

    @Override
    public boolean save(RCar rCar) {
        if (store.containsKey(rCar.getCarId())) {
            System.out.println("중복된 차량 번호입니다. 주차 공간 배정에 실패했습니다.");
            return false;
        } else {
            store.put(rCar.getCarId(), rCar);
            return true;
        }
    }

    @Override
    public void delete(RCar rCar) {
        store.remove(rCar.getCarId());
    }

    // Map에서 해당 CarId에 해당하는 Car 객체를 가져옴, 키값을 통해 Map의 값(Car객체)을 찾아옴
    @Override
    public RCar findById(Long CarId) {
        return store.get(CarId);
    }

    @Override
    public List<RCar> getAllInfo() {
        return new ArrayList<>(store.values());
    }

}
