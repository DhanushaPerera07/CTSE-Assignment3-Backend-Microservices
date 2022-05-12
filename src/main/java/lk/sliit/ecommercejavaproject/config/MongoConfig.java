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

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Collection;
import java.util.Collections;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

    private static final String MONGODB_DATABASE_URL = "mongodb.database.url";
    private static final String MONGODB_DATABASE_NAME = "mongodb.database.name";

    private static final String MONGODB_BASE_PACKAGE = "lk.sliit.ecommercejavaproject";

    @Autowired
    private Environment env;

    private String getMongodbURL() {
        String mongodbDatabaseUrl = env.getProperty(MONGODB_DATABASE_URL);
        if (mongodbDatabaseUrl == null || mongodbDatabaseUrl.isEmpty()) {
            throw new RuntimeException("mongodb.database.url property is not set or invalid.");
        }
        return mongodbDatabaseUrl;
    }

    private String getMongodbDatabaseName() {
        String mongodbDatabaseName = env.getProperty(MONGODB_DATABASE_NAME);
        if (mongodbDatabaseName == null || mongodbDatabaseName.isEmpty()) {
            throw new RuntimeException(MONGODB_DATABASE_NAME + " property is not set or invalid.");
        }
        return mongodbDatabaseName;
    }

    @Override
    protected String getDatabaseName() {
        return getMongodbDatabaseName();
    }

    @Override
    public MongoClient mongoClient() {
        //  "mongodb://localhost:27017/test"
        ConnectionString connectionString = new ConnectionString(getMongodbURL());
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    @Override
    public Collection getMappingBasePackages() {
        return Collections.singleton(MONGODB_BASE_PACKAGE);
    }

    @Bean
    public MongoTemplate MongoTemplate() throws Exception {
        return new MongoTemplate(mongoClient(), getDatabaseName());
    }
}