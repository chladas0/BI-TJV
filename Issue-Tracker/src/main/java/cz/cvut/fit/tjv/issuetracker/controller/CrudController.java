package cz.cvut.fit.tjv.issuetracker.controller;

import cz.cvut.fit.tjv.issuetracker.service.CrudService;
import cz.cvut.fit.tjv.issuetracker.Exception.EntityStateException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class CrudController <ID, Entity, DTO, CreateDTO>{
    protected final CrudService<ID, Entity, DTO, CreateDTO> crudService;

    public CrudController(CrudService<ID, Entity, DTO, CreateDTO> crudService) {
        this.crudService = crudService;
    }

    @GetMapping
    public List<DTO> getAll()
    {
        return crudService.findAll();
    }

    @GetMapping("/{id}")
    public DTO getById(@PathVariable ID id){
        return crudService.findByIDasDTO(id).orElseThrow(()
                -> new EntityStateException("Entity not found"));
    }

    @PostMapping
    public DTO create(@RequestBody CreateDTO createDTO){
        return crudService.create(createDTO);
    }

    @PutMapping("/{id}")
    DTO update (@PathVariable ID id, @RequestBody CreateDTO createDTO){
            return crudService.update(id, createDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable ID id){
            crudService.deleteById(id);
    }
}
