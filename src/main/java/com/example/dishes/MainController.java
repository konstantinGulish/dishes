package com.example.dishes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class MainController {
    @Autowired
    DishRepository dishes;

    @Autowired
    TastyRepository tasties;

    @Autowired
    NastyRepository nasties;

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
    public String dishForm(@Valid Dish dish, BindingResult result)
    {
        if (result.hasErrors()){
            return "dish";
        }
        dishes.save(dish);
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
