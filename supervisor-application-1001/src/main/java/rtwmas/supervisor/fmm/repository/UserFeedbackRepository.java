package rtwmas.supervisor.fmm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rtwmas.supervisor.fmm.model.UserFeedback;

public interface UserFeedbackRepository extends JpaRepository<UserFeedback, String> {

}
