package edu.stanford.bmir.protege.web.shared.form.data;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLEntity;

import javax.annotation.Nonnull;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2020-01-13
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.CUSTOM
)
@JsonSubTypes({
                    @JsonSubTypes.Type(FormEntitySubject.class),
              })
public interface FormSubject {

    interface FormDataSubjectVisitorEx<R> {

        R visit(@Nonnull FormEntitySubject formDataEntitySubject);
    }

    interface FormDataSubjectVisitor {

        void visit(@Nonnull FormEntitySubject formDataEntitySubject);
    }


    static FormEntitySubject get(@Nonnull OWLEntity entity) {
        return FormEntitySubject.get(entity);
    }

    <R> R accept(@Nonnull FormDataSubjectVisitorEx<R> visitor);

    void accept(@Nonnull FormDataSubjectVisitor visitor);

    @Nonnull
    IRI getIri();

}
