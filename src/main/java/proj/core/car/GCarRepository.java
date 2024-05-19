package proj.core.car;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GCarRepository implements CarRepository<GCar>{

    private static final Map<Long, GCar> store = new HashMap<>();

    @Override
    public boolean save(GCar gCar) {
        if (store.containsKey(gCar.getCarId())) {
            System.out.println("중복된 차량 번호입니다. 입차에 실패했습니다.");
            return false;
        } else {
            store.put(gCar.getCarId(), gCar);
            return true;
        }
    }

    @Override
    public void delete(GCar gCar) {
        store.remove(gCar.getCarId());
    }

    @Override
    public GCar findById(Long carId) {
        return store.get(carId);
    }

    @Override
    public List<GCar> getAllInfo() {
        return new ArrayList<>(store.values());
    }
}
