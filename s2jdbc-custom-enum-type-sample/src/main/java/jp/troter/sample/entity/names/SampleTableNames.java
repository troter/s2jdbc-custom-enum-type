package jp.troter.sample.entity.names;

import javax.annotation.Generated;
import jp.troter.sample.entity.SampleTable;
import jp.troter.sample.enums.ImplementedIntegerCode;
import jp.troter.sample.enums.ImplementedStringCode;
import jp.troter.sample.enums.NormalEnum;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link SampleTable}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.43", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2010/11/27 12:40:36")
public class SampleTableNames {

    /**
     * idのプロパティ名を返します。
     * 
     * @return idのプロパティ名
     */
    public static PropertyName<Long> id() {
        return new PropertyName<Long>("id");
    }

    /**
     * integerCodeのプロパティ名を返します。
     * 
     * @return integerCodeのプロパティ名
     */
    public static PropertyName<ImplementedIntegerCode> integerCode() {
        return new PropertyName<ImplementedIntegerCode>("integerCode");
    }

    /**
     * stringCodeのプロパティ名を返します。
     * 
     * @return stringCodeのプロパティ名
     */
    public static PropertyName<ImplementedStringCode> stringCode() {
        return new PropertyName<ImplementedStringCode>("stringCode");
    }

    /**
     * ordinalCodeのプロパティ名を返します。
     * 
     * @return ordinalCodeのプロパティ名
     */
    public static PropertyName<NormalEnum> ordinalCode() {
        return new PropertyName<NormalEnum>("ordinalCode");
    }

    /**
     * nameCodeのプロパティ名を返します。
     * 
     * @return nameCodeのプロパティ名
     */
    public static PropertyName<NormalEnum> nameCode() {
        return new PropertyName<NormalEnum>("nameCode");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _SampleTableNames extends PropertyName<SampleTable> {

        /**
         * インスタンスを構築します。
         */
        public _SampleTableNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _SampleTableNames(final String name) {
            super(name);
        }

        /**
         * インスタンスを構築します。
         * 
         * @param parent
         *            親
         * @param name
         *            名前
         */
        public _SampleTableNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

        /**
         * idのプロパティ名を返します。
         *
         * @return idのプロパティ名
         */
        public PropertyName<Long> id() {
            return new PropertyName<Long>(this, "id");
        }

        /**
         * integerCodeのプロパティ名を返します。
         *
         * @return integerCodeのプロパティ名
         */
        public PropertyName<ImplementedIntegerCode> integerCode() {
            return new PropertyName<ImplementedIntegerCode>(this, "integerCode");
        }

        /**
         * stringCodeのプロパティ名を返します。
         *
         * @return stringCodeのプロパティ名
         */
        public PropertyName<ImplementedStringCode> stringCode() {
            return new PropertyName<ImplementedStringCode>(this, "stringCode");
        }

        /**
         * ordinalCodeのプロパティ名を返します。
         *
         * @return ordinalCodeのプロパティ名
         */
        public PropertyName<NormalEnum> ordinalCode() {
            return new PropertyName<NormalEnum>(this, "ordinalCode");
        }

        /**
         * nameCodeのプロパティ名を返します。
         *
         * @return nameCodeのプロパティ名
         */
        public PropertyName<NormalEnum> nameCode() {
            return new PropertyName<NormalEnum>(this, "nameCode");
        }
    }
}