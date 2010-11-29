package jp.troter.seasar.extension.jdbc.types;

import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.MethodNotFoundRuntimeException;
import org.seasar.framework.beans.factory.BeanDescFactory;

public interface StringCode {

    public static final String FACTORY_METHOD_NAME = "codeOf";
    public static final String PROPERTY_CODE = "code";

    /**
     * @return コード
     */
    String getCode();

    /**
     * ヘルパー
     */
    public static class Helper {

        public static void assertFactoryMethod(final Class<? extends Enum> enumClass) {
            try {
                BeanDesc b = BeanDescFactory.getBeanDesc(enumClass);
                b.getMethod(FACTORY_METHOD_NAME, new Class[]{String.class});
            } catch (MethodNotFoundRuntimeException e) {
                String m = String.format("[%s]を継承したクラス[%s]はスタティックメソッド[%s]を実装する必要があります。", StringCode.class.getName(), enumClass.getName(), FACTORY_METHOD_NAME);
                throw new IllegalArgumentException(m, e);
            }
        }

        public static Enum codeOf(final Class<? extends Enum> enumClass, String code) {
            BeanDesc b = BeanDescFactory.getBeanDesc(enumClass);
            return (Enum)b.invoke(null, FACTORY_METHOD_NAME, new Object[]{code});
        }

        public static String getCode(final Class<? extends Enum> enumClass, Object value) {
            BeanDesc b = BeanDescFactory.getBeanDesc(enumClass);
            return (String)b.getPropertyDesc(PROPERTY_CODE).getValue(value);
        }
    }
}
