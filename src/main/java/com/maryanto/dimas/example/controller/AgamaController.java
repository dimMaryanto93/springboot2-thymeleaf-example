package com.maryanto.dimas.example.controller;

import com.maryanto.dimas.example.entity.Agama;
import com.maryanto.dimas.example.repository.AgamaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/master/agama")
public class AgamaController {

    @Autowired
    private AgamaDao dao;

    @GetMapping({"/", "/list"})
    public String list(Model model) {
        List<Agama> list = dao.findAll();
        model.addAttribute("list", list);
        return "/pages/master/agama/list";
    }

}
