package proj.core.car;

import java.util.List;

public interface CarRepository<T> {

    // car의 정보를 저장하는 기능
    boolean save(T car);

    // car의 정보를 삭제하는 기능
    void delete(T car);

    // car의 Id로 car를 찾는 기능
    T findById(Long carId);

    List<T> getAllInfo();
}
