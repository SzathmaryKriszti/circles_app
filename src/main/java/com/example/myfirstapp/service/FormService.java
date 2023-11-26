package com.example.myfirstapp.service;

import com.example.myfirstapp.domain.Form;
import com.example.myfirstapp.dto.FormData;
import com.example.myfirstapp.repository.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FormService {

    private FormRepository formRepository;

    @Autowired
    public FormService(FormRepository formRepository) {
        this.formRepository = formRepository;
    }


    public void saveData(FormData data) {
        Form form = new Form();
        form.setName(data.getName());
        form.setAge(data.getAge());

        formRepository.save(form);
    }
}
