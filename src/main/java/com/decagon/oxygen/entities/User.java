package com.decagon.oxygen.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Document
@Getter
@Setter
@Builder
@AllArgsConstructor
public class User {

    @Id
    private String id;
    @Indexed(unique = true)
    private String uuid;
    private String name;
    @Indexed(unique = true)
    private String username;
    private String password;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;


    @PrePersist
    private void setCreatedAt(){
        createdAt= new Date();
    }

}

