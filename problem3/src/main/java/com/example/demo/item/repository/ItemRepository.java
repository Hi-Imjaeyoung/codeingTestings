package com.example.demo.item.repository;

import com.example.demo.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {
    @Lock(LockModeType.PESSIMISTIC_READ)
    Optional<Item> findById(Long id);
}
