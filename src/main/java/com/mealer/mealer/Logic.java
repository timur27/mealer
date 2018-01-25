package com.mealer.mealer;

import com.mealer.mealer.Model.*;
import com.mealer.mealer.Repository.ElementRepository;
import com.mealer.mealer.Repository.ProductRepository;
import com.mealer.mealer.Repository.ShopRepository;
import com.mealer.mealer.Repository.ShoppingRepository;
import com.mealer.mealer.Service.Distance;
import com.mealer.mealer.Service.JSONReader;
import com.mealer.mealer.Service.TypeUsersService;
import com.mealer.mealer.config.ApplicationContextHolder;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Logic {

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
    @Autowired
    private ShopRepository shopRepository;

    public List<ElementListWOF> getCheapList(Set<ElementList> elementListList) {
        shopRepository= ApplicationContextHolder.getContext().getBean(ShopRepository.class);
        productRepository=ApplicationContextHolder.getContext().getBean(ProductRepository.class);
        List<ElementListWOF> lists=new ArrayList<>();
            ElementListWOF elementListWOF;
        List<Shop> list = shopRepository.findAll();
        double theBestPrize=100;
        Shop shop = null;
        double prize;
        for (Shop s : list) {
            prize=0;
            for (ElementList e : elementListList) {
                System.out.println(e.getName());
                for (ProductHasShop p:productRepository
                                .findByName(e.getName())
                                .getProductHasShops()){
                    if(p.getShop().getId()==s.getId()){
                        System.out.println(p.getPrize());
                        prize+=p.getPrize();
                    }
                }
            }
            if(theBestPrize>prize){
                theBestPrize=prize;
                shop=s;
            }
            System.out.println(prize);
            System.out.println("=================");
        }

        for (ElementList e : elementListList) {
            System.out.println(e.getName());
            for (ProductHasShop p:productRepository
                    .findByName(e.getName())
                    .getProductHasShops()){
                if(p.getShop().getId()==shop.getId()){
                    elementListWOF=new ElementListWOF();
                    elementListWOF.setPrize(p.getPrize());
                    elementListWOF.setName(e.getName());
                    elementListWOF.setCount(p.getProduct().getCount());
                    elementListWOF.setUnit(p.getProduct().getUnit());
                    lists.add(elementListWOF);
                }
            }
        }

        System.out.println(shop.getAddress()+"  "+theBestPrize);
        return lists;
    }

    public List<ElementListWOF> getFast(Set<ElementList> elementListList, Address addressMain) throws IOException {
        shopRepository= ApplicationContextHolder.getContext().getBean(ShopRepository.class);
        productRepository=ApplicationContextHolder.getContext().getBean(ProductRepository.class);
        Distance distance=new Distance();
        List<List<String>> list = distance.beforeDistances(addressMain.getValue());
        System.out.println("=======================");
        double dis=1000.0;
        String address = null;
        for (List<String> l:list
             ) {
            if(dis>Double.parseDouble(l.get(1).split(" ")[0].replaceAll(" ","."))){
                address=l.get(0);
            }
//            System.out.println(l.get(1).split(" ")[0]);
//            System.out.println( Double.parseDouble(l.get(1).split(" ")[0].replaceAll(" ",".")));
//            for (String s:l
//                 ) {
//                System.out.println(s);
//            }
        }
        System.out.println(address);
        Shop shop=shopRepository.findByAddress(address);
        List<ElementListWOF> lists=new ArrayList<>();
        ElementListWOF elementListWOF;
        for (ElementList e : elementListList) {

            for (ProductHasShop p:productRepository
                    .findByName(e.getName())
                    .getProductHasShops()){
                if(p.getShop().getId()==shop.getId()){
                    elementListWOF=new ElementListWOF();
                    elementListWOF.setName(e.getName());
                    elementListWOF.setPrize(p.getPrize());
                    elementListWOF.setCount(p.getProduct().getCount());
                    elementListWOF.setUnit(p.getProduct().getUnit());
                    lists.add(elementListWOF);
                }
            }
        }
        return lists;
    }
}
