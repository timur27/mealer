package com.mealer.mealer;


import com.mealer.mealer.Model.*;
import com.mealer.mealer.Repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;

@SpringBootApplication
public class MealerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MealerApplication.class, args);
    }


    @Bean
    CommandLineRunner runner(ShoppingRepository shoppingRepository, TypeUsersRepository typeUsersRepository, ShopRepository shopRepository, ElementRepository elementRepository, ProductRepository productRepository){
        return args -> {
            TypeUser timur = new TypeUser("Timur");
            TypeUser anastasia = new TypeUser("Anastasia");
            TypeUser mateusz = new TypeUser("Mateusz");
            TypeUser michal = new TypeUser("Michal");
            TypeUser artur = new TypeUser("Artur");
            TypeUser marlen = new TypeUser("Marlen");

            Shop a = new Shop("Kaufland", "Norymberska 1");
            Shop b = new Shop("Kaufland", "Bratysławska 4");
            shopRepository.save(a);
            shopRepository.save(b);
            typeUsersRepository.save(timur);
            typeUsersRepository.save(anastasia);
            typeUsersRepository.save(mateusz);
            typeUsersRepository.save(michal);
            typeUsersRepository.save(artur);
            typeUsersRepository.save(marlen);

            ShoppingList shoppingList=new ShoppingList("First", timur,new HashSet<>());
            ElementList elementList=new ElementList("Mleko",new HashSet<>());
//            elementList.getShoppingList().add(shoppingList);
//            elementRepository.save(elementList);
            shoppingList.getElementList().add(elementList);
            shoppingRepository.save(shoppingList);
            shoppingRepository.save(new ShoppingList("Second",artur));
            shoppingRepository.save(new ShoppingList("Third", anastasia));

            Product product1=new Product("Ser Żółty",0.5,"Kg");
            Product product2=new Product("Mleko",1.0,"Litr");
            Product product3=new Product("Chleb",1.0,"Sztuk");
            Product product4=new Product("Banany",1.0,"Kg");
            Product product5=new Product("Makaron",0.5,"Kg");
            Product product6=new Product("Filet z Kurczaka <3",1.0,"Kg");

            ProductHasShop productHasShop;
            productHasShop=new ProductHasShop();
            productHasShop.setPrize(2.5);
            productHasShop.setShop(b);
            productHasShop.setProduct(product1);
            product1.setProductHasShops(new HashSet<>());
            product1.getProductHasShops().add(productHasShop);
            productHasShop=new ProductHasShop();
            productHasShop.setPrize(2.8);
            productHasShop.setShop(a);
            productHasShop.setProduct(product1);
            product1.getProductHasShops().add(productHasShop);
//===========================
            productHasShop=new ProductHasShop();
            productHasShop.setPrize(2.0);
            productHasShop.setShop(b);
            productHasShop.setProduct(product2);
            product2.setProductHasShops(new HashSet<>());
            product2.getProductHasShops().add(productHasShop);
            productHasShop=new ProductHasShop();
            productHasShop.setPrize(2.3);
            productHasShop.setShop(a);
            productHasShop.setProduct(product2);
            product2.getProductHasShops().add(productHasShop);
            //===========================
            productHasShop=new ProductHasShop();
            productHasShop.setPrize(2.0);
            productHasShop.setShop(b);
            productHasShop.setProduct(product3);
            product3.setProductHasShops(new HashSet<>());
            product3.getProductHasShops().add(productHasShop);
            productHasShop=new ProductHasShop();
            productHasShop.setPrize(3.5);
            productHasShop.setShop(a);
            productHasShop.setProduct(product3);
            product3.getProductHasShops().add(productHasShop);
            //===========================
            productHasShop=new ProductHasShop();
            productHasShop.setPrize(3.0);
            productHasShop.setShop(b);
            productHasShop.setProduct(product4);
            product4.setProductHasShops(new HashSet<>());
            product4.getProductHasShops().add(productHasShop);
            productHasShop=new ProductHasShop();
            productHasShop.setPrize(4.5);
            productHasShop.setShop(a);
            productHasShop.setProduct(product4);
            product4.getProductHasShops().add(productHasShop);
            //===========================
            productHasShop=new ProductHasShop();
            productHasShop.setPrize(5.0);
            productHasShop.setShop(b);
            productHasShop.setProduct(product5);
            product5.setProductHasShops(new HashSet<>());
            product5.getProductHasShops().add(productHasShop);
            productHasShop=new ProductHasShop();
            productHasShop.setPrize(4.0);
            productHasShop.setShop(a);
            productHasShop.setProduct(product5);
            product5.getProductHasShops().add(productHasShop);
            //===========================
            productHasShop=new ProductHasShop();
            productHasShop.setPrize(13.0);
            productHasShop.setShop(b);
            productHasShop.setProduct(product6);
            product6.setProductHasShops(new HashSet<>());
            product6.getProductHasShops().add(productHasShop);
            productHasShop=new ProductHasShop();
            productHasShop.setPrize(14.00);
            productHasShop.setShop(a);
            productHasShop.setProduct(product6);
            product6.getProductHasShops().add(productHasShop);
            //===========================
            productRepository.save(product1);
            productRepository.save(product2);
            productRepository.save(product3);
            productRepository.save(product4);
            productRepository.save(product5);
            productRepository.save(product6);
            productRepository.save(new Product("Bannany",1.0,"Kg"));
            productRepository.save(new Product("Krakowska",0.2,"Kg"));

//            elementList.getShoppingList().add(shoppingList);
//            shoppingList.getElementList().add(elementList);
//            elementRepository.save(elementList);
        };
    }

}