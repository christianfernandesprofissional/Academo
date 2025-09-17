package com.academo.repository;

import com.academo.controller.dtos.notification.NotificationDTO;
import com.academo.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Integer> {

    List<Activity> findAllByUserId(Integer userId);
    Optional<Activity> findByIdAndUserId(Integer userId, Integer activityId);
    Boolean existsActivityByName(String activityName);

    @Query(nativeQuery = true, value = """
            SELECT\s
            	tu.email,
            	json_agg(
            		json_build_object(
            			'name', ta.name,
            			'description', ta.description,
            			'subject', ts.name,
            			'activityDate', ta.activity_date,
            			'notificationDate', ta.notification_date
            		)
            	) as activities
            FROM
            	tb_activities ta
            INNER JOIN
            	tb_subjects ts
            ON
            	ta.subject_id = ts.id
            INNER JOIN
            	tb_users tu
            ON
            	ta.user_id = tu.id
            WHERE
            	ta.notification_date = :date
            GROUP BY
            	tu.email""")
    List<NotificationDTO> searchNotificationByDate(@Param("date") LocalDate date);
}
