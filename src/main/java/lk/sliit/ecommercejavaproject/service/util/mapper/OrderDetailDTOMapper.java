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

import lk.sliit.ecommercejavaproject.dto.OrderDetailDTO;
import lk.sliit.ecommercejavaproject.entity.OrderDetail;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class OrderDetailDTOMapper {

    /*  -------------------- OrderDetail  -------------------- */

    @Mapping(target = "item", source = "item")
    public abstract OrderDetail getOrderDetail(OrderDetailDTO orderDetailDTO);

    @InheritInverseConfiguration
    public abstract OrderDetailDTO getOrderDetailDTO(OrderDetail orderDetail);

    public abstract List<OrderDetailDTO> getOrderDetailDTOList(List<OrderDetail> orderDetailList);

    @InheritInverseConfiguration
    public abstract List<OrderDetail> getOrderDetailList(List<OrderDetailDTO> orderDetailDTOList);

//    void mapItemFromItemDTO(ItemDTO itemDTO, @MappingTarget Item item);
}