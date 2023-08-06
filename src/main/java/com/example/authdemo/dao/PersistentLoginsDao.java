package com.example.authdemo.dao;

import com.example.authdemo.model.PersistentLogins;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersistentLoginsDao extends JpaRepository<PersistentLogins, String> {
}
