package io.beanmapper.core.converter.collections;

import io.beanmapper.BeanMapper;
import io.beanmapper.annotations.BeanCollectionUsage;
import io.beanmapper.core.BeanFieldMatch;
import io.beanmapper.core.converter.BeanConverter;
import io.beanmapper.utils.Classes;

import java.util.Collection;
import java.util.Map;

public abstract class AbstractCollectionConverter<T> implements BeanConverter {

    private final Class<?> type;
    protected abstract T createCollection();

    public AbstractCollectionConverter() {
        Class<?>[] types = Classes.getParameteredTypes(getClass());
        this.type = types[0];
    }

    @Override
    public T convert(BeanMapper beanMapper, Object source, Class<?> targetClass, BeanFieldMatch beanFieldMatch) {
        T sourceCollection = (T) source;

        if (beanFieldMatch.getCollectionInstructions() == null) {
            return sourceCollection;
        }

        T targetCollection = getTargetCollection(beanFieldMatch);

        if(targetCollection instanceof Map) {
            for (Object key : ((Map)sourceCollection).keySet()) {
                ((Map) targetCollection).put(key, convertElement(beanMapper, ((Map) sourceCollection).get(key), beanFieldMatch));
            }
        } else {
            for (Object sourceItem : (Collection)sourceCollection) {
                ((Collection) targetCollection).add(convertElement(beanMapper, sourceItem, beanFieldMatch));
            }
        }

        return targetCollection;
    }

    private T getTargetCollection(BeanFieldMatch beanFieldMatch) {

        BeanCollectionUsage beanCollectionUsage = beanFieldMatch.getCollectionInstructions().getBeanCollectionUsage();

        T targetCollection =
                (beanCollectionUsage == BeanCollectionUsage.CONSTRUCT || beanFieldMatch.getTargetObject() == null) ?
                        createCollection() :
                        (T)beanFieldMatch.getTargetObject();

        if (beanCollectionUsage == BeanCollectionUsage.CLEAR) {
            if(targetCollection instanceof Map)
                ((Map) targetCollection).clear();
            else
                ((Collection) targetCollection).clear();
        }

        return targetCollection;
    }

    private Object convertElement(BeanMapper beanMapper, Object source, BeanFieldMatch beanFieldMatch) {
        return beanMapper.map(source, beanFieldMatch.getCollectionInstructions().getCollectionMapsTo(), true);
    }

    @Override
    public boolean match(Class<?> sourceClass, Class<?> targetClass) {
        return targetClass.isAssignableFrom(type);
    }


}
