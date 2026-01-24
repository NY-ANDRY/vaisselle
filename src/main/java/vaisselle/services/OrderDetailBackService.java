package vaisselle.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vaisselle.models.tables.OrderDetailBack;
import vaisselle.repositories.OrderDetailBackRepository;

@Service
public class OrderDetailBackService {

    @Autowired
    private OrderDetailBackRepository orderDetailBackRepository;

    public OrderDetailBack save(OrderDetailBack orderDetailBack) {
        return orderDetailBackRepository.save(orderDetailBack);
    }

    public double getTotalReturns() {
        Double total = orderDetailBackRepository.sumValue();
        return total != null ? total : 0.0;
    }
}
