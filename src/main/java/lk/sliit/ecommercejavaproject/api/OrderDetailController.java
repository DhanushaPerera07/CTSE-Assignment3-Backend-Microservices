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
import lk.sliit.ecommercejavaproject.dto.OrderDetailDTO;
import lk.sliit.ecommercejavaproject.exception.BadRequestException;
import lk.sliit.ecommercejavaproject.exception.RecordNotFoundException;
import lk.sliit.ecommercejavaproject.service.OrderDetailService;
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
public class OrderDetailController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/{orderIdStr}/order-details")
    public List<OrderDetailDTO> getAll(@PathVariable(name = "orderIdStr") String orderIdStr) {
        Long orderId = ApiUtil.getLongId(orderIdStr);

        /* If no Order record found. Prompt an error. */
        checkForOrderRecord(orderId);
        return orderDetailService.getAll(orderId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/{orderIdStr}/order-details/{orderDetailIdStr}")
    public OrderDetailDTO getOrderDetailById(@PathVariable(name = "orderIdStr") String orderIdStr,
                                             @PathVariable(name = "orderDetailIdStr") String orderDetailIdStr) {
        Long orderId = ApiUtil.getLongId(orderIdStr);
        Long orderDetailId = ApiUtil.getLongId(orderDetailIdStr);

        /* If no Order record found. Prompt an error. */
        checkForOrderRecord(orderId);

        /* Get the record from  */
        OrderDetailDTO orderDetailDTO = orderDetailService.getById(orderId, orderDetailId);

        /* If order not found. */
        if (orderDetailDTO == null) throw new RecordNotFoundException();

        return orderDetailDTO;
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            value = "/{orderIdStr}/order-details")
    public Long saveOrderDetail(@PathVariable(name = "orderIdStr") String orderIdStr,
                                @RequestBody OrderDetailDTO orderDetailDTO) {
        Long orderId = ApiUtil.getLongId(orderIdStr);

        /* If no Order record found. Prompt an error. */
        checkForOrderRecord(orderId);

        /* set order ID. */
        orderDetailDTO.setOrderId(orderId);
        if (orderDetailDTO.getOrderId() != orderId)
            throw new BadRequestException("Order ID (URL) and OrderID in request body are mismatched.");

        return orderDetailService.createOrderDetail(orderDetailDTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{orderIdStr}/order-details/{orderDetailIdStr}",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateOrderDetail(@PathVariable(name = "orderIdStr") String orderIdStr,
                                  @PathVariable(name = "orderDetailIdStr") String orderDetailIdStr,
                                  @RequestBody OrderDetailDTO orderDetailDTO) {
        Long orderId = ApiUtil.getLongId(orderIdStr);
        Long orderDetailId = ApiUtil.getLongId(orderDetailIdStr);

        /* If no Order record found. Prompt an error. */
        checkForOrderRecord(orderId);

        orderDetailDTO.setId(orderDetailId);
        orderDetailDTO.setOrderId(orderId);
        orderDetailService.updateOrderDetail(orderId, orderDetailDTO);

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{orderIdStr}/order-details/{orderDetailIdStr}")
    public void deleteOrderDetail(@PathVariable(name = "orderIdStr") String orderIdStr,
                                  @PathVariable(name = "orderDetailIdStr") String orderDetailIdStr) {
        Long orderId = ApiUtil.getLongId(orderIdStr);
        Long orderDetailId = ApiUtil.getLongId(orderDetailIdStr);

        /* If no Order record found. Prompt an error. */
        checkForOrderRecord(orderId);

        System.out.println("OrderID: " + orderId);
        System.out.println("OrderDetailID: " + orderDetailId);

        orderDetailService.deleteOrderDetail(orderId, orderDetailId);
    }

    private void checkForOrderRecord(long orderId) {
        /* If no Order record found. Prompt an error. */
//        if (orderService.getOrderById(orderId) == null) throw new RecordNotFoundException();
        if (!orderService.isExists(orderId)) {
            /* No matching record found for the given ID. */
            throw new RecordNotFoundException("Order record not found for ID: " + orderId);
        }
    }
}
