package com.example.kotlinspringbootdemo1.data

import com.example.kotlinspringbootdemo1.model.Seat
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SeatRepository :CrudRepository<Seat,Long>