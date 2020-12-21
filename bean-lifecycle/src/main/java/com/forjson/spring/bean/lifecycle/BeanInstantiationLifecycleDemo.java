package com.forjson.spring.bean.lifecycle;

import com.forjson.spring.bean.lifecycle.domain.UserHolder;
import com.forjson.spring.ioc.container.overview.container.IOCContainerDemo;
import com.forjson.spring.ioc.container.overview.domain.SuperUser;
import com.forjson.spring.ioc.container.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.util.ObjectUtils;

/**
 * {@link InstantiationAwareBeanPostProcessor} 演示
 * <p>
 * BeanDefinitionReader 演示 {@link IOCContainerDemo}
 */
public class BeanInstantiationLifecycleDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //添加beanPostProcessor
        beanFactory.addBeanPostProcessor(new UserInstantiationAwareBeanPostProcessor());
        //创建PropertiesBeanDefinitionReader 对象
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        //A:需要使用EncodedResource指定编码方式进行加载
        String[] locations = {"classpath:/META-INF/dependency-lookup-context.xml", "classpath:/META-INF/constructor-dependency-lookup-context.xml"};
        int beanNumbers = beanDefinitionReader.loadBeanDefinitions(locations);

        System.out.println("通过XmlBeanDefinitionReader,加载到bean数量:" + beanNumbers);

        User user = beanFactory.getBean("user", User.class);
        System.out.println("user:" + user);
        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
        System.out.println("userHolder:" + userHolder);
    }

    static class UserInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
        //实例化前
        @Override
        public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
            if ("superUser".equals(beanName) && SuperUser.class.equals(beanClass)) {
                SuperUser superUser = new SuperUser();
                superUser.setId(-1L);
                superUser.setName("howliked-intantiation");
                superUser.setAddress("实例化前更新了值");
                return superUser;
            }
            return null;
        }

        //实例化之后,赋值前
        @Override
        public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
            if ("user".equals(beanName) && User.class.equals(bean.getClass())) {
                User user = new User();
                user.setId(-1L);
                user.setName("userName 手动赋值");
                return false;
            }
            return true;
        }

        //如果postProcessAfterInstantiation 方法返回false,则该方法不会被调用
        @Override
        public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
            //对userHolder进行拦截
            if (ObjectUtils.nullSafeEquals("userHolder", beanName) && UserHolder.class.equals(bean.getClass())) {
                MutablePropertyValues propertyValues;
                if (pvs instanceof MutablePropertyValues) {
                    propertyValues = (MutablePropertyValues) pvs;
                } else {
                    propertyValues = new MutablePropertyValues();
                }
                //等价于  <property name="number" value="007"/>
                propertyValues.addPropertyValue("number", "007");
                if (propertyValues.contains("desc")) {
                    propertyValues.removePropertyValue("desc");
                    //等价于  <property name="desc" value="The User Holder V2"/>
                    propertyValues.addPropertyValue("desc", "The User Holder V2");
                }
                return pvs;
            }
            return null;
        }
    }
}
