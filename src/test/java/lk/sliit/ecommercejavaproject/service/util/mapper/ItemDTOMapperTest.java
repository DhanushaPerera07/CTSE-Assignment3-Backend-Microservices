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
import lk.sliit.ecommercejavaproject.entity.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ItemDTOMapperTest {

    @Autowired
    private ItemDTO itemDTO;

    @Autowired
    private ItemDTOMapper itemDTOMapper;

    @Test
    void getItem() {
        assertNotNull(itemDTO);
        itemDTO.setItemId("1100");
        itemDTO.setItemName("Nokia Phone");
        itemDTO.setUnitPrice(new BigDecimal("2500.75"));

        Item item = itemDTOMapper.getItem(itemDTO);
        assertEquals(itemDTO.getItemId(), item.getItemId());
        assertEquals(itemDTO.getItemName(), item.getItemName());
        assertEquals(itemDTO.getUnitPrice(), item.getUnitPrice());
    }

//    @Test
//    void getItemDTO() {
//    }
}