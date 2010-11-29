package jp.troter.sample.service;

import static jp.troter.sample.entity.names.SampleTableNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

import java.util.List;

import javax.annotation.Generated;

import jp.troter.sample.entity.SampleTable;

/**
 * {@link SampleTable}のサービスクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.43", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2010/11/27 2:50:40")
public class SampleTableService extends AbstractService<SampleTable> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param id
     *            識別子
     * @return エンティティ
     */
    public SampleTable findById(Long id) {
        return select().id(id).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<SampleTable> findAllOrderById() {
        return select().orderBy(asc(id())).getResultList();
    }
}