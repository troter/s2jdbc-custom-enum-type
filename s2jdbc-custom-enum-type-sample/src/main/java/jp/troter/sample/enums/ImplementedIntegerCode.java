package jp.troter.sample.enums;

import jp.troter.seasar.extension.jdbc.types.IntegerCode;

public enum ImplementedIntegerCode implements IntegerCode {
    FIRST(100),
    SECOND(-100),
    ;
    
    private final int code;

    ImplementedIntegerCode(int code) {
        this.code = code;
    }


    @Override
    public int getCode() {
        return code;
    }

    public static ImplementedIntegerCode of(int code) {
        for (ImplementedIntegerCode e : values()) {
            if (e.getCode() == code) {
                return e;
            }
        }
        return null;
    }
}
