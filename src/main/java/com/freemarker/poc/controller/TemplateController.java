package com.freemarker.poc.controller;

import com.freemarker.poc.dto.IdCard;
import com.freemarker.poc.service.TemplateService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

//For rendering template HTML strings to be rendered
@RestController
@RequestMapping("/template")
public class TemplateController {

    @Autowired
    Configuration config;

    @Autowired
    TemplateService templateService;

    @GetMapping("/test")
    public String object() throws IOException, TemplateException {
        Map<String, Object> model = new HashMap<>();
        model.put("userName", "user123345");
        model.put("userId", "U12345");
        Template template = config.getTemplate("template.html");
        StringWriter stringWriter = new StringWriter();
        template.process(model, stringWriter);
        String string = stringWriter.toString();
        return string;
    }

    @PostMapping("/create")
    public String createTemplateString(@RequestBody IdCard idCard) throws IOException, TemplateException {
        Map<String, Object> model = new HashMap<>();
        model.put("userName", idCard.getUserName());
        model.put("userId", idCard.getUserId());
        Template template = config.getTemplate("template.html");
        StringWriter stringWriter = new StringWriter();
        template.process(model, stringWriter);
        String string = stringWriter.toString();
        return string;
    }

    @PostMapping("/create-id-card")
    public String createIdCard(@RequestBody IdCard idCard) throws IOException, TemplateException {
        return templateService.createIdCard(idCard);
    }
}
