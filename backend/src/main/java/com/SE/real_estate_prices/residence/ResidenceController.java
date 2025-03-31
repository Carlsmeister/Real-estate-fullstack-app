package com.SE.real_estate_prices.residence;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/residence")
public class ResidenceController {
    private final ResidenceService residenceService;

    public ResidenceController(ResidenceService residenceService) {
        this.residenceService = residenceService;
    }

    @GetMapping
    public List<Residence> getAllResidences(
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) Double minLandArea,
            @RequestParam(required = false) Double maxLandArea,
            @RequestParam(required = false) Double minLivingArea,
            @RequestParam(required = false) Double maxLivingArea,
            @RequestParam(required = false) BigDecimal minSqmPrice,
            @RequestParam(required = false) BigDecimal maxSqmPrice,
            @RequestParam(required = false) Double minRooms,
            @RequestParam(required = false) Double maxRooms,
            @RequestParam(required = false) Date minPublishedDate,
            @RequestParam(required = false) Date maxPublishedDate) {

        return residenceService.getResidences(
                address,
                city,
                type,
                minPrice, maxPrice,
                minLandArea, maxLandArea,
                minLivingArea, maxLivingArea,
                minRooms, maxRooms,
                minPublishedDate, maxPublishedDate,
                minSqmPrice, maxSqmPrice
        );
    }

    @GetMapping("/{residenceAddressId}")
    public ResponseEntity<Residence> getResidenceById(@PathVariable Integer residenceAddressId) {
        Optional<Residence> residence = residenceService.getResidenceById(residenceAddressId);
        return residence.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Residence> createResidence(@RequestBody Residence residence) {
        Residence createdResidence = residenceService.addResidence(residence);
        return new ResponseEntity<>(createdResidence, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Residence> updateResidence(@RequestBody Residence residence) {
        Residence updatedResidence = residenceService.updateResidence(residence);
        if (updatedResidence == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedResidence, HttpStatus.OK);
    }

    @DeleteMapping("/{residenceAddressId}")
    public ResponseEntity<String> deleteResidence(@PathVariable Integer residenceAddressId) {
        residenceService.deleteResidence(residenceAddressId);
        return new ResponseEntity<>("Residence was deleted successfully", HttpStatus.OK);
    }
}
