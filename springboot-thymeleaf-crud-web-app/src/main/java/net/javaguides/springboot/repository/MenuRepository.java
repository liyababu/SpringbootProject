package net.javaguides.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.model.Menu;


@Repository
public abstract interface MenuRepository extends JpaRepository<Menu, Long>
{
}
