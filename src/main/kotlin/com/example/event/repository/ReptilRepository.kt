package com.example.event.repository

import com.example.event.model.Reptil
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface ReptilRepository:JpaRepository<Reptil, Long> {
    fun findById(id: Long?): Reptil?
}
