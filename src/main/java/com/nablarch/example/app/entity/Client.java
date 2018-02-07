package com.nablarch.example.app.entity;

import java.io.Serializable;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

/**
 * 顧客
 *
 */
@Entity
@Table(schema = "PUBLIC", name = "CLIENT")
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 顧客ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CLIENT_ID")
    private Integer clientId;

    /** 顧客名 */
    @Column(name = "CLIENT_NAME")
    private String clientName;

    /** 業種コード */
    @Column(name = "INDUSTRY_CODE")
    private String industryCode;

    /**
     * 顧客IDを返します。
     *
     * @return 顧客ID
     */
    public Integer getClientId() {
        return clientId;
    }

    /**
     * 顧客IDを設定します。
     *
     * @param clientId 顧客ID
     */
    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    /**
     * 顧客名を返します。
     *
     * @return 顧客名
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * 顧客名を設定します。
     *
     * @param clientName 顧客名
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    /**
     * 業種コードを返します。
     *
     * @return 業種コード
     */
    public String getIndustryCode() {
        return industryCode;
    }

    /**
     * 業種コードを設定します。
     *
     * @param industryCode 業種コード
     */
    public void setIndustryCode(String industryCode) {
        this.industryCode = industryCode;
    }
}
