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

import lk.sliit.ecommercejavaproject.dto.ItemDTO;
import lk.sliit.ecommercejavaproject.dto.OrderDTO;
import lk.sliit.ecommercejavaproject.dto.OrderDetailDTO;
import lk.sliit.ecommercejavaproject.entity.Order;
import lk.sliit.ecommercejavaproject.service.util.mapper.OrderDTOMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
class OrderServiceTest {

    @Autowired
    ApplicationContext context;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderDTOMapper orderDTOMapper;

    @DisplayName("Test MongoDB insert operation")
    @Test
    void insertOrder() {
        assertNotNull(orderService);

        OrderDTO orderDTO = context.getBean(OrderDTO.class);
        assertNotNull(orderDTO);

        ItemDTO itemDTO = context.getBean(ItemDTO.class);
        assertNotNull(itemDTO);
        itemDTO.setItemId("1122");
        itemDTO.setItemName("Nokia Mobile Phone.");
        itemDTO.setUnitPrice(new BigDecimal("5540.75"));

        OrderDetailDTO orderDetailDTO = context.getBean(OrderDetailDTO.class);
        assertNotNull(orderDetailDTO);
        orderDetailDTO.setItem(itemDTO);
        orderDetailDTO.setOrderingQuantity(4);

        ArrayList<OrderDetailDTO> newOrderDetailDTOList = (ArrayList<OrderDetailDTO>)
                context.getBean("newOrderDetailDTOList");
        assertNotNull(newOrderDetailDTOList);
        newOrderDetailDTOList.add(orderDetailDTO);
        assertEquals(1, newOrderDetailDTOList.size());

        orderDTO.setOrderDetailList(newOrderDetailDTOList);

        Order order = orderDTOMapper.getOrder(orderDTO);
        assertNotNull(order);
        assertNotNull(order.getZonedDateTime());
        log.info("Order : " + order);
//        orderService.insertOrder();
    }

    @Test
    void getAll() {
        assertNotNull(orderService);
        orderService.getAllOrders();
    }
}