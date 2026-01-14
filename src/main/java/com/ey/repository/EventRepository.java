package com.ey.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ey.entity.Event;
import com.ey.entity.User;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {

}
