package hello.core.scope;

import static org.assertj.core.api.Assertions.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class SingletonTest {

    @Test
    void singletonBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
            SingleToneBean.class);
        SingleToneBean singleToneBean1 = ac.getBean(SingleToneBean.class);
        SingleToneBean singleToneBean2 = ac.getBean(SingleToneBean.class);
        System.out.println("singleToneBean1 = " + singleToneBean1);
        System.out.println("singleToneBean2 = " + singleToneBean2);
        assertThat(singleToneBean1).isSameAs(singleToneBean2);

        ac.close();
    }

    @Scope("singleton")
    static class SingleToneBean {
        @PostConstruct
        public void init() {
            System.out.println("SingletonBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("SingletonBean.destroy");
        }
    }
}
