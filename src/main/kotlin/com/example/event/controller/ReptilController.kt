package com.example.event.controller


import com.example.event.model.Reptil
import com.example.event.service.ReptilService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController



@RestController
@RequestMapping("/reptil")
class ReptilController {

    @Autowired
    lateinit var reptilService: ReptilService


    @GetMapping
    fun list (reptil: Reptil, pageable: Pageable):ResponseEntity<*>{
        val response= reptilService.list(pageable,reptil)
        return ResponseEntity(response, HttpStatus.OK)
    }


    @PostMapping
    fun save(@RequestBody reptil: Reptil): Reptil?{
        return reptilService.save(reptil)

    }

    @PutMapping
    fun update(@RequestBody reptil:Reptil): ResponseEntity<Reptil> {
       return ResponseEntity (reptilService.update(reptil),HttpStatus.OK)
    }

    @PatchMapping
    fun updateName(@RequestBody reptil: Reptil): ResponseEntity<Reptil> {
        return ResponseEntity(reptilService.updateName(reptil), HttpStatus.ACCEPTED)
    }
    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long):Boolean?{
        return reptilService.delete(id)
    }

}