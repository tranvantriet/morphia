package dev.morphia.mapping.conventions;

import dev.morphia.Datastore;
import dev.morphia.annotations.Embedded;
import dev.morphia.annotations.Entity;
import dev.morphia.mapping.Mapper;
import dev.morphia.mapping.MapperOptions;
import dev.morphia.mapping.codec.pojo.EntityModelBuilder;

/**
 * A set of conventions to apply to Morphia entities
 */
@SuppressWarnings("unchecked")
public class MorphiaDefaultsConvention implements MorphiaConvention {

    @Override
    public void apply(Datastore datastore, EntityModelBuilder modelBuilder) {
        MapperOptions options = datastore.getMapper().getOptions();

        final Entity entity = modelBuilder.getAnnotation(Entity.class);
        final Embedded embedded = modelBuilder.getAnnotation(Embedded.class);
        if (entity != null) {
            modelBuilder.enableDiscriminator(entity.useDiscriminator());
            modelBuilder.discriminatorKey(applyDefaults(entity.discriminatorKey(), options.getDiscriminatorKey()));
        } else {
            modelBuilder.enableDiscriminator(embedded == null || embedded.useDiscriminator());
            modelBuilder.discriminatorKey(applyDefaults(embedded != null ? embedded.discriminatorKey() : Mapper.IGNORED_FIELDNAME,
                options.getDiscriminatorKey()));
        }

        options.getDiscriminator().apply(modelBuilder);
    }

    String applyDefaults(String configured, String defaultValue) {
        if (!configured.equals(Mapper.IGNORED_FIELDNAME)) {
            return configured;
        } else {
            return defaultValue;
        }
    }

}
