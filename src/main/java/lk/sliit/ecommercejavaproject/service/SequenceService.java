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

import lk.sliit.ecommercejavaproject.entity.Sequence;
import lk.sliit.ecommercejavaproject.repository.SequenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class SequenceService implements SuperService {

    @Autowired
    SequenceRepository sequenceRepository;

    @Autowired
    Sequence newSequenceRecord;

    @Autowired
    MongoTemplate mongoTemplate;

    public long update(String value) {
        Sequence sequence = mongoTemplate.findOne(
                Query.query(Criteria.where("name").is(value)), Sequence.class);

        long sequenceNumber = 1;
        if (sequence == null) {
            /* insert a new record. */
            newSequenceRecord.setName(value);
            newSequenceRecord.setSequenceNumber(sequenceNumber);
            mongoTemplate.save(newSequenceRecord); // , "user"
        } else {
            /* increment the ID by 1 and update the record. */
            sequenceNumber = sequence.getSequenceNumber() + 1;
            sequence.setSequenceNumber(sequenceNumber);
            mongoTemplate.save(sequence); // , "user"
        }

        return sequenceNumber;
    }
}
