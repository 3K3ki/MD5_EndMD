package com.dsecurity.service.serviceIMPL;

import com.dsecurity.model.OrderDetail;
import com.dsecurity.repository.IOrderDetailRepository;
import com.dsecurity.repository.IOrderRepository;
import com.dsecurity.service.IOrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class OrderDetailServiceIMPL implements IOrderDetailService {
    private final IOrderDetailRepository iOrderDetailRepository;
    @Override
    public List<OrderDetail> findAll() {
        return iOrderDetailRepository.findAll();
    }

    @Override
    public OrderDetail save(OrderDetail orderDetail) {
        return iOrderDetailRepository.save(orderDetail);
    }

    @Override
    public void deleteById(Long id) {
        iOrderDetailRepository.deleteById(id);
    }

    @Override
    public OrderDetail findById(Long id) {
        return iOrderDetailRepository.findById(id).get();
    }

    @Override
    public OrderDetail update(Long id, OrderDetail orderDetail) {
        return null;
    }
}
