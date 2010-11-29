package jp.troter.sample.entity.names;

import javax.annotation.Generated;
import jp.troter.sample.entity.SampleTable;
import jp.troter.sample.entity.names.SampleTableNames._SampleTableNames;

/**
 * 名前クラスの集約です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.43", "org.seasar.extension.jdbc.gen.internal.model.NamesAggregateModelFactoryImpl"}, date = "2010/11/27 12:40:36")
public class Names {

    /**
     * {@link SampleTable}の名前クラスを返します。
     * 
     * @return SampleTableの名前クラス
     */
    public static _SampleTableNames sampleTable() {
        return new _SampleTableNames();
    }
}