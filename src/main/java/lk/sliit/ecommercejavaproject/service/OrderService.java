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
import lk.sliit.ecommercejavaproject.exception.ZonedDateTimeParseException;
import lk.sliit.ecommercejavaproject.repository.OrderDetailRepository;
import lk.sliit.ecommercejavaproject.repository.OrderRepository;
import lk.sliit.ecommercejavaproject.service.util.mapper.OrderDTOMapper;
import lk.sliit.ecommercejavaproject.service.util.mapper.OrderDetailDTOMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Slf4j
public class OrderService implements SuperService {

    @Autowired
    ApplicationContext context;

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

        List<OrderDetail> orderDetailList = orderDetailDTOMapper.getOrderDetailList(orderDTO.getOrderDetailList());

        for (OrderDetail orderDetail : orderDetailList) {
            orderDetail.setOrderId(generatedOrderId);
        }

        log.info("insertOrder() Order : " + order);

        /* Insert the Order. */
        orderRepository.insert(order);
        /* Insert the Order-Detail. */
        orderDetailRepository.insert(orderDetailList);

        return order.getId();
    }

    public boolean isExists(long orderId) {
        return orderRepository.existsById(orderId);
    }

    public void updateOrder(OrderDTO orderDTO) {
        Order order = orderDTOMapper.getOrder(orderDTO);
        List<OrderDetail> orderDetailList = orderDetailDTOMapper.getOrderDetailList(orderDTO.getOrderDetailList());

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
        orderRepository.deleteById(orderId);
    }

    public OrderDTO getOrderById(long orderId) {
        Optional<Order> optOrder = orderRepository.findById(orderId);
        Order order = optOrder.orElse(null);
        if (order == null) {
            return null;
        }
        OrderDTO orderDTO = prepareOrderDTO(order);
        log.info("Order: " + optOrder.orElse(null));
        return orderDTO;
    }

    public List<OrderDTO> getAllOrders() {
        List<Order> orderList = orderRepository.findAll();
        ArrayList<OrderDTO> orderDTOList = (ArrayList<OrderDTO>) context.getBean("newOrderDTOList");

        for (Order order : orderList) {
            OrderDTO orderDTO = prepareOrderDTO(order);
            orderDTOList.add(orderDTO);
        }

        return orderDTOList;
    }

    private OrderDTO prepareOrderDTO(Order order) {
        OrderDTO orderDTO = context.getBean(OrderDTO.class);
        orderDTO.setId(order.getId());
        /* Get all the Order-Detail record for given orderID from database. */
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(order.getId());
        orderDTO.setOrderDetailList(orderDetailDTOMapper.getOrderDetailDTOList(orderDetailList));

        try {
            /* ZonedDateTime as String ---> ZonedDateTime. */
            orderDTO.setOrderedDateTIme(ZonedDateTime.parse(order.getZonedDateTime()));
        } catch (
                DateTimeParseException e) {
            throw new ZonedDateTimeParseException(null, e);
        }
        return orderDTO;
    }

}
