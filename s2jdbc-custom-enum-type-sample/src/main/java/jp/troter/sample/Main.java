package jp.troter.sample;

import javax.annotation.Resource;

import jp.troter.sample.entity.SampleTable;
import jp.troter.sample.enums.ImplementedIntegerCode;
import jp.troter.sample.enums.ImplementedStringCode;
import jp.troter.sample.enums.NormalEnum;
import jp.troter.sample.service.SampleTableService;

import org.seasar.framework.container.SingletonS2Container;
import org.seasar.framework.container.deployer.ComponentDeployerFactory;
import org.seasar.framework.container.deployer.ComponentDeployerFactory.DefaultProvider;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

public class Main {

    @Resource
    SampleTableService sampleTableService;

    public void run() {
        for (SampleTable s : sampleTableService.findAll()) {
            sampleTableService.delete(s);
        }
        SampleTable first = new SampleTable();
        first.integerCode = ImplementedIntegerCode.FIRST;
        first.stringCode = ImplementedStringCode.FIRST;
        first.ordinalCode = NormalEnum.FIRST;
        first.nameCode = NormalEnum.FIRST;

        sampleTableService.insert(first);
        
        SampleTable second = new SampleTable();
        second.integerCode = ImplementedIntegerCode.SECOND;
        second.stringCode = ImplementedStringCode.SECOND;
        second.ordinalCode = NormalEnum.SECOND;
        second.nameCode = NormalEnum.SECOND;
        
        sampleTableService.insert(second);

        for (SampleTable s : sampleTableService.findAll()) {
            System.out.println(s);
        }
    }

    public static void main(String[] args) {
        ComponentDeployerFactory.setProvider(new DefaultProvider());
        SingletonS2ContainerFactory.init();
        
        Main main = SingletonS2Container.getComponent(Main.class);
        main.run();
        return;
    }
}
