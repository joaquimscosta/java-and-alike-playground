package com.example.kotlinspringbootdemo1.controller

import com.example.kotlinspringbootdemo1.data.SeatRepository
import com.example.kotlinspringbootdemo1.services.BookingService
import com.example.kotlinspringbootdemo1.services.TheaterService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class MainController(
        val bookingService: BookingService,
        val theaterService: TheaterService,
        val seatRepository: SeatRepository) {

    @GetMapping("")
    fun homePage(): ModelAndView = ModelAndView("seatBooking", "bean", CheckAvailabilityBackingBean())

    @PostMapping(value = arrayOf("checkAvailability"))
    fun checkAvailability(bean: CheckAvailabilityBackingBean): ModelAndView {
        val selectedSeat = theaterService.find(bean.selectedSeatNum, bean.selectedSeatRow)
        val result = bookingService.isSeatFree(selectedSeat)
        bean.result = "Seat $selectedSeat is " + if (result) "available" else "booked"
        return ModelAndView("seatBooking", "bean", bean)
    }

    @RequestMapping
    fun createInitialData(): ModelAndView {
        val seats = theaterService.seats()
        seatRepository.saveAll(seats)
        return homePage()
    }
}

class CheckAvailabilityBackingBean() {
    val seatNums = 1..36
    val seatRows = 'A'..'O'
    var selectedSeatNum: Int = 1
    var selectedSeatRow: Char = 'A'
    var result: String = ""
}