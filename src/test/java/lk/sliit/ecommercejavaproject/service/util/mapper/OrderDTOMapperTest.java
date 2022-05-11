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
package lk.sliit.ecommercejavaproject.service.util.mapper;

import lk.sliit.ecommercejavaproject.dto.ItemDTO;
import lk.sliit.ecommercejavaproject.dto.OrderDTO;
import lk.sliit.ecommercejavaproject.dto.OrderDetailDTO;
import lk.sliit.ecommercejavaproject.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class OrderDTOMapperTest {

    @Autowired
    OrderDTO orderDTO;

    @Autowired
    @Qualifier(value = "newOrderDetailDTOList")
    List<OrderDetailDTO> orderDetailDTOList;

    @Autowired
    OrderDTOMapper orderDTOMapper;

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void getOrder() {
        orderDTO.setId(1);

        ItemDTO itemDTO = applicationContext.getBean(ItemDTO.class);
        assertNotNull(itemDTO);
        itemDTO.setItemId("1100");
        itemDTO.setItemName("Nokia Phone");
        itemDTO.setUnitPrice(new BigDecimal("2500.75"));

        OrderDetailDTO orderDetailDTO = applicationContext.getBean(OrderDetailDTO.class);
        assertNotNull(orderDetailDTO);

        orderDetailDTO.setId(1);
        orderDetailDTO.setItemDTO(itemDTO);
        orderDetailDTO.setOrderingQuantity(5);
        orderDetailDTO.setOrderId(orderDTO.getId());

        orderDetailDTOList.add(orderDetailDTO);

        orderDTO.setOrderDetailDTOList(orderDetailDTOList);

        /* get order. */
        Order order = orderDTOMapper.getOrder(orderDTO);
        assertEquals(orderDTO.getId(), order.getId());
        assertEquals(1, orderDTO.getOrderDetailDTOList().size());

        System.out.println("************** Order ************");
        System.out.println(order);
    }

//    @Test
//    void getOrderDTO() {
//    }
}