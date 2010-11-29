package jp.troter.sample.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import jp.troter.sample.enums.ImplementedIntegerCode;
import jp.troter.sample.enums.ImplementedStringCode;
import jp.troter.sample.enums.NormalEnum;

@Entity
public class SampleTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    
    @Enumerated(EnumType.ORDINAL)
    public ImplementedIntegerCode integerCode;
    
    @Enumerated(EnumType.STRING)
    public ImplementedStringCode stringCode;
    
    @Enumerated(EnumType.ORDINAL)
    public NormalEnum ordinalCode;
    
    @Enumerated(EnumType.STRING)
    public NormalEnum nameCode;
    
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(this.getClass().getSimpleName()).append("[");
        s.append("id=").append(id).append(", ");
        s.append("integerCode=").append(integerCode).append(", ");
        s.append("stringCode=").append(stringCode).append(", ");
        s.append("ordinalCode=").append(ordinalCode).append(", ");
        s.append("nameCode=").append(nameCode).append("]");
        return s.toString();
    }
}
