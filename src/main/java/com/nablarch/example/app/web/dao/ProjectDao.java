package com.nablarch.example.app.web.dao;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;

import com.nablarch.example.app.entity.Project;

import nablarch.integration.doma.DomaConfig;

@Dao(config = DomaConfig.class)
public interface ProjectDao {

    @Insert
    int insert(Project project);
}
