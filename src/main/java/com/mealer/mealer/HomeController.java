package com.mealer.mealer;


import com.mealer.mealer.Model.*;
import com.mealer.mealer.Repository.ElementRepository;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.lang.model.element.Element;
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
    private ElementRepository elementRepository;
    @Autowired
    private JSONReader jsonReader;
    @Autowired
    private Distance distance;
    @Autowired
    private ProductRepository productRepository;

    private TypeUser user;
    private ShoppingList shoppingList;
    private Address address;

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
//    @RequestMapping(value = "/users/{userName}/lists", method = RequestMethod.GET)
//    public String getShop(ShoppingList shoppingList, Model model, @PathVariable("userName") String userName){
//        TypeUser tmpUser = typeUsersService.getUser(userName);
//        List<ShoppingList> results = shoppingRepository.findByTypeUser(tmpUser);
//        if (!results.isEmpty()){
//            model.addAttribute("emptyRes", "no");
//            model.addAttribute("url", "/users/" + userName + "/lists" + "/");
//            model.addAttribute("lists", results);
//            return "user";
//        }
//        else{
//            model.addAttribute("emptyRes", "yes");
//            shoppingList.setName("MyList");
//            shoppingList.setTypeUser(tmpUser);
//            System.out.println(shoppingList.getName());
//            Set list = new HashSet<ShoppingList>(){
//                {
//                    add(shoppingList);
//                }
//            };
//            tmpUser.setShoppingList(list);
//            typeUsersService.save(tmpUser);
//        }
//        return "user";
//    }

    @RequestMapping(value = "/users/{userName}", method = RequestMethod.GET)
    public String getShoppingList(Model model, @PathVariable("userName") String userName){
        TypeUser userExists = typeUsersService.getUser(userName);
        if (userExists != null){
            model.addAttribute("show", "yes");
            model.addAttribute("found", userExists);
            model.addAttribute("url", "/lists");
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
        if(userExists == null) {
            user=null;
        }
        if (userExists != null) {
            user=userExists;
            return "redirect:/users/" + typeUser.getName();
        }
        typeUsersService.save(typeUser);
        return "index";
    }

    @RequestMapping(value = {"/lists"}, method = RequestMethod.GET)
    public String training(Model model) {
        model.addAttribute("title", "Lists");
        model.addAttribute("name", "List");
        model.addAttribute("lists", shoppingRepository.findByTypeUser(user));
        model.addAttribute("url", "/addShoppingList");
        model.addAttribute("urlName", "Add Shopping List");

        return "lists";
    }

    @RequestMapping(value = {"/addShoppingList"}, method = RequestMethod.GET)
    public String addShoppingList(Model model) {
        ShoppingList shoppingList=new ShoppingList();
        model.addAttribute("list",shoppingList);
        return "addShoppingList";
    }

    @RequestMapping(value = {"/addShoppingList"}, method = RequestMethod.POST)
    public String DaySave(Model model, //
                          @ModelAttribute("list") @Validated ShoppingList shoppingList, //
                          BindingResult result, //
                          final RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "addShoppingList";
        }
        System.out.println(user.getName());
        shoppingList.setTypeUser(user);
        shoppingRepository.save(shoppingList);

        return "redirect:/lists";
    }

    @RequestMapping(value = {"/listElement"}, method = RequestMethod.GET)
    public String training(Model model, @RequestParam("name") String name) {
        model.addAttribute("title", name);
        shoppingList=shoppingRepository.findByName(name);
        model.addAttribute("lists", shoppingList.getElementList());
        model.addAttribute("url", "/addElementList");
        model.addAttribute("urlName", "Add Shopping List");
        model.addAttribute("urlCheap", "/getCheap");
        model.addAttribute("urlNameCheap", "Get Cheap List");
        model.addAttribute("urlFast", "/getYourAddress");
        model.addAttribute("urlNameFast", "Get Fast List");
        return "listElement";
    }


    @RequestMapping(value = {"/addElementList"}, method = RequestMethod.GET)
    public String addElementList(Model model) {
        ElementList elementList =new ElementList();
        model.addAttribute("list",elementList);
        List<Product> list=productRepository.findAll();
        System.out.println(list.get(0).getName());
        model.addAttribute("products",list);
        return "addElementList";
    }

    @RequestMapping(value = {"/addElementList"}, method = RequestMethod.POST)
    public String addElementListSave(Model model, //
                          @ModelAttribute("list") @Validated ElementList elementList, //
                          BindingResult result, //
                          final RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "addElementList";
        }
        System.out.println(user.getName());
        System.out.println(shoppingList.getName());

//        elementList.getShoppingList().add(shoppingList);
        shoppingRepository.delete(shoppingList.getId());
        if(shoppingList.getElementList()==null)
        shoppingList.setElementList(new HashSet<>());
        shoppingList.getElementList().add(elementList);
        shoppingRepository.save(shoppingList);
//        elementRepository.save(elementList);

        return "redirect:/lists";
    }

    @RequestMapping(value = {"/getCheap"}, method = RequestMethod.GET)
    public String getCheap(Model model) {
        model.addAttribute("title", "getCheap");
        Logic logic=new Logic();
        List<ElementListWOF> lists =logic.getCheapList(shoppingList.getElementList());
        for (ElementListWOF w:lists
             ) {
            System.out.println(w.getName()+" "+w.getPrize()+" "+w.getCount()+" "+w.getUnit());
        }
        model.addAttribute("lists",lists);
        return "getCheap";
    }

    @RequestMapping(value = {"/getFast"}, method = RequestMethod.GET)
    public String getFast(Model model) throws Exception {
        model.addAttribute("title", "getFast");
        Logic logic=new Logic();
        JSONReader.Point a = JSONReader.getPoint(address.getValue());
        StaticImageService staticImageService = new StaticImageService();
        staticImageService.sendGet(a.lat, a.lng, 300, 300);
        model.addAttribute("picture", "yes");
//        logic.getFast(shoppingList.getElementList());
        model.addAttribute("lists",logic.getFast(shoppingList.getElementList(),address));
        return "getFast";
    }


    @RequestMapping(value = {"/getYourAddress"}, method = RequestMethod.GET)
    public String getYourAddress(Model model) {
        Address address=new Address();
        model.addAttribute("address",address);
     return "getYourAddress";
    }
    @RequestMapping(value = {"/getYourAddress"}, method = RequestMethod.POST)
    public String addElementListSave(Model model, //
                                     @ModelAttribute("address") @Validated Address ad) {
        address=ad;
     return "redirect:/getFast";
    }

}