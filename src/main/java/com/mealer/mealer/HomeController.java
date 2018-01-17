package com.mealer.mealer;


import com.mealer.mealer.Model.Product;
import com.mealer.mealer.Model.Shop;
import com.mealer.mealer.Model.ShoppingList;
import com.mealer.mealer.Model.TypeUser;
import com.mealer.mealer.Repository.ProductRepository;
import com.mealer.mealer.Repository.ShoppingRepository;
import com.mealer.mealer.Service.TypeUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class HomeController {
    @Autowired
    private TypeUsersService typeUsersService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ShoppingRepository shoppingRepository;

//    @RequestMapping(value = "/users/{userName}/maps", method = RequestMethod.GET)
//    public String getShop(Model model, @PathVariable("userName") String userName, String address){
//
//    }



    @RequestMapping(value = "/users/{userName}/lists", method = RequestMethod.GET)
    public String getShop(ShoppingList shoppingList, Model model, @PathVariable("userName") String userName){
        TypeUser tmpUser = typeUsersService.getUser(userName);
        List<ShoppingList> results = shoppingRepository.findByTypeUser(tmpUser);
        if (!results.isEmpty()){
            model.addAttribute("emptyRes", "no");
            model.addAttribute("lists", results);
            return "user";
        }
        else{
            model.addAttribute("emptyRes", "yes");
            shoppingList.setName("MyList");
            shoppingList.setTypeUser(tmpUser);
            System.out.println(shoppingList.getName());
            Set list = new HashSet<ShoppingList>(){
                {
                    add(shoppingList);
                }
            };
            tmpUser.setShoppingList(list);
            typeUsersService.save(tmpUser);
        }
        return "user";
    }

    @RequestMapping(value = "/users/{userName}", method = RequestMethod.GET)
    public String getShoppingList(Model model, @PathVariable("userName") String userName){
        TypeUser userExists = typeUsersService.getUser(userName);
        if (userExists != null){
            model.addAttribute("show", "yes");
            model.addAttribute("found", userExists);
            model.addAttribute("url", "/users/" + userName + "/lists");
            return "user";
        }
        return "users";
    }



//    List<ShoppingList> shoppingLists = shoppingRepository.findByTypeUser(userName);
//
//        if (shoppingLists.isEmpty()){
//        model.addAttribute("success", "empty");
//        shoppingRepository.save(shoppingList);
//    }
//        else{
//        model.addAttribute("success", "not-empty");
//        model.addAttribute("list", shoppingLists);
//    }
//        return "users";

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getAllFlyers(Model model){
        List<TypeUser> users = typeUsersService.getAllUsers();
        model.addAttribute("list", users);
        return "users";
    }


    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public ModelAndView index(TypeUser typeUser, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        TypeUser userExists = typeUsersService.getUser(typeUser.getName());
        if (userExists != null)
            bindingResult.rejectValue("name", null, "There is already a user with same name");
        if (bindingResult.hasErrors()){
            modelAndView.setViewName("index");
            return modelAndView;
        }
        typeUsersService.save(typeUser);
        modelAndView.setViewName("index");
        return modelAndView;
    }


    //AIzaSyCm_FEVQ2CBG8W_rlj2MTewXfFFnfsRKNE

}