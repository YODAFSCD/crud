package com.example.event.service

import com.example.event.model.Reptil
import com.example.event.repository.ReptilRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class ReptilService {
    @Autowired
    lateinit var reptilRepository: ReptilRepository

    fun list ():List<Reptil>{
        return reptilRepository.findAll()
    }

    fun save (reptil: Reptil):Reptil{
        return reptilRepository.save(reptil)
    }

    fun update(reptil: Reptil):Reptil{
        try {
            reptilRepository.findById(reptil.id)
                ?: throw Exception("El id ${reptil.id} en reptile no existe")
            return reptilRepository.save(reptil)
        }
        catch(ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun updateName(reptil: Reptil):Reptil {
        try {
            val response = reptilRepository.findById(reptil.id)
                ?: throw Exception("El ${reptil.id} en reptile no existe")
            response.apply {
                nombre = reptil.nombre
            }
            return reptilRepository.save(response)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)

        }
    }
        fun list (pageable: Pageable, reptil: Reptil): Page<Reptil> {
            val matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withMatcher(("field"), ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            return reptilRepository.findAll(Example.of(reptil, matcher), pageable)

        }
    fun delete (id: Long?):Boolean?{
        reptilRepository.findById(id) ?:
        throw  Exception()
        reptilRepository.deleteById(id!!)
        return true
    }

}