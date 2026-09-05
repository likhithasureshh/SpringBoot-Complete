package com.springboot.prod_ready_features.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.envers.Audited;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "posts")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Audited
public class PostEntity extends AuditEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String description;

}
