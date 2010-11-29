package jp.troter.seasar.extension.jdbc.types;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.seasar.extension.jdbc.types.AbstractValueType;
import org.seasar.extension.jdbc.util.BindVariableUtil;

public class EnumIntegerType extends AbstractValueType {

    @SuppressWarnings("unchecked")
    private final Class<? extends Enum> enumClass;

    /**
     * インスタンスを構築します。
     * 
     * @param enumClass
     */
    @SuppressWarnings("unchecked")
    public EnumIntegerType(Class<? extends Enum> enumClass) {
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
    @SuppressWarnings("unchecked")
    public void bindValue(PreparedStatement ps, int index, Object value)
            throws SQLException {
        if (value == null) {
            setNull(ps, index);
        } else {
            ps.setInt(index, fromEnum(value));
        }
    }

    @Override
    @SuppressWarnings("unchecked")
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
    @SuppressWarnings("unchecked")
    protected Enum toEnum(int ordinal) {
        if (IntegerCode.class.isAssignableFrom(enumClass)) {
            return IntegerCode.Helper.codeOf(enumClass, ordinal);
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
