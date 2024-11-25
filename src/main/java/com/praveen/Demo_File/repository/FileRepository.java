package com.praveen.Demo_File.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.praveen.Demo_File.entity.File;

@Repository
public interface FileRepository extends JpaRepository<File, Integer> {

}
