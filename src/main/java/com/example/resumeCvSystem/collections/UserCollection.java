package com.example.resumeCvSystem.collections;

import com.example.resumeCvSystem.domain.Education;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user")
public class UserCollection implements Serializable {
    @Id
    private String id;
    private String name;
    private Long age;
    private String avatar;
    private String description;
    private List<Education> educations;
}
