package com.example.practice_mobilelele.service.Impl;

import com.example.practice_mobilelele.model.entity.Brand;
import com.example.practice_mobilelele.repository.BrandRepository;
import com.example.practice_mobilelele.service.BrandService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public Set<Brand> getAllBrands() {
        return new HashSet<>(brandRepository.findAllBrandsWithModels());
    }
}
