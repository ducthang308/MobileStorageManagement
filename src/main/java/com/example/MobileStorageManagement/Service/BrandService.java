package com.example.MobileStorageManagement.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.DTO.BrandDTO;
import com.example.MobileStorageManagement.Entity.Brand;
import com.example.MobileStorageManagement.Repository.BrandRepository;

@Service
public class BrandService {
    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    private BrandDTO convertToDTO(Brand brand) {
        return BrandDTO.builder()
                .brandId(brand.getBrandId())
                .name(brand.getName())
                .country(brand.getCountry())
                .description(brand.getDescription())
                .build();
    }

    public List<BrandDTO> getAllBrands() {
        return brandRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public BrandDTO getBrandById(Integer id) {
        Brand b = brandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand not found with id: " + id));
        return convertToDTO(b);
    }

    // search brand by name
    public List<BrandDTO> searchBrandByName(String name) {
        return brandRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<BrandDTO> getBrandsByCountry(String country) {
        return brandRepository.findByCountry(country).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public BrandDTO createBrand(BrandDTO brandDTO) {
        if (brandRepository.existsByName(brandDTO.getName())) {
            throw new RuntimeException("Brand already exists with name: " + brandDTO.getName());
        } else {
            Brand brand = new Brand();
            brand.setName(brandDTO.getName());
            brand.setCountry(brandDTO.getCountry());
            brand.setDescription(brandDTO.getDescription());
            return convertToDTO(brandRepository.save(brand));
        }
    }

    public BrandDTO updateBrand(Integer id, BrandDTO brandDTO) {
        Optional<Brand> existBrand = brandRepository.findById(id);
        if (existBrand.isPresent()) {
            Brand brand = existBrand.get();
            brand.setName(brandDTO.getName());
            brand.setCountry(brandDTO.getCountry());
            brand.setDescription(brandDTO.getDescription());
            return convertToDTO(brandRepository.save(brand));
        } else {
            throw new RuntimeException("Brand not found with id: " + id);
        }
    }

    public void deleteBrand(Integer id) {
        if (brandRepository.existsById(id)) {
            brandRepository.deleteById(id);
        } else {
            throw new RuntimeException("Brand not found with id: " + id);
        }
    }
}
