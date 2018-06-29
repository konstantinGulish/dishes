package com.example.dishes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.cloudinary.utils.ObjectUtils;


import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    DishRepository dishes;

    @Autowired
    TastyRepository tasties;

    @Autowired
    NastyRepository nasties;

    @Autowired
    CloudinaryConfig cloudc;

    @RequestMapping("/")
    public String index(Model model)
    {

        model.addAttribute("dishes",dishes.findAll());
        return "index";
    }

    @GetMapping("/adddish")
    public String dishForm( Model model){

        model.addAttribute("dish",new Dish());

        return "dish";
    }

    @PostMapping("/process")
    public String dishForm(@Valid Dish dish, BindingResult result,
                           @RequestParam("file")MultipartFile file)
    {

        if (result.hasErrors()){
            return "dish";
        }

        if (file.isEmpty())
        {
            dish.setImg(null);
            dishes.save(dish);
            return "redirect:/";
        }
        if (dish.getImgUrl().equals("")){
            dish.setImgUrl(null);
        }
        try {
            Map uploadResult= cloudc.upload(file.getBytes(),
                    ObjectUtils.asMap("resourcetype","auto"));
            dish.setImg(uploadResult.get("url").toString());
            dishes.save(dish);
        } catch (IOException e){
            e.printStackTrace();
            return "redirect:/adddish";
        }
        return "redirect:/";
    }

    @RequestMapping("/tasty/{id}")
    public String addTasty (@PathVariable("id") long id, Model model){
        Tasty t = new Tasty();
        t.setDish(dishes.findById(id).get());
        tasties.save(t);
        return "redirect:/";
    }

    @RequestMapping("/nasty/{id}")
    public String addNasty (@PathVariable("id") long id, Model model){
        Nasty n = new Nasty();
        n.setDish(dishes.findById(id).get());
        nasties.save(n);
        return "redirect:/";
    }


}
