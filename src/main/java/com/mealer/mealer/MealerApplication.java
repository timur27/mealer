package com.mealer.mealer;


import com.mealer.mealer.Repository.UsersRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MealerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MealerApplication.class, args);
    }



//    	@Bean
//	CommandLineRunner runner(UsersRepo repo){
//           TypeUser u= new TypeUser("ADMIN");
//            Users us=new Users("ala","ma","kota",u);
//            u.setUsers(new HashSet<Users>(){{
//                add(us);
//            }});
//            TypeUser u2= new TypeUser("ADMIN");
//            Users us2=new Users("Mala","ma","kota",u2);
//            u2.setUsers(new HashSet<Users>(){{
//                add(us2);
//            }});
//		return args -> {
//			repo.save(new HashSet<TypeUser>(){{
//			    add(u);
//			    add(u2);
//            }});
//		};
//	}

//    @Bean
//    CommandLineRunner runner(UsersRepo repo) {
//        return args -> {
//            repo.save(new TypeUser("ADMIN", new Users("ala", "ma", "kota")));
//            repo.save(new Users("alala", "m22a", "kota", new TypeUser("ADMIN")));


//        };
//    }
}