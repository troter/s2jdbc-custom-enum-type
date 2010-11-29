package jp.troter.sample.enums;

import jp.troter.seasar.extension.jdbc.types.StringCode;

public enum ImplementedStringCode implements StringCode {
    FIRST("first enums"),
    SECOND("second enums"),
    ;
    
    private final String code;

    ImplementedStringCode(String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;
    }

    public static ImplementedStringCode codeOf(String code) {
        for (ImplementedStringCode e : values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }
}
