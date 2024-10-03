package com.LiveasyLogistics.Load.Repository;

import com.LiveasyLogistics.Load.Entity.Load;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LoadRepositoy extends JpaRepository<Load,Integer> {

    List<Load> findByShipperId(@Param("shipperId") String shipperId);

}
