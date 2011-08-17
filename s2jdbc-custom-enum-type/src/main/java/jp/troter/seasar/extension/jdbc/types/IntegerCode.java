/*
 * Copyright 2011 Takumi IINO
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.troter.seasar.extension.jdbc.types;

import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.MethodNotFoundRuntimeException;
import org.seasar.framework.beans.factory.BeanDescFactory;

public interface IntegerCode {

    public static final String FACTORY_METHOD_NAME = "codeOf";
    public static final String PROPERTY_CODE = "code";

    /**
     * @return コード
     */
    int getCode();

    /**
     * ヘルパー
     */
    public static class Helper {

        public static void assertFactoryMethod(final Class<? extends Enum> enumClass) {
            try {
                BeanDesc b = BeanDescFactory.getBeanDesc(enumClass);
                b.getMethod(FACTORY_METHOD_NAME, new Class[]{int.class});
            } catch (MethodNotFoundRuntimeException e) {
                String m = String.format("[%s]を継承したクラス[%s]はスタティックメソッド[%s]を実装する必要があります。", StringCode.class.getName(), enumClass.getName(), FACTORY_METHOD_NAME);
                throw new IllegalArgumentException(m, e);
            }
        }

        public static Enum codeOf(final Class<? extends Enum> enumClass, int code) {
            BeanDesc b = BeanDescFactory.getBeanDesc(enumClass);
            return (Enum)b.invoke(null, FACTORY_METHOD_NAME, new Object[]{code});
        }

        public static int getCode(final Class<? extends Enum> enumClass, Object value) {
            BeanDesc b = BeanDescFactory.getBeanDesc(enumClass);
            return (Integer)b.getPropertyDesc(PROPERTY_CODE).getValue(value);
        }
    }

}
