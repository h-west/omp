package io.hsjang.omp.common.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@NoArgsConstructor
public class Doc {
    @Id
    String id;
    String name;

    public Doc(String id,String name){
        this.id = id;
        this.name = name;
    }
}