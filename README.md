Custom enum type support for S2JDBC.
===========================================

pom.xmlに追記する設定
---------------------

**mavenリポジトリを追加**

    <repositories>
      <repository>
        <id>troter.jp/release</id>
        <name>TROTER.JP Release Maven2 Repository</name>
        <url>http://troter.jp/maven2/release</url>
      </repository>
    </repositories>

**依存関係を追加**

    <dependencies>
      <dependency>
        <groupId>jp.troter</groupId>
        <artifactId>s2jdbc-custom-enum-type</artifactId>
        <version>1.0.0</version>
      </dependency>
    </dependencies>

設定例
------

**s2jdbc.diconの設定例**

    <?xml version="1.0" encoding="UTF-8"?>
    <!DOCTYPE components PUBLIC "-//SEASAR//DTD S2Container 2.4//EN"
        "http://www.seasar.org/dtd/components24.dtd">
    <components>
      <include path="jdbc.dicon"/>
      <include path="s2jdbc-internal.dicon"/>
      <component name="jdbcManager"
       class="org.seasar.extension.jdbc.manager.JdbcManagerImpl">
        <property name="maxRows">0</property>
        <property name="fetchSize">0</property>
        <property name="queryTimeout">0</property>
        <property name="dialect">h2Dialect</property>
        <property name="allowVariableSqlForBatchUpdate">true</property>
        <initMethod>
          @org.seasar.extension.jdbc.types.ValueTypes@setEnumDefaultValueType(
            @jp.troter.seasar.extension.jdbc.types.EnumIntegerType@class)
        </initMethod>
        <initMethod>
          @org.seasar.extension.jdbc.types.ValueTypes@setEnumOrdinalValueType(
            @jp.troter.seasar.extension.jdbc.types.EnumIntegerType@class)
        </initMethod>
        <initMethod>
          @org.seasar.extension.jdbc.types.ValueTypes@setEnumStringValueType(
            @jp.troter.seasar.extension.jdbc.types.EnumStringType@class)
        </initMethod>
      </component>
    </components>

**IntegerCodeを利用する場合の定義例**

    import jp.troter.seasar.extension.jdbc.types.IntegerCode;
    
    public enum IntegerCodeImplementation implements IntegerCode {
        FIRST(100),
        SECOND(-100),
        ;
    
        private final int code;
    
        IntegerCodeImplementation(int code) {
            this.code = code;
        }
    
        // codeを取得するためのメソッドを実装
        @Override
        public int getCode() {
            return code;
        }
    
        // ファクトリとしてofメソッドが必要
        public static IntegerCodeImplementation of(int code) {
            for (IntegerCodeImplementation e : values()) {
                if (e.getCode() == code) {
                    return e;
                }
            }
            return null;
        }
    }

**StringCodeを利用する場合の定義例**

    import jp.troter.seasar.extension.jdbc.types.StringCode;
    
    public enum StringCodeImplementation implements StringCode {
        FIRST("first enums"),
        SECOND("second enums"),
        ;
        
        private final String code;
    
        StringCodeImplementation(String code) {
            this.code = code;
        }
    
        // codeを取得するためのメソッドを実装
        @Override
        public String getCode() {
            return code;
        }
    
        // ファクトリとしてofメソッドが必要
        public static StringCodeImplementation of(String code) {
            for (ImplementedStringCode e : values()) {
                if (e.getCode().equals(code)) {
                    return e;
                }
            }
            return null;
        }
    }
