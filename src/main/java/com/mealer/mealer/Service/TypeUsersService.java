package com.mealer.mealer.Service;


import com.mealer.mealer.Model.TypeUser;
import com.mealer.mealer.Repository.TypeUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeUsersService {
    @Autowired
    protected TypeUsersRepository typeUsersRepository;

    public TypeUser getUser(String name){
        return typeUsersRepository.findByName(name);
    }

    public List<TypeUser> getAllUsers(){
        return typeUsersRepository.findAll();
    }

    public void save(TypeUser typeUser){
        typeUsersRepository.save(typeUser);
    }
}
