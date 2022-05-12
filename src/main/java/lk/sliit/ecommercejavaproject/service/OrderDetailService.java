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

import lk.sliit.ecommercejavaproject.dto.OrderDetailDTO;
import lk.sliit.ecommercejavaproject.entity.OrderDetail;
import lk.sliit.ecommercejavaproject.exception.RecordNotFoundException;
import lk.sliit.ecommercejavaproject.repository.OrderDetailRepository;
import lk.sliit.ecommercejavaproject.service.util.mapper.OrderDetailDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService implements SuperService {

    @Autowired
    OrderDetailDTOMapper orderDetailDTOMapper;

    @Autowired
    SequenceService sequenceService;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    public Long createOrderDetail(OrderDetailDTO orderDetailDTO) {
        OrderDetail orderDetail = orderDetailDTOMapper.getOrderDetail(orderDetailDTO);

        long generatedOrderDetailId = sequenceService.update(OrderDetail.SEQUENCE_NAME);

        /* set generatedID. */
        orderDetail.setId(generatedOrderDetailId);
        orderDetailRepository.insert(orderDetail);

        return generatedOrderDetailId;
    }

    public boolean isExists(long orderId, long orderDetailId) {
        OrderDetail orderDetail = mongoTemplate.findOne(
                Query.query(Criteria.where("orderId").is(orderId).and("id").is(orderDetailId)),
                OrderDetail.class);

//        return orderDetailRepository.existsById(String.valueOf(orderDetailId));
        return (orderDetail != null);
    }

    public void updateOrderDetail(long orderId,
                                  OrderDetailDTO orderDetailDTO) {
        OrderDetail orderDetail = orderDetailDTOMapper.getOrderDetail(orderDetailDTO);

        if (!isExists(orderId, orderDetail.getId())) {
            /* No matching record found for the given ID. */
            throw new RecordNotFoundException();
        }
        orderDetailRepository.save(orderDetail);
    }

    public void deleteOrderDetail(long orderId, long orderDetailId) {
        if (!isExists(orderId, orderDetailId)) {
            /* No matching record found for the given ID. */
            throw new RecordNotFoundException();
        }

        orderDetailRepository.deleteById(orderDetailId);

//        if (!isExists(orderDetailId)) {
//            /* No matching record found for the given ID. */
//            throw new NoSuchElementException("OrderDetail record not found");
//        }
//        orderDetailRepository.deleteById(String.valueOf(orderDetailId));
    }

    public void deleteAllByOrderId(long orderId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("orderId").is(orderId));
//        orderDetailRepository.findAll(query);
        mongoTemplate.remove(query);
    }

    public OrderDetailDTO getById(long orderId, long orderDetailId) {
//        Optional<OrderDetail> optOrderDetail = orderDetailRepository.findById(String.valueOf(orderDetailId));
//        return optOrderDetail.orElse(null);
        List<OrderDetail> orderDetails = orderDetailRepository.findByIdAndOrderId(orderId, orderDetailId);
        return (orderDetails.isEmpty()) ? null : orderDetailDTOMapper.getOrderDetailDTO(orderDetails.get(0));
    }

    public List<OrderDetailDTO> getAll(long orderId) {
        return orderDetailDTOMapper.getOrderDetailDTOList(orderDetailRepository.findByOrderId(orderId));
    }
}
