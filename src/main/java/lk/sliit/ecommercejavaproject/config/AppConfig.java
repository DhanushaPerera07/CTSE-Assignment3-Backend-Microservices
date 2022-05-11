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
package lk.sliit.ecommercejavaproject.config;

import lk.sliit.ecommercejavaproject.dto.OrderDetailDTO;
import lk.sliit.ecommercejavaproject.entity.OrderDetail;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;

@Configuration
@ComponentScan(basePackages = {
        "lk.sliit.ecommercejavaproject.entity",
        "lk.sliit.ecommercejavaproject.dto",
        "lk.sliit.ecommercejavaproject.service.util.mapper"
})
public class AppConfig {

    @Bean(name = {"OrderDetailList", "newOrderDetailList"})
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public ArrayList<OrderDetail> OrderDetailList() {
        return new ArrayList<OrderDetail>();
    }

    @Bean(name = {"OrderDetailDTOList", "newOrderDetailDTOList"})
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public ArrayList<OrderDetailDTO> OrderDetailDTOList() {
        return new ArrayList<OrderDetailDTO>();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//    @Scope("prototype")
    public ZonedDateTime CurrentZonedDateTime() {
        ZonedDateTime zonedDateTime = LocalDateTime.now().atZone(ZoneId.of("Asia/Colombo"));
//        System.out.println("ZonedDateTime: " + zonedDateTime);
        return zonedDateTime;
    }
}
