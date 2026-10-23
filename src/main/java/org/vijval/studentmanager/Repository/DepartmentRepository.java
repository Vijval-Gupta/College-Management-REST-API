package org.vijval.studentmanager.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vijval.studentmanager.Entity.Department;

public interface DepartmentRepository extends JpaRepository<Department,Long> {
}
