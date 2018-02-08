package com.nablarch.example.app.web.dao;

import java.util.Optional;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;

import com.nablarch.example.app.entity.Client;

import nablarch.integration.doma.DomaConfig;

/**
 * 顧客のDAO。
 * 
 * @author Taichi Uragami
 *
 */
@Dao(config = DomaConfig.class)
public interface ClientDao {

    /**
     * 主キーで検索する。
     * 
     * @param clientId 顧客ID
     * @return 検索結果
     */
    @Select
    Optional<Client> selectById(Integer clientId);

    /**
     * 主キーで検索して存在するかどうかを返す。
     * 
     * @param clientId 顧客ID
     * @return 存在すれば{@code true}
     */
    default boolean existsById(Integer clientId) {
        return selectById(clientId).isPresent();
    }
}
