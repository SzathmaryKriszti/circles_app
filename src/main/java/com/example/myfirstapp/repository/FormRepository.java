package com.example.myfirstapp.repository;

import com.example.myfirstapp.domain.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormRepository extends JpaRepository <Form, Long>{
}
