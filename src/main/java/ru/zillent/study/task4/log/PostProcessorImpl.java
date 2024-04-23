package ru.zillent.study.task4.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

@Component
public class PostProcessorImpl implements BeanPostProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(PostProcessorImpl.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (!bean.getClass().isAnnotationPresent(LogTransformation.class)) return bean;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(bean.getClass());
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                Date dt = Calendar.getInstance().getTime();
                Object result = method.invoke(bean, args);
                LOGGER.info(String.format("Start: %s, bean: %s, method: %s, args: %s, result: %s",
                        dt,
                        beanName,
                        method.getName(),
                        Arrays.toString(args),
                        result
                ));
                return result;
            }
        });
        // execute noArgsConstructor
        return enhancer.create();
    }
}
