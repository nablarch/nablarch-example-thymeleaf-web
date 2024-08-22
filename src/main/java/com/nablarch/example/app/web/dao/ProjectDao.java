package com.nablarch.example.app.web.dao;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;

import com.nablarch.example.app.entity.Project;

import nablarch.integration.doma.DomaConfig;

/**
 * プロジェクトのDAO。
 * 
 * @author Taichi Uragami
 *
 */
@Dao
public interface ProjectDao {

    /**
     * 登録する。
     * 
     * @param project プロジェクト
     * @return 登録件数
     */
    @Insert
    int insert(Project project);
}
