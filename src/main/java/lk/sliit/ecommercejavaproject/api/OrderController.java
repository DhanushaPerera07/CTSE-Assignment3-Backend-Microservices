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
package lk.sliit.ecommercejavaproject.api;

import lk.sliit.ecommercejavaproject.api.util.ApiUtil;
import lk.sliit.ecommercejavaproject.dto.OrderDTO;
import lk.sliit.ecommercejavaproject.exception.BadRequestException;
import lk.sliit.ecommercejavaproject.exception.RecordNotFoundException;
import lk.sliit.ecommercejavaproject.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/orders")
@CrossOrigin(origins = "*")
@RestController
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderDTO> getAll() {
        return orderService.getAllOrders();
    }


    /**
     * Get all the Order records by OrderId.
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/{id}")
    public OrderDTO getOrderById(@PathVariable(name = "id") String id) {
        Long orderId = ApiUtil.getLongId(id);

        OrderDTO orderDTO = orderService.getOrderById(orderId);
        /* If order not found. */
        if (orderDTO == null) throw new RecordNotFoundException();

        log.info("Successfully found a matching Order record. OrderID: " + orderId);
        return orderDTO;
    }

    /**
     * Save Order record.
     * zonedDateTime: it should be null, and should not be set in the request body.
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Long saveOrder(@RequestBody OrderDTO orderDTO) {
        /* Let's check ZonedDateTime, it should be null. */
        if (orderDTO.getZonedDateTime() != null) {
            /* If ZonedDateTime is not null. Then, we prompt an error. */
            throw new BadRequestException("\"zonedDateTime\" should not be set in the request body.");
        }

        /* Insert order. */
        long insertedOrderId = orderService.insertOrder(orderDTO);

        log.info("Successfully inserted Order record. OrderID: " + insertedOrderId);
        return insertedOrderId;
    }

    /**
     * Update operation cannot be done for Order.
     * Instead, OrderDetailAPI can be used.
     */
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    @PutMapping(value = "/{id}")
    public void updateOrder(@PathVariable String id) {
    }

//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public void updateOrder(@PathVariable String id,
//                            @RequestBody OrderDTO orderDTO) {
//        Long orderId = ApiUtil.getLongId(id);
//
//        if (orderDTO != null && orderDTO.getId() == 0) {
//            orderDTO.setId(orderId);
//            if (orderDTO.getOrderDetailDTOList() == null || orderDTO.getOrderDetailDTOList().isEmpty()) {
//                /* If orderDetails array is empty. Prompt a BadRequestException. */
//                throw new BadRequestException("At least one OrderDetail should be in the request body.");
//            }
//            orderService.updateOrder(orderDTO);
//        } else {
//            /* handle error. */
//            throw new BadRequestException("OrderID should be 0 in the request body.");
//        }
//
//    }

    /**
     * Delete order by orderId, and delete all the OrderDetail records under the orderId.
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public void deleteOrder(@PathVariable String id) {
        Long orderId = ApiUtil.getLongId(id);
        log.info("OrderID: " + orderId);
        orderService.deleteOrderById(orderId);
        log.info("Successfully deleted Order record. OrderID: " + orderId);
    }

}
