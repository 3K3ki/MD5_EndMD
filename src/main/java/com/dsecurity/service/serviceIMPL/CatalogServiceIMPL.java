package com.dsecurity.service.serviceIMPL;

import com.dsecurity.model.Catalog;
import com.dsecurity.repository.ICatalogRepository;
import com.dsecurity.service.ICatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatalogServiceIMPL implements ICatalogService {
    private ICatalogService iCatalogService;
    private final ICatalogRepository iCatalogRepository;

    @Override
    public List<Catalog> findAll() {
        return iCatalogRepository.findAll();
    }

    @Override
    public Catalog save(Catalog catalog) {
        return iCatalogRepository.save(catalog);
    }

    @Override
    public void deleteById(Long id) {
        iCatalogRepository.deleteById(id);
    }

    @Override
    public Catalog findById(Long id) {
        return iCatalogRepository.findById(id).get();
    }

    @Override
    public Catalog update(Long id, Catalog catalog) {
        return null;
    }
}
