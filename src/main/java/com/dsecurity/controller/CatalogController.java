package com.dsecurity.controller;

import com.dsecurity.dto.response.ResponseMessage;
import com.dsecurity.model.Catalog;
import com.dsecurity.service.ICatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/catalogs")
@CrossOrigin(origins = "*")
public class CatalogController {
    @Autowired
    private ICatalogService icatalogService;

    @GetMapping
    public ResponseEntity<List<Catalog>> findAll(){
        List<Catalog> list = (List<Catalog>) icatalogService.findAll();
        if (list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(list,HttpStatus.OK);
        }
    }
    @PostMapping("/create")
    public ResponseEntity<Catalog> create(@RequestBody Catalog catalog){
        Catalog catalogs = icatalogService.save(catalog);
        return new ResponseEntity<>(catalogs,HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public  ResponseEntity<Catalog> update(@PathVariable("id") Long id,@RequestBody Catalog catalog){
        Optional<Catalog> bookOptional = Optional.ofNullable(icatalogService.findById(id));
        if(bookOptional.isPresent()){
            catalog.setCatalogId(id);
            icatalogService.save(catalog);
            return new ResponseEntity<>(catalog,HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Catalog> delete(@PathVariable("id") Long id){
        Optional<Catalog> bookOptional = Optional.ofNullable(icatalogService.findById(id));
        if(bookOptional.isPresent()){
            icatalogService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Catalog> findById(@PathVariable("id") Long id){
        Optional<Catalog> catalogOptional= Optional.ofNullable(icatalogService.findById(id));
        if (catalogOptional.isPresent()){
            return new ResponseEntity<>(catalogOptional.get(),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}