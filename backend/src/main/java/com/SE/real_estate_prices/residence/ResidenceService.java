package com.SE.real_estate_prices.residence;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ResidenceService {
    private final ResidenceRepository residenceRepository;

    public ResidenceService(ResidenceRepository residenceRepository) {
        this.residenceRepository = residenceRepository;
    }

    public List<Residence> getResidences(String address, String city, String type, BigDecimal minPrice, BigDecimal maxPrice,
                                         Double minLandArea, Double maxLandArea, Double minLivingArea, Double maxLivingArea,
                                         Double minRooms, Double maxRooms, Date minPublishedDate, Date maxPublishedDate, BigDecimal minSqmPrice, BigDecimal maxSqmPrice) {

        if (address == null && city == null && type == null && minPrice == null && maxPrice == null &&
                minLandArea == null && maxLandArea == null && minLivingArea == null && maxLivingArea == null &&
                minRooms == null && maxRooms == null && minPublishedDate == null && maxPublishedDate == null &&
                minSqmPrice == null && maxSqmPrice == null) {
            return residenceRepository.findAll();
        }

        return residenceRepository.findAll().stream()
                .filter(residence -> address == null || residence.getAddress().toLowerCase().contains(address.toLowerCase()))
                .filter(residence -> city == null || residence.getCity().toLowerCase().contains(city.toLowerCase()))
                .filter(residence -> type == null || residence.getResidenceType().equals(type))
                .filter(residence -> minPrice == null || residence.getAskingPrice().doubleValue() >= minPrice.doubleValue())
                .filter(residence -> maxPrice == null || residence.getAskingPrice().doubleValue() <= maxPrice.doubleValue())
                .filter(residence -> minLandArea == null || residence.getLandAreaSqm() >= minLandArea)
                .filter(residence -> maxLandArea == null || residence.getLandAreaSqm() <= maxLandArea)
                .filter(residence -> minLivingArea == null || residence.getLivingAreaSqm() >= minLivingArea)
                .filter(residence -> maxLivingArea == null || residence.getLivingAreaSqm() <= maxLivingArea)
                .filter(residence -> minRooms == null || residence.getNumberOfRooms() >= minRooms)
                .filter(residence -> maxRooms == null || residence.getNumberOfRooms() <= maxRooms)
                .filter(residence -> minPublishedDate == null || residence.getPublished().equals(minPublishedDate) || residence.getPublished().after(minPublishedDate))
                .filter(residence -> maxPublishedDate == null || residence.getPublished().equals(maxPublishedDate) || residence.getPublished().before(maxPublishedDate))
                .filter(residence -> minSqmPrice == null || residence.getSqmPriceSek().doubleValue() >= minSqmPrice.doubleValue())
                .filter(residence -> maxSqmPrice == null || residence.getSqmPriceSek().doubleValue() <= maxSqmPrice.doubleValue())
                .collect(Collectors.toList());
    }

    public Optional<Residence> getResidenceById(Integer residenceAddressId) {
        return residenceRepository.findByResidenceAddressId(residenceAddressId);
    }

    public Residence addResidence(Residence residence) {
        return residenceRepository.save(residence);
    }

    public Residence updateResidence(Residence residence) {
        Optional<Residence> existingResidence = residenceRepository.findByResidenceAddressId(residence.getResidenceAddressId());

        if (existingResidence.isPresent()) {
            Residence updatedResidence = existingResidence.get();

            updatedResidence.setAddress(residence.getAddress());
            updatedResidence.setCity(residence.getCity());
            updatedResidence.setResidenceType(residence.getResidenceType());
            updatedResidence.setAskingPrice(residence.getAskingPrice());
            updatedResidence.setLandAreaSqm(residence.getLandAreaSqm());
            updatedResidence.setLivingAreaSqm(residence.getLivingAreaSqm());
            updatedResidence.setNumberOfRooms(residence.getNumberOfRooms());
            updatedResidence.setPublished(residence.getPublished());
            updatedResidence.setSqmPriceSek(residence.getSqmPriceSek());
            updatedResidence.setCoordinates(residence.getCoordinates());

            return residenceRepository.save(updatedResidence);
        }
        return null;
    }

    @Transactional
    public void deleteResidence(Integer residenceAddressId) {
        residenceRepository.deleteByResidenceAddressId(residenceAddressId);
    }
}
