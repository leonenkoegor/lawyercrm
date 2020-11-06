package webfusion.lawyercrm.views.feedback;

import com.vaadin.flow.component.crud.CrudEditor;
import com.vaadin.flow.data.provider.AbstractBackEndDataProvider;
import com.vaadin.flow.data.provider.Query;
import webfusion.lawyercrm.models.Feedback;
import webfusion.lawyercrm.services.FeedbackService;

import java.util.stream.Stream;

public class FeedbackDataProvider extends AbstractBackEndDataProvider<Feedback, CrudEditor> {

    private final FeedbackService feedbackService;

    public FeedbackDataProvider(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @Override
    protected Stream<Feedback> fetchFromBackEnd(Query<Feedback, CrudEditor> query) {
        return feedbackService.findAll().stream();
    }

    @Override
    protected int sizeInBackEnd(Query<Feedback, CrudEditor> query) {
        return feedbackService.findAll().size();
    }

}
