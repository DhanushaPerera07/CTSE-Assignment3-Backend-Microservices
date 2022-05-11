/*
 * MIT License
 *
 * Copyright (c) 2022 Dhanusha Perera
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package lk.sliit.ecommercejavaproject.service;

import lk.sliit.ecommercejavaproject.dto.OrderDTO;
import lk.sliit.ecommercejavaproject.entity.Order;
import lk.sliit.ecommercejavaproject.entity.OrderDetail;
import lk.sliit.ecommercejavaproject.repository.OrderDetailRepository;
import lk.sliit.ecommercejavaproject.repository.OrderRepository;
import lk.sliit.ecommercejavaproject.service.util.mapper.OrderDTOMapper;
import lk.sliit.ecommercejavaproject.service.util.mapper.OrderDetailDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class OrderService implements SuperService {


    @Autowired
    private OrderDTOMapper orderDTOMapper;

    @Autowired
    private OrderDetailDTOMapper orderDetailDTOMapper;

    @Autowired
    SequenceService sequenceService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    OrderDetailService orderDetailService;

    public long insertOrder(OrderDTO orderDTO) {
        Order order = orderDTOMapper.getOrder(orderDTO);

        long generatedOrderId = sequenceService.update(Order.SEQUENCE_NAME);

        /* Set generated ID to OrderID. */
        order.setId(generatedOrderId);

        List<OrderDetail> orderDetailList = orderDetailDTOMapper.getOrderDetailList(orderDTO.getOrderDetailDTOList());

        for (OrderDetail orderDetail : orderDetailList) {
            orderDetail.setOrderId(generatedOrderId);
        }

        /* Insert the Order. */
        orderRepository.insert(order);
        /* Insert the Order-Detail. */
        orderDetailRepository.insert(orderDetailList);

        return order.getId();
    }

    public boolean isExists(long orderId) {
        return orderRepository.existsById(String.valueOf(orderId));
    }

    public void updateOrder(OrderDTO orderDTO) {
        Order order = orderDTOMapper.getOrder(orderDTO);
        List<OrderDetail> orderDetailList = orderDetailDTOMapper.getOrderDetailList(orderDTO.getOrderDetailDTOList());

        /* Update order. */
        orderRepository.save(order);
        /* Update all. */
        orderDetailRepository.saveAll(orderDetailList);
    }

    public void deleteOrderById(long orderId) {
        if (!isExists(orderId)) {
            /* No matching record found for the given ID. */
            throw new NoSuchElementException("Order record not found for ID: " + orderId);
        }

        /* Delete all Order-Detail records under the OrderID. */
        orderDetailService.deleteAllByOrderId(orderId);
        /* Delete order. */
        orderRepository.deleteById(String.valueOf(orderId));
    }

    public OrderDTO getOrderById(long orderId) {
        Optional<Order> optOrder = orderRepository.findById(String.valueOf(orderId));
        return orderDTOMapper.getOrderDTO(optOrder.orElse(null));
    }

    public List<OrderDTO> getAllOrders() {
        return orderDTOMapper.getOrderDTOList(orderRepository.findAll());
    }

}
