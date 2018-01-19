package com.mealer.mealer;


import com.mealer.mealer.Model.Shop;
import com.mealer.mealer.Model.ShoppingList;
import com.mealer.mealer.Model.TypeUser;
import com.mealer.mealer.Repository.ShopRepository;
import com.mealer.mealer.Repository.ShoppingRepository;
import com.mealer.mealer.Repository.TypeUsersRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MealerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MealerApplication.class, args);
    }


    @Bean
    CommandLineRunner runner(ShoppingRepository shoppingRepository, TypeUsersRepository typeUsersRepository, ShopRepository shopRepository){
        return args -> {
            TypeUser timur = new TypeUser("Timur");
            TypeUser anastasia = new TypeUser("Anastasia");
            TypeUser mateusz = new TypeUser("Mateusz");
            TypeUser michal = new TypeUser("Michal");
            TypeUser artur = new TypeUser("Artur");
            TypeUser marlen = new TypeUser("Marlen");

            Shop a = new Shop("Kaufland", "Norymberska 1");
            shopRepository.save(a);
            shopRepository.save(new Shop("Kaufland", "Bratysławska 4"));
            typeUsersRepository.save(timur);
            typeUsersRepository.save(anastasia);
            typeUsersRepository.save(mateusz);
            typeUsersRepository.save(michal);
            typeUsersRepository.save(artur);
            typeUsersRepository.save(marlen);

            shoppingRepository.save(new ShoppingList("First", timur));
            shoppingRepository.save(new ShoppingList("Second",artur));
            shoppingRepository.save(new ShoppingList("Third", anastasia));
        };
    }

}