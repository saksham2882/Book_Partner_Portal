package com.cg.repository;

import com.cg.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStoreRepo extends JpaRepository<Store, String> {
}
