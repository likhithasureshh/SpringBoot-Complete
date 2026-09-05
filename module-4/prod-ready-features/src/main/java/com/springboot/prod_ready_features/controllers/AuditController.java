package com.springboot.prod_ready_features.controllers;

import com.springboot.prod_ready_features.entities.PostEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/audits")
@RequiredArgsConstructor
public class AuditController {

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @GetMapping("/posts/{postsId}")
    public List<PostEntity> postEntity(@PathVariable Long postsId)
    {
        AuditReader reader = AuditReaderFactory.get(entityManagerFactory.createEntityManager());
        List<Number> readers = reader.getRevisions(PostEntity.class,postsId);
        return  readers
                .stream()
                .map(revNum -> reader.find(PostEntity.class,postsId,revNum))
                .collect(Collectors.toList());

    }

}
