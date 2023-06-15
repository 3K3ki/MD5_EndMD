package com.dsecurity.service.serviceIMPL;

import com.dsecurity.model.Order;
import com.dsecurity.repository.IOrderRepository;
import com.dsecurity.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class OrderServiceIMPL implements IOrderService {
    private final IOrderRepository iOrderRepository;
    @Override
    public List<Order> findAll() {
        return iOrderRepository.findAll();
    }

    @Override
    public Order save(Order order) {
        return iOrderRepository.save(order);
    }

    @Override
    public void deleteById(Long id) {
        iOrderRepository.deleteById(id);
    }

    @Override
    public Order findById(Long id) {
        return iOrderRepository.findById(id).get();
    }

    @Override
    public Order update(Long id, Order order) {
        return null;
    }
}
