//package com.proiect_final.proiect_final.controllers;
//
//import com.proiect_final.proiect_final.dtos.ReservationRequestDTO;
//import com.proiect_final.proiect_final.entities.Reservation;
//import com.proiect_final.proiect_final.services.ReservationService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/reservation")
//public class ReservationController {
//
//    private ReservationService reservationService;
//
//    @Autowired
//    public ReservationController(ReservationService reservationService) {
//        this.reservationService = reservationService;
//    }
//
////    @PostMapping
////    public ResponseEntity<Reservation> createReservation(@RequestBody ReservationRequestDTO reservationRequestDTO){
////        return ResponseEntity.ok(reservationService.createReservation(reservationRequestDTO));
////    }
//}
