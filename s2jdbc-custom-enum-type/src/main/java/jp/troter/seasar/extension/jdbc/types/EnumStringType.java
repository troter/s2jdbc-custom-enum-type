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

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.seasar.extension.jdbc.types.AbstractValueType;
import org.seasar.extension.jdbc.util.BindVariableUtil;

public class EnumStringType<T extends Enum<T>> extends AbstractValueType {

    private final Class<T> enumClass;

	/**
     * インスタンスを構築します。
     * 
     * @param enumClass
     */
    public EnumStringType(Class<T> enumClass) {
        super(Types.VARCHAR);
        this.enumClass = enumClass;
        if (StringCode.class.isAssignableFrom(enumClass)) {
            StringCode.Helper.assertFactoryMethod(enumClass);
        }
    }

    @Override
    public Object getValue(ResultSet resultSet, int index) throws SQLException {
        return toEnum(resultSet.getString(index));
    }

    /**
     * {@link Enum}に変換します。
     * 
     * @param name
     * @return {@link Enum}
     */
    protected T toEnum(String name) {
        if (name == null) {
            return null;
        }
        if (StringCode.class.isAssignableFrom(enumClass)) {
            return StringCode.Helper.of(enumClass, name);
        }
        return Enum.valueOf(enumClass, name);
    }

    @Override
    public Object getValue(ResultSet resultSet, String columnName)
            throws SQLException {

        return toEnum(resultSet.getString(columnName));
    }

    @Override
    public Object getValue(CallableStatement cs, int index) throws SQLException {
        return toEnum(cs.getString(index));
    }

    @Override
    public Object getValue(CallableStatement cs, String parameterName)
            throws SQLException {

        return toEnum(cs.getString(parameterName));
    }

    @Override
    public void bindValue(PreparedStatement ps, int index, Object value)
            throws SQLException {

        if (value == null) {
            setNull(ps, index);
        } else {
            ps.setString(index, fromEnum(value));
        }
    }

    @Override
    public void bindValue(CallableStatement cs, String parameterName,
            Object value) throws SQLException {

        if (value == null) {
            setNull(cs, parameterName);
        } else{
            cs.setString(parameterName, fromEnum(value));
        }
    }

    @Override
    public String toText(Object value) {
        if (value == null) {
            return BindVariableUtil.nullText();
        }
        return BindVariableUtil.toText(fromEnum(value));
    }
    
    /**
     * {@link Enum}を文字列表現に変換します。
     * @param value {@link Enum}
     * @return 文字列
     */
    protected String fromEnum(Object value) {
        if (StringCode.class.isAssignableFrom(enumClass)) {
            return StringCode.Helper.getCode(enumClass, value);
        }
        return (Enum.class.cast(value)).name();
    }
   
}
