package com.nablarch.example.app.web.dao;

import java.util.Optional;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;

import com.nablarch.example.app.entity.Client;

import nablarch.integration.doma.DomaConfig;

@Dao(config = DomaConfig.class)
public interface ClientDao {

    @Select
    Optional<Client> selectById(Integer clientId);

    default boolean existsById(Integer clientId) {
        return selectById(clientId).isPresent();
    }
}
