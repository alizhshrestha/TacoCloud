package com.example.tacocloud.repo;

import com.example.tacocloud.domain.TacoOrder;
import com.example.tacocloud.dto.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<TacoOrder, Long> {

//    List<TacoOrder> findByDeliveryZip(String deliveryZip);
//
//    List<TacoOrder> readOrdersByDeliveryZipAndPlaceAtBetween
//            (String deliveryZip, Date startDate, Date endDate);

//    @Query(value = "Order o where o.deliveryCity='Seattle'")
//    List<TacoOrder> readOrdersDeliveredInSeattle();

    List<TacoOrder> findByUserOrderByPlaceAtDesc(User user, Pageable pageable);
}
