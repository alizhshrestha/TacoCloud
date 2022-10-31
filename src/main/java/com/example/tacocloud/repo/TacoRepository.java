package com.example.tacocloud.repo;

import com.example.tacocloud.domain.Taco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TacoRepository extends JpaRepository<Taco, Long> {
}
