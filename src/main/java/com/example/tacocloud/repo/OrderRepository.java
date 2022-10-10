package com.example.tacocloud.repo;

import com.example.tacocloud.domain.TacoOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {

//    List<TacoOrder> findByDeliveryZip(String deliveryZip);
//
//    List<TacoOrder> readOrdersByDeliveryZipAndPlaceAtBetween
//            (String deliveryZip, Date startDate, Date endDate);

//    @Query(value = "Order o where o.deliveryCity='Seattle'")
//    List<TacoOrder> readOrdersDeliveredInSeattle();
}
