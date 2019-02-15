package com.maryanto.dimas.example.controller;

import com.maryanto.dimas.example.dto.AgamaDto;
import com.maryanto.dimas.example.entity.Agama;
import com.maryanto.dimas.example.repository.AgamaDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/master/agama")
public class AgamaController {

    @Autowired
    private AgamaDao dao;

    private final Logger console = LoggerFactory.getLogger(AgamaController.class);

    @GetMapping({"/", "/list"})
    public String list(Model model) {
        List<Agama> list = dao.findAll();
        model.addAttribute("list", list);
        return "/pages/master/agama/list";
    }

    @GetMapping("/new")
    public String formNew(Model model, @ModelAttribute AgamaDto.AgamaNewRequest agama) {
        model.addAttribute("agama", agama);
        return "/pages/master/agama/new-form";
    }

    @PostMapping("/new")
    public String formNewSubmit(
            @ModelAttribute @Validated AgamaDto.AgamaNewRequest agama,
            BindingResult binding) {
        if (binding.hasErrors()) {
            console.error("{}", binding.getAllErrors());
            return "/pages/master/agama/new-form";
        }

        console.info("{}", agama);
        return "redirect:/master/agama/list";
    }

}

