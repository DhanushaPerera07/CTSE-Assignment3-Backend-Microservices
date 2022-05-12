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

import lk.sliit.ecommercejavaproject.dto.OrderDTO;
import lk.sliit.ecommercejavaproject.entity.Order;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.time.ZonedDateTime;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class OrderDTOMapper {

    @Autowired
    ApplicationContext context;

    /*  -------------------- Order  -------------------- */

    @Mappings(value = {
            @Mapping(target = "zonedDateTime", source = ".", qualifiedByName = {"toGetZonedDateTimeAsString",})
    })
    public abstract Order getOrder(OrderDTO orderDTO); // "toMapZonedDateTime"

    //    @Mapping(target = "id", source = "id")
//    @InheritInverseConfiguration
    @Mapping(target = "orderedDateTIme", source = "zonedDateTime")
    public abstract OrderDTO getOrderDTO(Order order);

    public abstract List<OrderDTO> getOrderDTOList(List<Order> orderList);

    @InheritInverseConfiguration
    public abstract List<Order> getOrderList(List<OrderDTO> orderDTOList);

    /**
     * This @Named mapper function used to map ZonedDateTime DTO ---> Entity.
     */
    @Named(value = "toMapZonedDateTime")
    public ZonedDateTime mapDTOZdtToEntityZdt(OrderDTO orderDTO) {
        ZonedDateTime zonedDateTimeValue = orderDTO.getOrderedDateTIme();
        if (zonedDateTimeValue == null) {
            /* If zonedDateTimeValue is empty, we deal with an order insertion operation.
            Hence, we need to set the CurrentZonedDateTime */
            ZonedDateTime currentZonedDateTime = context.getBean("CurrentZonedDateTime", ZonedDateTime.class);
            return currentZonedDateTime;
        }
        return zonedDateTimeValue;
    }

    @Named(value = "toGetZonedDateTimeAsString")
    public String getZonedDateTimeAsString(OrderDTO orderDTO) {
        ZonedDateTime zonedDateTimeValue = orderDTO.getOrderedDateTIme();
        if (zonedDateTimeValue == null) {
            /* If zonedDateTimeValue is empty, we deal with an order insertion operation.
            Hence, we need to set the CurrentZonedDateTime */
            ZonedDateTime currentZonedDateTime = context.getBean("CurrentZonedDateTime", ZonedDateTime.class);
            return currentZonedDateTime.toString();
        }

        return zonedDateTimeValue.toString();
    }

}
