package com.example.kotlinspringbootdemo1.services

import com.example.kotlinspringbootdemo1.data.SeatRepository
import com.example.kotlinspringbootdemo1.model.Seat
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class TheaterService(val seatRepository: SeatRepository) {
    fun find(selectedSeatNum: Int, selectedSeatRow: Char): Seat {
        return Seat(1L, 'A', 1, BigDecimal(23), "")
    }

    fun seats(): List<Seat> {
        val seats = mutableListOf<Seat>()
        for (seatNumber in 1..36) {
            for (seatRow in 'A'..'O') {
                seats.add(Seat(1L, seatRow, seatNumber, BigDecimal(23), ""))
            }
        }
        return seats
    }
}