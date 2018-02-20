package com.nablarch.example.app.web.action;

import com.nablarch.example.app.entity.Client;
import com.nablarch.example.app.entity.Project;
import com.nablarch.example.app.web.dao.ClientDao;
import com.nablarch.example.app.web.dao.ProjectDao;
import com.nablarch.example.app.web.form.ProjectForm;

import nablarch.common.web.interceptor.InjectForm;
import nablarch.common.web.session.SessionUtil;
import nablarch.common.web.token.OnDoubleSubmission;
import nablarch.common.web.token.UseToken;
import nablarch.core.beans.BeanUtil;
import nablarch.core.message.ApplicationException;
import nablarch.core.message.MessageLevel;
import nablarch.core.message.MessageUtil;
import nablarch.fw.ExecutionContext;
import nablarch.fw.web.HttpRequest;
import nablarch.fw.web.HttpResponse;
import nablarch.fw.web.interceptor.OnError;
import nablarch.integration.doma.DomaDaoRepository;
import nablarch.integration.doma.Transactional;

/**
 * プロジェクト検索、登録、更新、削除機能 。
 *
 * @author Nabu Rakutaro
 */
public class ProjectAction {

    /**
     * プロジェクト登録初期画面を表示。
     *
     * @param request HTTPリクエスト
     * @param context 実行コンテキスト
     * @return HTTPレスポンス
     */
    public HttpResponse index(HttpRequest request, ExecutionContext context) {
        SessionUtil.delete(context, "project");
        context.setRequestScopedVar("projectForm", new ProjectForm());
        return new HttpResponse("project/index.html");
    }

    /**
     * 登録情報確認画面を表示。
     *
     * @param request HTTPリクエスト
     * @param context 実行コンテキスト
     * @return HTTPレスポンス
     */
    @Transactional
    @InjectForm(form = ProjectForm.class, name = "projectForm")
    @OnError(type = ApplicationException.class, path = "project/index.html")
    @UseToken
    public HttpResponse confirmOfCreate(HttpRequest request, ExecutionContext context) {
        final ProjectForm form = context.getRequestScopedVar("projectForm");
        if (form.hasClientId()) {
            final ClientDao clientDao = DomaDaoRepository.get(ClientDao.class);
            final Integer clientId = Integer.parseInt(form.getClientId());
            if (!clientDao.existsById(clientId)) {
                throw new ApplicationException(
                        MessageUtil.createMessage(MessageLevel.ERROR, "errors.nothing.client",
                                Client.class.getSimpleName(),
                                form.getClientId()));
            }
        }
        final Project project = BeanUtil.createAndCopy(Project.class, form);
        SessionUtil.put(context, "project", project);
        final ProjectProfit projectProfit = new ProjectProfit(
                project.getSales(),
                project.getCostOfGoodsSold(),
                project.getSga(),
                project.getAllocationOfCorpExpenses());
        context.setRequestScopedVar("profit", projectProfit);
        return new HttpResponse("project/confirmOfCreate.html");
    }

    /**
     * 登録処理。
     *
     * @param request HTTPリクエスト
     * @param context 実行コンテキスト
     * @return HTTPレスポンス
     */
    @Transactional
    @OnDoubleSubmission
    public HttpResponse create(HttpRequest request, ExecutionContext context) {
        final Project project = SessionUtil.get(context, "project");
        final ProjectDao projectDao = DomaDaoRepository.get(ProjectDao.class);
        projectDao.insert(project);
        return new HttpResponse(303, "redirect://completeOfCreate");
    }

    /**
     * 登録完了画面を表示。
     *
     * @param request HTTPリクエスト
     * @param context 実行コンテキスト
     * @return HTTPレスポンス
     */
    public HttpResponse completeOfCreate(HttpRequest request, ExecutionContext context) {
        return new HttpResponse("project/completeOfCreate.html");
    }

    /**
     * 登録情報入力画面へ戻る。
     *
     * @param request HTTPリクエスト
     * @param context 実行コンテキスト
     * @return HTTPレスポンス
     */
    public HttpResponse backToNew(HttpRequest request, ExecutionContext context) {
        final Project project = SessionUtil.get(context, "project");
        final ProjectForm form = BeanUtil.createAndCopy(ProjectForm.class, project);
        context.setRequestScopedVar("projectForm", form);
        return new HttpResponse("project/index.html");
    }
}
