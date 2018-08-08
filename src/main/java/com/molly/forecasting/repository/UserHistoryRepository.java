package com.molly.forecasting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.molly.forecasting.entity.UserHistory;

@Repository("userHistoryRepository")
public interface UserHistoryRepository extends JpaRepository<UserHistory, Long> {
	
}
