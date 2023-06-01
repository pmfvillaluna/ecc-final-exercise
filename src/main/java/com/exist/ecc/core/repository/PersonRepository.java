package com.exist.ecc.core.repository;

import com.exist.ecc.core.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("SELECT p FROM Person p ORDER BY p.GWA")
    List<Person> listPersonByGWAAsc();
    @Query("SELECT p FROM Person p ORDER BY p.GWA DESC")
    List<Person> listPersonByGWADesc();

    @Query("SELECT p FROM Person p ORDER BY p.dateHired")
    List<Person> listPersonByDateHiredAsc();
    @Query("SELECT p FROM Person p ORDER BY p.dateHired DESC")
    List<Person> listPersonByDateHiredDesc();

    @Query("SELECT p FROM Person p ORDER BY p.name.lastName")
    List<Person> listPersonByLastNameAsc();
    @Query("SELECT p FROM Person p ORDER BY p.name.lastName DESC")
    List<Person> listPersonByLastNameDesc();

}


