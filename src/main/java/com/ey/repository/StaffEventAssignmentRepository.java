package com.ey.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ey.entity.StaffEventAssignment;

@Repository
public interface StaffEventAssignmentRepository extends JpaRepository<StaffEventAssignment, Long> {

	List<StaffEventAssignment> findByEventId(Long eventId);
	boolean existsByEventIdAndStaffId(Long eventId, Long staffId);
	Optional<StaffEventAssignment> findByEventIdAndStaffId(Long eventId, Long staffId);
	boolean existsByStaffIdAndEventId(Long id, Long eventId);
}
