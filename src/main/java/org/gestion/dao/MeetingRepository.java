package org.gestion.dao;

import org.gestion.modele.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRepository extends JpaRepository<Meeting, Integer>{

}
