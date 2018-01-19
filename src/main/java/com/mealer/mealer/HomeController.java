package com.mealer.mealer;


import com.mealer.mealer.Model.*;
import com.mealer.mealer.Repository.ProductRepository;
import com.mealer.mealer.Repository.ShoppingRepository;
import com.mealer.mealer.Service.Distance;
import com.mealer.mealer.Service.JSONReader;
import com.mealer.mealer.Service.StaticImageService;
import com.mealer.mealer.Service.TypeUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class HomeController {
    @Autowired
    private TypeUsersService typeUsersService;
    @Autowired
    private ShoppingRepository shoppingRepository;
    @Autowired
    private JSONReader jsonReader;
    @Autowired
    private Distance distance;


    //Wyświetlanie obrazku mapy
    @RequestMapping(value = "/users/{userName}/maps/{mapName}", method = RequestMethod.GET)
    public String getMeMap(Model model, @PathVariable("userName") String userName, @PathVariable("mapName") String mapName) throws Exception {
        JSONReader.Point a = JSONReader.getPoint(mapName);
        StaticImageService staticImageService = new StaticImageService();
        staticImageService.sendGet(a.lat, a.lng, 300, 300);
        model.addAttribute("picture", "yes");
        return "user";
    }

    //Formularz do wypełniania obecnej lokalizacji oraz podanie listy wszystkich sklepów z odległościami
    @RequestMapping(value = "/users/{userName}/maps", method = RequestMethod.GET)
    public String addAdress(Model model,@Valid Address address, @PathVariable("userName") String userName, BindingResult bindingResult){
        model.addAttribute("mytmp", "yes");
        model.addAttribute("username", userName);
        if (address != null){
            try{
                model.addAttribute("lists", "are");
                model.addAttribute("shoppinglists", shoppingRepository.findByTypeUser(typeUsersService.getUser(userName)));
                if (address.getValue() != null){
                    model.addAttribute("distancess", "herehere");
                    model.addAttribute("secondurl", "/users/" + userName + "/maps");
                    model.addAttribute("distances1", distance.beforeDistances(address.getValue()));
                    model.addAttribute("distances", jsonReader.beforeSearch(address.getValue()));
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }
            return "user";
        }
        return "user";
    }

    //Dodawanie listy. Możliwe że do poprawy wg tego co robi Mateusz
    @RequestMapping(value = "/users/{userName}/lists", method = RequestMethod.GET)
    public String getShop(ShoppingList shoppingList, Model model, @PathVariable("userName") String userName){
        TypeUser tmpUser = typeUsersService.getUser(userName);
        List<ShoppingList> results = shoppingRepository.findByTypeUser(tmpUser);
        if (!results.isEmpty()){
            model.addAttribute("emptyRes", "no");
            model.addAttribute("url", "/users/" + userName + "/lists" + "/");
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
            model.addAttribute("url1", "/users/" + userName + "/maps");
            return "user";
        }
        return "users";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getAllFlyers(Model model){
        List<TypeUser> users = typeUsersService.getAllUsers();
        model.addAttribute("list", users);
        return "users";
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String addUser(@Valid TypeUser typeUser, BindingResult bindingResult){
        TypeUser userExists = typeUsersService.getUser(typeUser.getName());
        if (userExists != null)
            return "redirect:/users/" + typeUser.getName();
        typeUsersService.save(typeUser);
        return "index";
    }
}