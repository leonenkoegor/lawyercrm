package webfusion.lawyercrm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webfusion.lawyercrm.models.Feedback;
import webfusion.lawyercrm.repositories.FeedbackRepository;

import java.util.List;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    @Autowired
    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public List<Feedback> findAll() {
        return feedbackRepository.findAll();
    }

    public void update(Feedback feedback) {
        feedbackRepository.save(feedback);
    }

    public void delete(Feedback feedback) {
        feedbackRepository.delete(feedback);
    }

}
