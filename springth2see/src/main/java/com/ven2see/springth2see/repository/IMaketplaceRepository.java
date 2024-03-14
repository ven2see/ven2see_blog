package com.ven2see.springth2see.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ven2see.springth2see.model.Marketplace;

@Repository
public interface IMaketplaceRepository extends JpaRepository<Marketplace,Integer> {

}
