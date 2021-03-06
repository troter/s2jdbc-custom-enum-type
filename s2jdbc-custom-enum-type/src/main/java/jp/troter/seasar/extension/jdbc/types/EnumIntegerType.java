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

public class EnumIntegerType<T extends Enum<T>> extends AbstractValueType {

    private final Class<T> enumClass;

    /**
     * インスタンスを構築します。
     * 
     * @param enumClass
     */
    public EnumIntegerType(Class<T> enumClass) {
        super(Types.INTEGER);
        this.enumClass = enumClass;
        if (IntegerCode.class.isAssignableFrom(enumClass)) {
            IntegerCode.Helper.assertFactoryMethod(enumClass);
        }
    }

    @Override
    public Object getValue(ResultSet resultSet, int index) throws SQLException {
        final int ordinal = resultSet.getInt(index);
        if (ordinal == 0 && resultSet.wasNull()) {
            return null;
        }
        return toEnum(ordinal);
    }

    @Override
    public Object getValue(ResultSet resultSet, String columnName)
            throws SQLException {
        final int ordinal = resultSet.getInt(columnName);
        if (ordinal == 0 && resultSet.wasNull()) {
            return null;
        }
        return toEnum(ordinal);
    }

    @Override
    public Object getValue(CallableStatement cs, int index) throws SQLException {
        final int ordinal = cs.getInt(index);
        if (ordinal == 0 && cs.wasNull()) {
            return null;
        }
        return toEnum(ordinal);
    }

    @Override
    public Object getValue(CallableStatement cs, String parameterName)
            throws SQLException {
        final int ordinal = cs.getInt(parameterName);
        if (ordinal == 0 && cs.wasNull()) {
            return null;
        }
        return toEnum(ordinal);
    }

    @Override
    public void bindValue(PreparedStatement ps, int index, Object value)
            throws SQLException {
        if (value == null) {
            setNull(ps, index);
        } else {
            ps.setInt(index, fromEnum(value));
        }
    }

    @Override
    public void bindValue(CallableStatement cs, String parameterName,
            Object value) throws SQLException {
        if (value == null) {
            setNull(cs, parameterName);
        } else {
            cs.setInt(parameterName, fromEnum(value));
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
     * {@link Enum}に変換します。
     * 
     * @param ordinal
     *            序数
     * @return {@link Enum}
     */
    protected T toEnum(int ordinal) {
        if (IntegerCode.class.isAssignableFrom(enumClass)) {
            return IntegerCode.Helper.of(enumClass, ordinal);
        }
        return enumClass.getEnumConstants()[ordinal];
    }

    /**
     * {@link Enum}から整数に変換します。
     * @param value
     * @return 整数
     */
    protected int fromEnum(Object value) {
        if (value == null) { return 0; }
        
        if (IntegerCode.class.isAssignableFrom(enumClass)) {
            return IntegerCode.Helper.getCode(enumClass, value);
        }
        return (Enum.class.cast(value)).ordinal();
    }
}
