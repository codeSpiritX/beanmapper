package io.beanmapper.core.converter.collections;

import io.beanmapper.annotations.BeanCollectionUsage;

public class BeanCollectionInstructions {

    private Class collectionMapsTo;

    private BeanCollectionUsage beanCollectionUsage;

    private Class<?> preferredInstantiatedClass;

    public Class getCollectionMapsTo() {
        return collectionMapsTo;
    }

    public void setCollectionMapsTo(Class collectionMapsTo) {
        this.collectionMapsTo = collectionMapsTo;
    }

    public BeanCollectionUsage getBeanCollectionUsage() {
        return beanCollectionUsage;
    }

    public void setBeanCollectionUsage(BeanCollectionUsage beanCollectionUsage) {
        this.beanCollectionUsage = beanCollectionUsage;
    }

    public Class<?> getPreferredInstantiatedClass() {
        return preferredInstantiatedClass;
    }

    public void setPreferredInstantiatedClass(Class<?> preferredInstantiatedClass) {
        this.preferredInstantiatedClass =
                preferredInstantiatedClass == null || preferredInstantiatedClass.equals(void.class) ?
                null :
                preferredInstantiatedClass;
    }

}
