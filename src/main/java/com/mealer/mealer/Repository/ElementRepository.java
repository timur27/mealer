package com.mealer.mealer.Repository;

import com.mealer.mealer.Model.ElementList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElementRepository extends JpaRepository<ElementList,Integer> {

}
