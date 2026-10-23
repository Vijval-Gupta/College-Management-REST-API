package org.vijval.studentmanager.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vijval.studentmanager.Entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
