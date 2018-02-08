package com.nablarch.example.app.entity.core.validation.validator;

import com.nablarch.example.app.web.common.code.ProjectClass;
import com.nablarch.example.app.web.common.code.ProjectType;

import nablarch.core.validation.ee.Digits;
import nablarch.core.validation.ee.Length;
import nablarch.core.validation.ee.NumberRange;
import nablarch.core.validation.ee.SystemChar;


/**
 * ドメイン定義を表す列挙型。
 *
 * @author Nabu Rakutaro
 */
@SuppressWarnings("all")
public class ExampleDomainType {

    /** ID */
    @Digits(integer = 9)
    private String id;

    /** ログインID */
    @Length(max = 20)
    @SystemChar(charsetDef = "半角数字")
    private String loginId;

    /** パスワード */
    private String password;

    /** 顧客名 */
    @Length(max = 64, message = "{domainType.clientName.message}")
    @SystemChar(charsetDef = "全角文字", message = "{domainType.clientName.message}")
    private String clientName;

    /** 顧客の業種を表すコード */
    @Length(min = 2, max = 2, message = "{domainType.industryCode.message}")
    @SystemChar(charsetDef = "半角数字", message = "{domainType.industryCode.message}")
    private String industryCode;

    /** プロジェクト名 */
    @Length(max = 64, message = "{domainType.projectName.message}")
    @SystemChar(charsetDef = "全角文字", message = "{domainType.projectName.message}")
    private String projectName;

    /** 新規開発PJ、または保守PJを表すコード値 */
    @CodeValue(enumClass = ProjectType.class)
    private String projectType;

    /** プロジェクトの規模を表すコード値 */
    @CodeValue(enumClass = ProjectClass.class)
    private String projectClass;

    /** 日付 */
    @YYYYMMDD()
    private String date;

    /** ユーザ氏名（漢字） */
    @Length(max = 64, message = "{domainType.userName.message}")
    @SystemChar(charsetDef = "全角文字", message = "{domainType.userName.message}")
    private String userName;

    /** 備考 */
    @Length(max = 64, message = "{domainType.note.message}")
    @SystemChar(charsetDef = "システム許容文字", allowLineSeparator = true, message = "{domainType.note.message}")
    private String note;

    /** 金額 */
    @MoneyRange(max = 999999999)
    private String amountOfMoney;

    /** ページ番号 */
    @NumberRange(min = 1, max = 9999)
    private String pageNumber;
}
