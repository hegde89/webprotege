package edu.stanford.bmir.protege.web.shared.form.field;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.google.auto.value.AutoValue;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import edu.stanford.bmir.protege.web.shared.lang.LanguageMap;
import edu.stanford.bmir.protege.web.shared.match.criteria.*;
import org.semanticweb.owlapi.model.EntityType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

import static com.google.common.base.MoreObjects.toStringHelper;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 30/03/16
 */
@JsonTypeName(EntityNameFieldDescriptor.TYPE)
@AutoValue
@GwtCompatible(serializable = true)
public abstract class EntityNameFieldDescriptor implements FormFieldDescriptor {

    protected static final String TYPE = "ENTITY_NAME";

    @JsonCreator
    @Nonnull
    public static EntityNameFieldDescriptor get(@Nullable @JsonProperty("placeholder") LanguageMap languageMap,
                                                @Nullable @JsonProperty("matchCriteria") CompositeRootCriteria criteria) {
        return new AutoValue_EntityNameFieldDescriptor(languageMap == null ? LanguageMap.empty() : languageMap,
                                                       criteria);
    }

    @Nonnull
    public static EntityNameFieldDescriptor getDefault() {
        return new AutoValue_EntityNameFieldDescriptor(LanguageMap.empty(),
                                                       getDefaultEntityMatchCriteria());
    }

    public static CompositeRootCriteria getDefaultEntityMatchCriteria() {
        return CompositeRootCriteria.get(
                ImmutableList.of(EntityTypeIsOneOfCriteria.get(
                        ImmutableSet.of(EntityType.CLASS)
                )),
                MultiMatchType.ALL
        );
    }

    public static String getFieldTypeId() {
        return TYPE;
    }

    @Nonnull
    @Override
    @JsonIgnore
    public String getAssociatedType() {
        return TYPE;
    }

    @Nonnull
    public abstract LanguageMap getPlaceholder();

    @JsonIgnore
    @Nullable
    protected abstract CompositeRootCriteria getMatchCriteriaInternal();

    @Nonnull
    public Optional<CompositeRootCriteria> getMatchCriteria() {
        return Optional.ofNullable(getMatchCriteriaInternal());
    }
}
