package io.beanmapper.config;

import io.beanmapper.core.BeanMatchStore;
import io.beanmapper.core.constructor.BeanInitializer;
import io.beanmapper.core.converter.BeanConverter;
import io.beanmapper.core.unproxy.BeanUnproxy;
import io.beanmapper.core.unproxy.SkippingBeanUnproxy;
import io.beanmapper.dynclass.ClassStore;

import java.util.List;

public class OverrideConfiguration implements Configuration {

    private final Configuration parentConfiguration;

    private BeanInitializer beanInitializer;

    private SkippingBeanUnproxy beanUnproxy;

    private List<String> packagePrefixes;

    private List<BeanConverter> beanConverters;

    private List<String> limitSource;

    private List<String> limitTarget;

    private Class targetClass;

    private Object target;

    private Class collectionClass;

    private boolean converterChoosable = true;

    public OverrideConfiguration(Configuration configuration) {
        if (configuration == null) {
            throw new RuntimeException("Developer error: the parent configuration may not be null");
        }
        this.parentConfiguration = configuration;
    }

    @Override
    public List<String> getDownsizeSource() {
        return limitSource;
    }

    @Override
    public List<String> getDownsizeTarget() {
        return limitTarget;
    }

    @Override
    public Class getCollectionClass() {
        return collectionClass;
    }

    @Override
    public Class getTargetClass() {
        return targetClass;
    }

    @Override
    public Object getTarget() {
        return target;
    }

    @Override
    public BeanInitializer getBeanInitializer() {
        return beanInitializer == null ? parentConfiguration.getBeanInitializer() : beanInitializer;
    }

    @Override
    public SkippingBeanUnproxy getBeanUnproxy() {
        return beanUnproxy == null ? parentConfiguration.getBeanUnproxy() : beanUnproxy;
    }

    @Override
    public BeanMatchStore getBeanMatchStore() {
        return parentConfiguration.getBeanMatchStore();
    }

    @Override
    public ClassStore getClassStore() {
        return parentConfiguration.getClassStore();
    }

    @Override
    public List<String> getPackagePrefixes() {
        return packagePrefixes == null ? parentConfiguration.getPackagePrefixes() : packagePrefixes;
    }

    @Override
    public List<BeanConverter> getBeanConverters() {
        return beanConverters == null ? parentConfiguration.getBeanConverters() : beanConverters;
    }

    @Override
    public boolean isConverterChoosable() {
        return converterChoosable;
    }

    @Override
    public void withoutDefaultConverters() {
        // not supported for override options
    }

    // @todo make sure addConverter works for override
    @Override
    public void addConverter(BeanConverter converter) {
//        if (beanConverters == null) {
//            beanConverters = new ArrayList<BeanConverter>();
//        }
//        beanConverters.add(converter);
        // not supported for override options
    }

    @Override
    public void addProxySkipClass(Class<?> clazz) {
        // not supported for override options
    }

    @Override
    public void addPackagePrefix(Class<?> clazz) {
        // not supported for override options
    }

    @Override
    public void addPackagePrefix(String packagePrefix) {
        // not supported for override options
    }

    @Override
    public void setBeanInitializer(BeanInitializer beanInitializer) {
        this.beanInitializer = beanInitializer;
    }

    @Override
    public void setBeanUnproxy(BeanUnproxy beanUnproxy) {
        this.beanUnproxy = new SkippingBeanUnproxy(beanUnproxy);
    }

    @Override
    public boolean isAddDefaultConverters() {
        return false;
    }

    @Override
    public void downsizeSource(List<String> includeFields) {
        this.limitSource = includeFields;
    }

    @Override
    public void downsizeTarget(List<String> includeFields) {
        this.limitTarget = includeFields;
    }

    @Override
    public void setCollectionClass(Class collectionClass) {
        this.collectionClass = collectionClass;
    }

    @Override
    public void setTargetClass(Class targetClass) {
        this.targetClass = targetClass;
    }

    @Override
    public void setTarget(Object target) {
        this.target = target;
    }

    @Override
    public void setConverterChoosable(boolean converterChoosable) {
        this.converterChoosable = converterChoosable;
    }

    @Override
    public boolean canReuse() {
        return true;
    }

    @Override
    public Class determineTargetClass() {
        return getTargetClass() == null ? getTarget().getClass() : getTargetClass();
    }
}
