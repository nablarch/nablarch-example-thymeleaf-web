package com.nablarch.example.app.web.form;

import static org.junit.jupiter.api.Assertions.assertEquals;

import nablarch.core.beans.BeanUtil;
import nablarch.core.util.DateUtil;

import org.junit.jupiter.api.Test;

import com.nablarch.example.app.entity.Project;

/**
 * {@link ProjectForm}のテスト。
 */
public class ProjectFormTest {

    /**
     * 登録確認画面から入力画面へ戻る際({@code ProjectAction#backToNew})の詰め替えを想定し、
     * {@link Project}の日付型項目が{@link ProjectForm}の文字列項目へ{@code yyyyMMdd}形式で移送されることを検証する。
     */
    @Test
    public void プロジェクトエンティティの日付項目がyyyyMMdd形式の文字列としてフォームに移送されること() {
        Project project = new Project();
        project.setProjectName("プロジェクト名");
        project.setProjectStartDate(DateUtil.getDate("20240101"));
        project.setProjectEndDate(DateUtil.getDate("20241231"));

        ProjectForm form = BeanUtil.createAndCopy(ProjectForm.class, project);

        assertEquals("プロジェクト名", form.getProjectName());
        assertEquals("20240101", form.getProjectStartDate());
        assertEquals("20241231", form.getProjectEndDate());
    }

    /**
     * 入力画面から登録確認画面へ進む際({@code ProjectAction#confirmOfCreate})の詰め替えを想定し、
     * {@link ProjectForm}の{@code yyyyMMdd}形式の文字列項目が{@link Project}の日付型項目へ移送されることを検証する。
     */
    @Test
    public void フォームのyyyyMMdd形式の日付文字列がプロジェクトエンティティの日付項目に移送されること() {
        ProjectForm form = new ProjectForm();
        form.setProjectName("プロジェクト名");
        form.setProjectStartDate("20240101");
        form.setProjectEndDate("20241231");

        Project project = BeanUtil.createAndCopy(Project.class, form);

        assertEquals("プロジェクト名", project.getProjectName());
        assertEquals(DateUtil.getDate("20240101"), project.getProjectStartDate());
        assertEquals(DateUtil.getDate("20241231"), project.getProjectEndDate());
    }
}
