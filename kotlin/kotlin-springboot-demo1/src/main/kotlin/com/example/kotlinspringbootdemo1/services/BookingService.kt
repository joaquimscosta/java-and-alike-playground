package com.example.kotlinspringbootdemo1.services

import com.example.kotlinspringbootdemo1.model.Seat
import org.springframework.stereotype.Service

@Service
class BookingService{
    fun isSeatFree(selectedSeat: Seat): Boolean {
        return true
    }
}